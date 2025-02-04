package com.example.iou_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iou_api.model.Payment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    BigDecimal sumPaymentsByDebtId(Long debtId);

    List<Payment> findByDebtId(Long debtId);
}
