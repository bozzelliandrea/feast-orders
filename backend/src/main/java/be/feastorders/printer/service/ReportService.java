package be.feastorders.printer.service;

import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.order.entity.Order;
import be.feastorders.order.entity.OrderItemDetail;
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
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public String parseThymeleafTemplate() {
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

    public void generatePdfFromHtml(String html) throws DocumentException, IOException {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

}
