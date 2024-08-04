package com.pos.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id",length = 80)
    private String productId;
    private String name;

    @Lob
    @Column(name="description")
    private Blob description;
    @Column(name="price", precision = 2)
    private BigDecimal price;
    private int quantity;
    @Column(name="created_date", columnDefinition = "DATETIME")
    private Date createdDate;
    @Column(name="last_update", columnDefinition = "DATETIME")
    private Date lastUpdate;

    @OneToMany(mappedBy = "product")
    private Set<ProductHasBatch> batchData= new HashSet<>();

    /*@JoinTable(
            name="product_has_batch",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="batch_id")
    )
    private Set<Batch> batches = new HashSet<>();*/

}
