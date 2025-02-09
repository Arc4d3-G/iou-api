package com.example.iou_api.service;

import com.example.iou_api.dto.DebtDTO;
import com.example.iou_api.model.*;
import com.example.iou_api.repository.DebtRepository;
import com.example.iou_api.repository.GroupMemberRepository;
import com.example.iou_api.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DebtService {
    private final DebtRepository debtRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    public DebtService(DebtRepository debtRepository, GroupRepository groupRepository, GroupMemberRepository groupMemberRepository) {
        this.debtRepository = debtRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public DebtDTO createDebt(DebtDTO debtDTO) {

        // Get and Validate group/groupMember
        Group group = groupRepository.findById(debtDTO.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
        GroupMember debtor = groupMemberRepository.findByGroup_GroupIdAndMemberId(debtDTO.getGroupId(), debtDTO.getDebtorMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Debtor not found"));
        GroupMember creditor = groupMemberRepository.findByGroup_GroupIdAndMemberId(debtDTO.getGroupId(), debtDTO.getCreditorMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Creditor not found"));

        // Create Debt
        Debt debt = new Debt();
        debt.setAmount(debtDTO.getAmount());
        debt.setGroup(group);
        debt.setDebtor(debtor);
        debt.setCreditor(creditor);
        debt.setStatus(DebtStatus.PENDING);
        debt.setDescription(debtDTO.getDescription());

        Debt savedDebt = debtRepository.save(debt);
        return mapToDTO(savedDebt);
    }

    public Optional<DebtDTO> getDebtById(Integer debtId) {
        return debtRepository.findById(debtId).map(this::mapToDTO);
    }

    public List<DebtDTO> getDebtsByGroupId(Integer groupId) {
        return debtRepository.findByGroup_GroupId(groupId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void markDebtAsPaid(Integer debtId) {
        Debt debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new RuntimeException("Debt not found"));
        debt.setStatus(DebtStatus.PAID);
        debtRepository.save(debt);
    }

    private DebtDTO mapToDTO(Debt debt) {
        return new DebtDTO(
                debt.getDebtId(),
                debt.getAmount(),
                debt.getGroup().getGroupId(),
                debt.getDebtor().getMemberId(),
                debt.getCreditor().getMemberId(),
                debt.getStatus(),
                debt.getDescription()
        );
    }
}

