package business.printer.service;

import atomic.entity.Order;
import atomic.entity.PrinterCfg;
import atomic.entity.ReportTemplate;
import atomic.repository.ReportTemplateRepository;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final static String REPORT_TEMPLATE_FOLDER = "user.home";
    private final ReportTemplateRepository repository;

    public ReportService(ReportTemplateRepository repository) {
        this.repository = repository;
    }

    public List<ReportTemplate> getReportTemplates() {
        return repository.findAll();
    }

    public String createPdf(Order order, PrinterCfg printerCfg) throws DocumentException, IOException {
        String template = parseThymeleafTemplate(order, printerCfg);
        String orderFileName = printerCfg.getReportTemplate().getName() + "_" + order.getId() + ".pdf";
        return generatePdfFromTemplate(template, REPORT_TEMPLATE_FOLDER, orderFileName);
    }

    public String parseThymeleafTemplate(Order order, PrinterCfg printerCfg) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Map<String, Object> variables = new HashMap<>();
        variables.put("order", order);

        Context context = new Context();
        context.setVariables(variables);

        String templateFilePath = "templates/" + printerCfg.getReportTemplate().getFilepath();
        return templateEngine.process(templateFilePath, context);
    }

    public String generatePdfFromTemplate(String template, String destinationFolder, String destinationFileName) throws IOException, DocumentException {
        String outputFolder = System.getProperty(destinationFolder) + File.separator + destinationFileName;
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(template);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
        return outputFolder;
    }

}
