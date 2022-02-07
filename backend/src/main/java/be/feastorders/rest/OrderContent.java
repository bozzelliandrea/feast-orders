package be.feastorders.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderContent implements Serializable {

    private static final long serialVersionUID = -1990103258206178473L;

    private String itemId;
    private String categoryId;
    private Integer qty;
    private List<String> additions;
    private List<String> less;
    private String info;
    private Double price;
}

