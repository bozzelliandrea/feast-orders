package atomic.bean;

import java.io.Serializable;

public class KeyMap implements Serializable {

    private static final long serialVersionUID = -7729583320581876280L;

    private Object key;
    private Object value;

    public KeyMap() {
    }

    public KeyMap(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
