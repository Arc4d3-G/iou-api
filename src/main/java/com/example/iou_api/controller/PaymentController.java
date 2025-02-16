package com.example.iou_api.controller;

import com.example.iou_api.dto.DebtDTO;
import com.example.iou_api.dto.PaymentDTO;
import com.example.iou_api.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("iou/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // POST request to create a new payment
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            PaymentDTO recordedPayment = paymentService.recordPayment(paymentDTO);
            return ResponseEntity.ok(recordedPayment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
