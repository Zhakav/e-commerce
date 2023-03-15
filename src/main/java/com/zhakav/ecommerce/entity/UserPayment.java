package com.zhakav.ecommerce.entity;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "user_payments")
public class UserPayment {
    
    @Id
    @Column(name = "user_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "payment_type" , nullable = false)
    @NotBlank(message = "Payment type cannot be blank!!!")
    private String paymentType;

    @NonNull
    @Column(name = "provider" , nullable = false)
    @NotBlank(message = "Provider cannot be blank!!!")
    private String provider;

    @NonNull
    @Column(name = "account_number" , nullable = false)
    @NotBlank(message = "Account number cannot be blank!!!")
    private String accountNo;

    @NonNull
    @Column(name = "expiry" , nullable = false)
    @NotBlank(message = "Expiry cannot be blank!!!")
    private LocalDateTime expiry;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
