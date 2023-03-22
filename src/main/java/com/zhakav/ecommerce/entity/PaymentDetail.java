package com.zhakav.ecommerce.entity;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PaymentDetail {
 
    @Id
    @Column(name = "payment_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Min(value = 0)
    @Column(name = "amount" , nullable = false)
    @NotBlank(message = "Amount cannot be blank!!!")
    private Long amount;

    @NonNull
    @Column(name = "provider", nullable = false)
    @NotBlank(message = "Provider cannot be blank!!!")
    private String provider;

    @NonNull
    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status cannot be blank!!!")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @OneToOne(mappedBy = "paymentDetail")
    private OrderDetail orderDetail;

}
