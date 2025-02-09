package com.example.iou_api.dto;

import com.example.iou_api.model.DebtStatus;

import java.math.BigDecimal;

public class DebtDTO {
    private Integer debtId;
    private BigDecimal amount;
    private Integer groupId;
    private Integer debtorMemberId;
    private Integer creditorMemberId;
    private DebtStatus status;
    private String description;

    // Constructors
    public DebtDTO() {}

    public DebtDTO(Integer debtId, BigDecimal amount, Integer groupId, Integer debtorMemberId, Integer creditorMemberId, DebtStatus status, String description) {
        this.debtId = debtId;
        this.amount = amount;
        this.groupId = groupId;
        this.debtorMemberId = debtorMemberId;
        this.creditorMemberId = creditorMemberId;
        this.status = status;
        this.description = description;
    }

    // Getters and Setters
    public Integer getDebtId() { return debtId; }
    public void setDebtId(Integer debtId) { this.debtId = debtId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getGroupId() { return groupId; }
    public void setGroupId(Integer groupId) { this.groupId = groupId; }

    public Integer getDebtorMemberId() { return debtorMemberId; }
    public void setDebtorMemberId(Integer debtorMemberId) { this.debtorMemberId = debtorMemberId; }

    public Integer getCreditorMemberId() { return creditorMemberId; }
    public void setCreditorMemberId(Integer creditorMemberId) { this.creditorMemberId = creditorMemberId; }

    public DebtStatus getStatus() { return status; }
    public void setStatus(DebtStatus status) { this.status = status; }

    public String getDescription() {return description; }

    public void setDescription(String description) { this.description = description; }
}
