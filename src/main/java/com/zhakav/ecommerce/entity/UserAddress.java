package com.zhakav.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Mobile;
import com.zhakav.ecommerce.validators.PostalCode;
import com.zhakav.ecommerce.validators.Telephone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_addresses")
public class UserAddress {
    
    @Id
    @Column(name = "user_address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "address_line_1" , nullable = false)
    @NotBlank(message = "addressLine1 cannot be blank!!!")
    private String addressLine1;

    @NonNull
    @Column(name = "address_line_2" , nullable = false)
    @NotBlank(message = "addressLine2 cannot be blank!!!")
    private String addressLine2;

    @NonNull
    @Column(name = "city" , nullable = false)
    @NotBlank(message = "City cannot be blank!!!")
    private String city;

    @NonNull
    @PostalCode(message = "Invalid postal code!!")
    @Column(name = "postal_code" , nullable = false)
    @NotBlank(message = "Postal code cannot be blank!!!")
    private String postalCode;

    @NonNull
    @Column(name = "country")
    @NotBlank(message = "Country cannot be blank!!!")
    private String country;

    @Column(name = "mobile")
    @Mobile(message = "Invalid mobile phone number!!")
    private String mobile;

    @Column(name = "telephone")
    @Telephone(message = "Invalid telephone number!!")
    private String telephone;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
