package be.feastorders.order.entity.type;

import be.feastorders.rest.OrderContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class JsonOrderContentConverter implements AttributeConverter<List<OrderContent>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<OrderContent> meta) {
        return objectMapper.writeValueAsString(meta);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public List<OrderContent> convertToEntityAttribute(String dbData) {
        return objectMapper.readValue(dbData, List.class);
    }
}
