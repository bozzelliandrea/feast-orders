package atomic.type;

import arch.exception.SneakyThrows;
import atomic.bean.KeyMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class JsonMapConverter implements AttributeConverter<List<KeyMap>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<KeyMap> keyMaps) {
        try {
            return objectMapper.writeValueAsString(keyMaps);
        } catch (JsonProcessingException e) {
            SneakyThrows.execute(e);
        }
        return null;
    }

    @SuppressWarnings("all")
    @Override
    public List<KeyMap> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, List.class);
        } catch (JsonProcessingException e) {
            SneakyThrows.execute(e);
        }
        return null;
    }
}
