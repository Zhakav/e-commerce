package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Telephone;

import jakarta.persistence.*;
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
@Table(name = "shippers")
public class Shipper {
    
    @Id
    @Column(name = "shipper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Company name cannot be blank!!!")
    private String companyName;

    @Column(name = "telephone")
    @Telephone(message = "Invalid telephone number!!")
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

}
