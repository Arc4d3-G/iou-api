package com.example.iou_api.service;

import com.example.iou_api.model.Debt;
import com.example.iou_api.model.DebtStatus;
import com.example.iou_api.repository.DebtRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DebtService {
    private final DebtRepository debtRepository;

    @Autowired
    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    public Debt createDebt(Debt debt) {
        return debtRepository.save(debt);
    }

    public Optional<Debt> getDebtById(Long debtId) {
        return debtRepository.findById(debtId);
    }

    public List<Debt> getDebtsByGroupId(Long groupId) {
        return debtRepository.findByGroupId(groupId);
    }

    public void markDebtAsPaid(Long debtId) {
        Debt debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new RuntimeException("Debt not found"));
        debt.setStatus(DebtStatus.PAID);
        debtRepository.save(debt);
    }
}

