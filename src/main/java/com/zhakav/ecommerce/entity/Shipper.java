package com.zhakav.ecommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Telephone;

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
    @OneToMany(mappedBy = "shipper")
    private List<OrderDetail> orderDetail;

}
