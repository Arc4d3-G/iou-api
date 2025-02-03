package com.example.iou_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iou_api.model.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
