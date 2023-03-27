package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "discounts")
public class Discount {

    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Discount name cannot be blank!!!")
    private String name;

    @NonNull
    @Column(name = "discount_percent" , nullable = false)
    @NotNull(message = "Discount percentage cannot be Null!!!")
    private float discountPercent;

    @NonNull
    @Column(name = "active" , nullable = false)
    @NotNull (message = "Discount activity cannot be Null!!!")
    private boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
}
