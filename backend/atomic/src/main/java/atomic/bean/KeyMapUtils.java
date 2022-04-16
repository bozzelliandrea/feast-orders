package atomic.bean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class KeyMapUtils {

    public static Map<?, ?> toMap(List<KeyMap> in) {
        return in.stream().collect(Collectors.toMap(KeyMap::getKey, KeyMap::getValue));
    }

    public static List<KeyMap> toList(Map<?, ?> in) {
        return in.entrySet().stream().map(e -> new KeyMap(e.getKey(), e.getValue())).collect(Collectors.toList());
    }
}
