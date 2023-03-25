package com.zhakav.ecommerce.entity;

import com.zhakav.ecommerce.entity.Discount;

import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

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
import jakarta.validation.constraints.NotNull;
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
@Table(name = "products")
public class Product {
    
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Product name cannot be blank!!!")
    private String name;

    @NonNull
    @Column(name = "description" , nullable = false)
    @NotBlank(message = "Product description cannot be blank!!!")
    private String desc;

    @NonNull
    @Column(name = "SKU" , nullable = false)
    @NotNull(message = "Product SKU cannot be Null!!!")
    private String sku;

    @NonNull
    @Min(value = 0)
    @Column(name = "price" , nullable = false)
    @NotNull(message = "Product price cannot be Null!!!")
    private Long price;

    @NonNull
    //@URL(message = "Invalid URL")
    @NotNull(message = "Product main picture cannot be blank!!!")
    @Column(name = "main_picture" , nullable = false)
    private String mainPicture;

    @NonNull
    //@URL(message = "Invalid URL")
    @NotNull(message = "Product pictures cannot be blank!!!")
    @Column(name = "pictures" , nullable = false)
    private List<String> pictures;

    @NonNull
    @NotNull(message = "Product availability cannot be blank!!!")
    @Column(name = "available" , nullable = false)
    boolean available;

    @Min(value = 0)
    @Column(name = "size")
    private int size;

    @Column(name = "color")
    private String color;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_category_id", referencedColumnName = "product_category_id")
    private ProductCategory category;

    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_inventory_id", referencedColumnName = "product_inventory_id")
    private ProductInventory inventory;

    @JsonIgnore
    @OneToOne(optional = true)
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id")
    private Discount discount;

    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private CartItem cartItem;

    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private OrderItem orderItem;
}
