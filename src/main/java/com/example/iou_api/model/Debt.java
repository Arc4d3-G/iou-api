package com.example.iou_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id", nullable = false)
    private Integer debtId;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "creditor_id", nullable = false)
    private GroupMember creditor;

    @ManyToOne
    @JoinColumn(name = "debtor_id", nullable = false)
    private GroupMember debtor;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DebtStatus status;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    public Debt() {
    }

    public Debt(Group group, GroupMember creditor, GroupMember debtor, BigDecimal amount, String description, DebtStatus status) {
        this.group = group;
        this.creditor = creditor;
        this.debtor = debtor;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupMember getCreditor() {
        return creditor;
    }

    public void setCreditor(GroupMember creditor) {
        this.creditor = creditor;
    }

    public GroupMember getDebtor() {
        return debtor;
    }

    public void setDebtor(GroupMember debtor) {
        this.debtor = debtor;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
