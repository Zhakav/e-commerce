package com.zhakav.ecommerce.entity;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "admin_types")
public class AdminType {
    
    @Id
    @Column(name = "admin_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NonNull
    @Column(name = "admin_type" , nullable = false)
    @NotBlank(message = "Admin type cannot be blank!!!")
    private String adminType;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "modified_at")
    LocalDateTime modifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "adminType", cascade = CascadeType.ALL)
    List<AdminUser> adminUsers;
}
