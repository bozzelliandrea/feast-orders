package be.feastorders.printer.service;

import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.order.entity.Order;
import be.feastorders.order.entity.OrderItemDetail;
import be.feastorders.printer.entity.PrinterCfg;
import be.feastorders.printer.entity.ReportTemplate;
import be.feastorders.printer.repository.ReportTemplateRepository;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;
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
        String orderFileName = printerCfg.getReportTemplate().getName() + "_" + order.getID() + ".pdf";
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

    public String parseThymeleafTemplate() {
        // todo to remove
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Order order = new Order();
        order.setClient("Rizzaccio");
        order.setTableNumber(1L);
        order.setCashier("Cassa");
        order.setPlaceSettingNumber(6L);
        order.setTotal(55.30f);
        MenuItem menuItem = new MenuItem();
        menuItem.setName("Birra media bionda");
        menuItem.setPrice(5f);

        OrderItemDetail orderItemDetail = new OrderItemDetail();
        orderItemDetail.setOrder(order);
        orderItemDetail.setMenuItem(menuItem);
        orderItemDetail.setQuantity(1L);
        orderItemDetail.setTotalPrice(10f);

        List<OrderItemDetail> orderItemDetails = new ArrayList<>();
        orderItemDetails.add(orderItemDetail);
        order.setOrderItemDetails(orderItemDetails);

        Map<String, Object> variables = new HashMap<>();
        variables.put("order", order);

        Context context = new Context();
        context.setVariables(variables);

        return templateEngine.process("templates/thymeleaf_template", context);
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

    public void generatePdfFromHtml(String html) throws DocumentException, IOException {
        // todo to remove
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

}
