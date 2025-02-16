package com.example.iou_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auth_user_id", nullable = false)
    private Integer authUserId;  // Foreign key reference (not a direct relation)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 10, nullable = false)
    private Currency currency;

    @Column(length = 255)
    private String name;

    public User() {}

    public User(Integer authUserId, Currency currency, String name) {
        this.authUserId = authUserId;
        this.currency = currency;
        this.name = name;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getAuthUserId() { return authUserId; }
    public void setAuthUserId(Integer authUserId) { this.authUserId = authUserId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public String getName() { return name;}
    public void setName(String name) { this.name = name; }

}
