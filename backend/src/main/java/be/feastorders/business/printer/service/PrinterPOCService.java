package be.feastorders.business.printer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
public class PrinterPOCService {

    @Value("${printerpoc.filepath}")
    private String filePath;

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

    public boolean print() {
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = ps.createPrintJob();
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printDataTransferCompleted(PrintJobEvent event) {
                System.out.println("data transfer complete");
            }

            public void printJobNoMoreEvents(PrintJobEvent event) {
                System.out.println("received no more events");
            }
        });

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        // Doc doc=new SimpleDoc(fis, DocFlavor.INPUT_STREAM.JPEG, null);
        PrintRequestAttributeSet attrib = new HashPrintRequestAttributeSet();
//        attrib.add(new Copies(1));
        try {
            job.print(doc, attrib);
        } catch (PrintException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // A utility method to look up printers that can support the specified
    // attributes and return the one that matches the specified name.
    public static PrintService getNamedPrinter(String name, PrintRequestAttributeSet attrs) {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, attrs);
        if (services.length > 0) {
            if (name == null)
                return services[0];
            else {
                for (int i = 0; i < services.length; i++) {
                    if (services[i].getName().equals(name))
                        return services[i];
                }
            }
        }
        return null;
    }

}
