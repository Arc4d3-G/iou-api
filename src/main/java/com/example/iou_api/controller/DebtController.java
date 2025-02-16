package com.example.iou_api.controller;

import com.example.iou_api.dto.DebtDTO;
import com.example.iou_api.model.Debt;
import com.example.iou_api.service.DebtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/iou/debts")
public class DebtController {

    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    // Create a new debt
    @PostMapping
    public ResponseEntity<?> createDebt(@RequestBody DebtDTO debtDTO) {
        try {
            DebtDTO createdDebt = debtService.createDebt(debtDTO);
            return ResponseEntity.ok(createdDebt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Get a single debt by ID
    @GetMapping("/{debtId}")
    public ResponseEntity<?> getDebtById(@PathVariable Integer debtId) {
        Optional<DebtDTO> debt = debtService.getDebtById(debtId);
        return debt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all debts for a specific group
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<DebtDTO>> getDebtsByGroupId(@PathVariable Integer groupId) {
        List<DebtDTO> debts = debtService.getDebtsByGroupId(groupId);
        return ResponseEntity.ok(debts);
    }

    // Mark a debt as paid
    @PutMapping("/{debtId}/pay")
    public ResponseEntity<?> markDebtAsPaid(@PathVariable Integer debtId) {
        try {
            debtService.markDebtAsPaid(debtId);
            return ResponseEntity.ok("Debt marked as paid.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
