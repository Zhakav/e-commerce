package com.zhakav.ecommerce.entity;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Min(value = 0)
    @Column(name = "quantity" , nullable = false)
    @NotBlank(message = "Quantity cannot be blank!!!")
    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "order_detail_id")
    private OrderDetail orderDetail;

    @JsonIgnore
    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;


}
