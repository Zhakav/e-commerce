package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "shopping_sessions")
public class ShoppingSession {
    
    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Min(value = 0)
    @Column(name = "total" , nullable = false)
    @NotNull(message = "Total cannot be blank!!!")
    private Long total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user; 

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
