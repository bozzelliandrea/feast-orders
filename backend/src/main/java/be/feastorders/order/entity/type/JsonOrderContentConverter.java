package be.feastorders.order.entity.type;

import be.feastorders.rest.OrderContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class JsonOrderContentConverter implements AttributeConverter<List<OrderContent>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<OrderContent> meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException e) {
            SneakyThrows.execute(e);
        }
        return null;
    }

    @SuppressWarnings("all")
    @Override
    public List<OrderContent> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, List.class);
        } catch (JsonProcessingException e) {
            SneakyThrows.execute(e);
        }
        return null;
    }
}
