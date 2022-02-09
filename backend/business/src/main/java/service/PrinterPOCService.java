package service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PrinterPOCService {

    // List names of all PrintServices that can support the attributes
    public List<PrintService> getPrinterServices(DocFlavor docFlavor, PrintRequestAttributeSet attributes) {
        // Find all services that can support the specified docFlavor and attributes
        List<PrintService> services = Arrays.asList(PrintServiceLookup.lookupPrintServices(docFlavor, attributes));
        // Loop through available services
        for (PrintService service : services) {
            // Then query and print the document types it can print
            DocFlavor[] flavors = service.getSupportedDocFlavors();
            for (DocFlavor flavor : flavors) {
                // Filter out DocFlavors that have a representation class other
                // than java.io.InputStream.
                String repclass = flavor.getRepresentationClassName();
                if (!repclass.equals("java.io.InputStream"))
                    continue;
            }
        }

        return services;
    }

    // print to a real printer
    public boolean print(String printerName, Map<String, String> params) {
        String filename = params.get("filename");
        Objects.requireNonNull(filename);

        PrintRequestAttributeSet attrs = getAttributes(params);
        PrintService ps = getPrinterService(printerName, attrs).orElseThrow(IllegalArgumentException::new);
        return printToService(filename, attrs, ps);
    }

    // Print to an output file instead of a printer
    public boolean printToFile(Map<String, String> params) {
        String inputFileName = params.get("inputFileName");
        String outputFileName = params.get("outputFileName");
        String outputMimeType = params.get("outputMimeType");

        Objects.requireNonNull(inputFileName);
        Objects.requireNonNull(outputFileName);

        // Figure out what type of file we're printing
        DocFlavor inputFlavor = getFlavorFromFilename(inputFileName); //DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet attrs = getAttributes(params);

        if (outputMimeType == null) {
            // TODO sistemare
            outputMimeType = "application/pdf";
        }
        StreamPrintServiceFactory[] factories = StreamPrintServiceFactory.lookupStreamPrintServiceFactories(inputFlavor, outputMimeType);

        // Error message if we can't print to the specified output type
        if (factories.length == 0) {
            System.out.println("Unable to print files of type: " + outputMimeType);
            return false;
        }

        // Open the output file
        try (FileOutputStream out = new FileOutputStream(outputFileName)) {
            // Get a PrintService object to print to that file
            StreamPrintService service = factories[0].getPrintService(out);
            // Print using the method below
            printToService(inputFileName, attrs, service);
            // And remember to close the output file
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean printPdf(String printerName, Map<String, String> params) throws IllegalArgumentException {
        String filename = params.get("filename");
        Objects.requireNonNull(filename);

        PrintService service = getPrinterServiceAwt(printerName).orElseThrow(IllegalArgumentException::new);

        PrinterJob job = PrinterJob.getPrinterJob();

        try (FileInputStream fis = new FileInputStream(filename)) {
            PDDocument document = PDDocument.load(fis);
            job.setPageable(new PDFPageable(document));
            PrintRequestAttributeSet attrs = getAttributes(params);

            job.setPrintService(service);
            job.print(attrs);

            document.close();
            return true;
        } catch (PrinterException | IOException e) {
            e.printStackTrace();
            // todo gestire
            return false;
        }
    }

    private static boolean printToService(String filename, PrintRequestAttributeSet attrs, PrintService ps) {
        // Figure out what type of file we're printing
        DocFlavor flavor = getFlavorFromFilename(filename);
        // Create a print job from the service
        DocPrintJob job = ps.createPrintJob();

        // Monitor the print job with a listener
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printJobCompleted(PrintJobEvent e) {
                // TODO: sistemare usando logger slf4j
                System.out.println("Print job complete");
            }

            public void printDataTransferCompleted(PrintJobEvent e) {
                System.out.println("Document transferred to printer");
            }

            public void printJobRequiresAttention(PrintJobEvent e) {
                System.out.println("Print job requires attention");
                System.out.println("Check printer: out of paper?");
            }

            public void printJobFailed(PrintJobEvent e) {
                System.out.println("Print job failed");
            }
        });

        try (FileInputStream fis = new FileInputStream(filename)) {
            // Create a Doc object to print from the file and flavor.
            Doc doc = new SimpleDoc(fis, flavor, null);
            job.print(doc, attrs);
            return true;
        } catch (IOException | PrintException e) {
            //TODO non catturare l'eccezione ma propagarla e gestirla nel controller
            e.printStackTrace();
            return false;
        }
    }

    private static PrintRequestAttributeSet getAttributes(Map<String, String> params) {
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                switch (key) {
                    case "copies":
                        int copies = Integer.parseInt(params.get("copies"));
                        attrs.add(new Copies(copies));
                        break;
                    case "mediaSize":
                        String mediaSize = params.get("mediaSize");
                        if (mediaSize.equalsIgnoreCase("A4")) {
                            attrs.add(MediaSizeName.ISO_A4);
                        } else if (mediaSize.equalsIgnoreCase("A5")) {
                            attrs.add(MediaSizeName.ISO_A5);
                        } else {
                            // TODO: gestire gli altri tipi, default A4
                            attrs.add(MediaSizeName.ISO_A4);
                        }
                        break;
                    case "orientation":
                        String orientation = params.get("orientation");
                        if (orientation.equalsIgnoreCase("portrait")) {
                            attrs.add(OrientationRequested.PORTRAIT);
                        } else if (orientation.equalsIgnoreCase("landscape")) {
                            attrs.add(OrientationRequested.LANDSCAPE);
                        } else {
                            // TODO: gestire gli altri tipi, default portrait
                            attrs.add(OrientationRequested.PORTRAIT);
                        }
                        break;
                    case "sides":
                        String sides = params.get("sides");
                        if (sides.equalsIgnoreCase("one-side")) {
                            attrs.add(Sides.ONE_SIDED);
                        } else if (sides.equalsIgnoreCase("duplex")) {
                            attrs.add(Sides.DUPLEX);
                        } else {
                            // TODO: gestire gli altri tipi, default one-side
                            attrs.add(Sides.ONE_SIDED);
                        }
                        break;
                    case "color":
                        boolean color = Boolean.parseBoolean(params.get("color"));
                        if (color) {
                            attrs.add(Chromaticity.COLOR);
                        }
                        break;
                }
            }
        }
        return attrs;
    }

    // A utility method to look up printers that can support the specified
    // attributes and return the one that matches the specified name.
    private static Optional<PrintService> getPrinterService(String printerName, PrintRequestAttributeSet attrs) {
        Optional<PrintService> service = Arrays.stream(PrintServiceLookup.lookupPrintServices(null, attrs))
                .filter(printService -> {
                    return printService.getName().equalsIgnoreCase(printerName);
                }).findFirst();
        return service;
    }

    private static Optional<PrintService> getPrinterServiceAwt(String printerName) {
        Optional<PrintService> service = Arrays.stream(PrinterJob.lookupPrintServices())
                .filter(printService -> {
                    return printService.getName().equalsIgnoreCase(printerName);
                }).findFirst();
        return service;
    }

    // A utility method to return a DocFlavor object matching the
    // extension of the filename.
    private static DocFlavor getFlavorFromFilename(String filename) {
        Objects.requireNonNull(filename);

        String extension = filename.substring(filename.lastIndexOf('.') + 1);
        extension = extension.toLowerCase();
        switch (extension) {
            case "gif":
                return DocFlavor.INPUT_STREAM.GIF;
            case "jpeg":
            case "jpg":
                return DocFlavor.INPUT_STREAM.JPEG;
            case "png":
                return DocFlavor.INPUT_STREAM.PNG;
            case "ps":
                return DocFlavor.INPUT_STREAM.POSTSCRIPT;
            case "txt":
                return DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
            case "pdf":
                return DocFlavor.INPUT_STREAM.PDF;
            // Fallback: try to determine flavor from file content
            default:
                return DocFlavor.INPUT_STREAM.AUTOSENSE;
        }
    }
}
