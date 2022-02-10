package atomic.bean;

import java.io.Serializable;
import java.util.List;

public class OrderContent implements Serializable {

    private static final long serialVersionUID = -1990103258206178473L;

    private String itemId;
    private String categoryId;
    private Integer qty;
    private List<String> additions;
    private List<String> less;
    private String info;
    private Double price;

    public OrderContent() {
    }

    public OrderContent(String itemId, String categoryId, Integer qty, List<String> additions, List<String> less, String info, Double price) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.qty = qty;
        this.additions = additions;
        this.less = less;
        this.info = info;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<String> getAdditions() {
        return additions;
    }

    public void setAdditions(List<String> additions) {
        this.additions = additions;
    }

    public List<String> getLess() {
        return less;
    }

    public void setLess(List<String> less) {
        this.less = less;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

