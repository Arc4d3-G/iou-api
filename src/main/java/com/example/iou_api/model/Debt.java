package com.example.iou_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id")
    private Long debtId;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "lender_id", nullable = false)
    private GroupMember lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private GroupMember borrower;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DebtStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Debt() {
    }

    public Debt(Group group, GroupMember lender, GroupMember borrower, BigDecimal amount, String description, DebtStatus status) {
        this.group = group;
        this.lender = lender;
        this.borrower = borrower;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupMember getLender() {
        return lender;
    }

    public void setLender(GroupMember lender) {
        this.lender = lender;
    }

    public GroupMember getBorrower() {
        return borrower;
    }

    public void setBorrower(GroupMember borrower) {
        this.borrower = borrower;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DebtStatus getStatus() {
        return status;
    }

    public void setStatus(DebtStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
