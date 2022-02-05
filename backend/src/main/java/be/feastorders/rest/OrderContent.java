package be.feastorders.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderContent implements Serializable {
    private static final long serialVersionUID = -1990103258206178473L;
    private String itemId;
    private Category category;
    private Integer qty;
    private List<String> additions;
    private List<String> less;
    private String info;
    private Double price;

    public OrderContent() {
    }

    public OrderContent(String itemId, Category category, Integer qty, List<String> additions, List<String> less, String info, Double price) {
        this.itemId = itemId;
        this.category = category;
        this.qty = qty;
        this.additions = additions;
        this.less = less;
        this.info = info;
        this.price = price;
    }

    public enum Category {
        SUB(5.50), DRINK(4.50), MEAL(6.0);

        private final Double price;

        Category(Double price) {
            this.price = price;
        }

        public Double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static OrderContent empty() {
        OrderContent c = new OrderContent();
        c.setAdditions(new ArrayList<>());
        c.setLess(new ArrayList<>());
        c.setPrice(0.0);
        c.setQty(0);
        return c;
    }

    public static Builder emptyBuilder() {
        return new Builder(empty());
    }

    public static Builder builder() {
        return new Builder();
    }

    protected static final class Builder {
        private String itemId;
        private Category category;
        private Integer qty;
        private List<String> additions;
        private List<String> less;
        private String info;
        private Double price;

        public Builder(OrderContent c) {
            this.additions = c.additions;
            this.less = c.less;
            this.qty = c.qty;
            this.price = c.price;
        }

        public Builder() {
        }

        public OrderContent build() {
            return new OrderContent(this.itemId,
                    this.category,
                    this.qty,
                    this.additions,
                    this.less,
                    this.info,
                    this.price);
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder qty(Integer qty) {
            this.qty = qty;
            return this;
        }

        public Builder additions(List<String> additions) {
            this.additions = additions;
            return this;
        }

        public Builder additions(String addition) {
            if (this.additions == null) {
                this.additions = new ArrayList<>();
            }
            this.additions.add(addition);
            return this;
        }

        public Builder less(List<String> less) {
            this.less = less;
            return this;
        }

        public Builder less(String l) {
            if (this.less == null) {
                this.less = new ArrayList<>();
            }
            this.less.add(l);
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    @JsonIgnore
    public boolean isSub() {
        return this.category.equals(Category.SUB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderContent orderContent = (OrderContent) o;
        return Objects.equals(itemId, orderContent.itemId) && category == orderContent.category && Objects.equals(qty, orderContent.qty) && Objects.equals(additions, orderContent.additions) && Objects.equals(less, orderContent.less) && Objects.equals(info, orderContent.info) && Objects.equals(price, orderContent.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, category, qty, additions, less, info, price);
    }
}

