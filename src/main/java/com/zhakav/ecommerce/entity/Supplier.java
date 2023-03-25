package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Mobile;
import com.zhakav.ecommerce.validators.PostalCode;
import com.zhakav.ecommerce.validators.Telephone;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
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
@Table(name = "suppliers")
public class Supplier {
    
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Company Name cannot be blank!!!")
    private String companyName;

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

    @NonNull
    @Column(name = "mobile")
    @NotBlank(message = "Mobile number cannot be blank!!")
    @Mobile(message = "Invalid mobile phone number!!")
    private String mobile;

    @NonNull
    @NotBlank(message = "Telephone number cannot be blank!!")
    @Column(name = "telephone")
    @Telephone(message = "Invalid telephone number!!")
    private String telephone;

    @Nonnull
    @Email(message = "Invalid email!!")
    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email cannot be blank!!")
    private String email;

    @Nonnull
    @Column(name = "type_goods", nullable = false)
    @NotNull(message = "Type of goods cannot be null!!")
    private List<String> typeGoods;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime ModifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Product> products;

}
