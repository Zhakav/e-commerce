package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "product_categories")
public class ProductCategory {
    
    @Id
    @Column(name = "product_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Category name cannot be blank!!!")
    private String name;

    @NonNull
    @Column(name = "description" , nullable = false)
    @NotBlank(message = "Category description cannot be blank!!!")
    private String desc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
