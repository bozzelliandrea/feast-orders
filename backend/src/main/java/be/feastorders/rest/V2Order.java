package be.feastorders.rest;


import be.feastorders.order.entity.type.JsonOrderContentConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_NEW")
@Getter
@Setter
public class V2Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "content")
    @Convert(converter = JsonOrderContentConverter.class)
    private OrderContent orderContent;
}
