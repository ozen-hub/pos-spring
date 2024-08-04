package com.pos.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name="customer_order_id")
    private CustomerOrder customerOrder;

    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unit_price", precision = 2)
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 2)
    private BigDecimal discount;
}
