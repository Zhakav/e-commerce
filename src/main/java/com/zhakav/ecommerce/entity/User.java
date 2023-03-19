package com.zhakav.ecommerce.entity;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Mobile;
import com.zhakav.ecommerce.validators.Password;
import com.zhakav.ecommerce.validators.Username;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Username(message= "Invalid username!!")
    @NotBlank(message = "Username cannot be blank!!!")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NonNull
    @Password(message = "Invalid password!!")
    @NotBlank(message = "Password cannot be blank!!!")
    @Column(name = "password" , nullable = false, unique = true)
    private String password;

    @NonNull
    @Column(name = "first_name" , nullable = false)
    @NotBlank(message = "First name cannot be blank!!!")
    private String firstName;

    @NonNull
    @Column(name = "last_name" , nullable = false)
    @NotBlank(message = "Last name cannot be blank!!!")
    private String lastName;

    @NonNull
    @Column(name = "mobile")
    @Mobile(message = "Invalid Phone number!!")
    @NotBlank(message = "Phone number cannot be blank!!!")
    private String mobile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime ModifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAddress> userAddresses;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPayment> userPayments;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingSession session;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}
