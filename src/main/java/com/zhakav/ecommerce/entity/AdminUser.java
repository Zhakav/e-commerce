package com.zhakav.ecommerce.entity;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhakav.ecommerce.validators.Password;
import com.zhakav.ecommerce.validators.Username;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "admin_users")
public class AdminUser {
 
    @Id
    @Column(name = "admin_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Username(message = "Invalid username!!")
    @Column(name = "username" , nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank!!!")
    private String username;

    @NonNull
    @Password(message = "Invalid password!!")
    @Column(name = "password" , nullable = false, unique = true)
    @NotBlank(message = "Password cannot be blank!!!")
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
    @NotBlank(message = "Age cannot be blank!")
    @Min(value = 7, message = "Age cannot be less than 7")
    @Column(name = "age", nullable = false)
    private int age;

    @NonNull
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email cannot be blank!")
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @NotBlank(message = "Gender cannot be blank!")
    @Column(name = "gender")
    private String gender;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime ModifiedAt;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_type_id", referencedColumnName ="admin_type_id" )
    private AdminType adminType;

}
