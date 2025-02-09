package com.example.iou_api.service;

import com.example.iou_api.dto.PaymentDTO;
import com.example.iou_api.model.Debt;
import com.example.iou_api.model.DebtStatus;
import com.example.iou_api.model.GroupMember;
import com.example.iou_api.model.Payment;
import com.example.iou_api.repository.DebtRepository;
import com.example.iou_api.repository.GroupMemberRepository;
import com.example.iou_api.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final DebtService debtService;
    private final DebtRepository debtRepository;
    private final GroupMemberRepository groupMemberRepository;

    public PaymentService(PaymentRepository paymentRepository, DebtService debtService, DebtRepository debtRepository, GroupMemberRepository groupMemberRepository) {
        this.paymentRepository = paymentRepository;
        this.debtService = debtService;
        this.debtRepository = debtRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public PaymentDTO recordPayment(PaymentDTO paymentDTO) {

        boolean isPaidOff = false;

        // Get and validate debt and payer
        Debt debt = debtRepository.findById(paymentDTO.getDebtId())
                .orElseThrow(() -> new IllegalArgumentException("Debt not found"));

        // Check if debt is pending
        if(!debt.getStatus().equals(DebtStatus.PENDING)){
            throw new IllegalArgumentException("Debt is already paid");
        }

        GroupMember payer = groupMemberRepository.findById(paymentDTO.getPayerMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Payer not found"));

        // Check that payer is associated with the group
        if (!debt.getGroup().getGroupId().equals(payer.getGroup().getGroupId())) {
            throw new IllegalArgumentException("Payer is not part of the debt's group");
        }

        // Create payment
        Payment payment = new Payment(debt, payer, paymentDTO.getAmount());
        Payment savedPayment = paymentRepository.save(payment);

        // Check if debt is fully paid
        BigDecimal totalPaid = paymentRepository.sumPaymentsByDebtId(debt.getDebtId());
        if (totalPaid.compareTo(debt.getAmount()) >= 0) {
            debtService.markDebtAsPaid(debt.getDebtId());
            isPaidOff = true;
        }

        return new PaymentDTO(debt.getDebtId(), savedPayment.getPayer().getMemberId(), savedPayment.getAmount(), isPaidOff, savedPayment.getPaymentId());
    }

    public List<Payment> getPaymentsByDebtId(Integer debtId) {
        return paymentRepository.findByDebt_DebtId(debtId);
    }
}

