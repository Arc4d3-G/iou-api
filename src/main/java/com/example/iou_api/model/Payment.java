package com.example.iou_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "debt_id", nullable = false)
    private Debt debt;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private GroupMember payer;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private Instant paymentDate;

    public Payment() {}

    public Payment(Debt debt, GroupMember payer, BigDecimal amount, Instant paymentDate) {
        this.debt = debt;
        this.payer = payer;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public Debt getDebt() { return debt; }
    public void setDebt(Debt debt) { this.debt = debt; }

    public GroupMember getPayer() { return payer; }
    public void setPayer(GroupMember payer) { this.payer = payer; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Instant getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Instant paymentDate) { this.paymentDate = paymentDate; }
}
