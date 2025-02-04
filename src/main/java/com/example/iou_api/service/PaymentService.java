package com.example.iou_api.service;

import com.example.iou_api.model.Debt;
import com.example.iou_api.model.Payment;
import com.example.iou_api.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final DebtService debtService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, DebtService debtService) {
        this.paymentRepository = paymentRepository;
        this.debtService = debtService;
    }

    public Payment recordPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);

        // Check if full payment is completed
        Debt debt = debtService.getDebtById(payment.getDebt().getDebtId())
                .orElseThrow(() -> new RuntimeException("Debt not found"));

        BigDecimal totalPaid = paymentRepository.sumPaymentsByDebtId(debt.getDebtId());

        if (totalPaid.compareTo(debt.getAmount()) >= 0) {
            debtService.markDebtAsPaid(debt.getDebtId());
        }

        return savedPayment;
    }

    public List<Payment> getPaymentsByDebtId(Long debtId) {
        return paymentRepository.findByDebtId(debtId);
    }
}

