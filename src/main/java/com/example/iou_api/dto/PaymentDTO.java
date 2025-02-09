package com.example.iou_api.dto;

import com.example.iou_api.model.Payment;

import java.math.BigDecimal;

public class PaymentDTO {
    private Integer debtId;
    private Integer payerMemberId;
    private BigDecimal amount;
    private boolean isPaidOff;
    private Integer paymentId;

    public PaymentDTO() {
    }

    // Receiving
    public PaymentDTO(Integer debtId, Integer payerMemberId, BigDecimal amount) {
        this.debtId = debtId;
        this.payerMemberId = payerMemberId;
        this.amount = amount;
    }

    // Sending constructor
    public PaymentDTO(Integer debtId, Integer payerMemberId, BigDecimal amount, boolean isPaidOff, Integer paymentId) {
        this.debtId = debtId;
        this.payerMemberId = payerMemberId;
        this.amount = amount;
        this.isPaidOff = isPaidOff;
        this.paymentId = paymentId;
    }

    // Getters and Setters
    public Integer getDebtId() { return debtId; }
    public void setDebtId(Integer debtId) { this.debtId = debtId; }

    public Integer getPayerMemberId() { return payerMemberId; }
    public void setPayerMemberId(Integer payerMemberId) { this.payerMemberId = payerMemberId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public boolean isPaidOff() {return isPaidOff;}
    public void setPaidOff(boolean paidOff) {this.isPaidOff = paidOff;}

    public Integer getPaymentId() {return paymentId;}

    public void setPaymentId(Integer paymentId) {this.paymentId = paymentId;}
}
