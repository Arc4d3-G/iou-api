package com.example.iou_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iou_api.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.debt.debtId = :debtId")
    BigDecimal sumPaymentsByDebtId(@Param("debtId") Integer debtId);

    List<Payment> findByDebt_DebtId(Integer debtId);
}
