package be.feastorders.order.entity.type;

import be.feastorders.rest.OrderContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonOrderContentConverter implements AttributeConverter<OrderContent, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(OrderContent meta) {
        return objectMapper.writeValueAsString(meta);
    }

    @SneakyThrows
    @Override
    public OrderContent convertToEntityAttribute(String dbData) {
        return objectMapper.readValue(dbData, OrderContent.class);
    }
}
