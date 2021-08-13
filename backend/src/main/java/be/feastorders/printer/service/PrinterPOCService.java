package be.feastorders.printer.service;

import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.FileInputStream;
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
            // Print service name
            System.out.print(service.getName());

            // Then query and print the document types it can print
            DocFlavor[] flavors = service.getSupportedDocFlavors();
            for (DocFlavor flavor : flavors) {
                // Filter out DocFlavors that have a representation class other
                // than java.io.InputStream.
                String repclass = flavor.getRepresentationClassName();
                if (!repclass.equals("java.io.InputStream"))
                    continue;
                System.out.println("\t" + flavor.getMimeType());
            }
        }

        return services;
    }

    public boolean print(String printerName, Map<String, String> params) {
        String filename = params.get("filename");
        if (filename == null || filename.isEmpty()) {
            // TODO lanciare eccezione
            return false;
        }

        PrintRequestAttributeSet attrs = getAttributes(params);
        PrintService ps = getPrinterService(printerName, attrs).orElseThrow(IllegalArgumentException::new);
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
        } catch (IOException | PrintException e) {
            //TODO non catturare l'eccezione ma propagarla e gestirla nel controller
            e.printStackTrace();
        }

        return true;
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
    private static Optional<PrintService> getPrinterService(String name, PrintRequestAttributeSet attrs) {
        Optional<PrintService> service = Arrays.stream(PrintServiceLookup.lookupPrintServices(null, attrs))
                .filter(printService -> {
                    return printService.getName().equalsIgnoreCase(name);
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
            // Fallback: try to determine flavor from file content
            default:
                return DocFlavor.INPUT_STREAM.AUTOSENSE;
        }
    }
}
