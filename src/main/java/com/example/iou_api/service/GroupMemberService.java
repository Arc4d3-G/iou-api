package com.example.iou_api.service;

import com.example.iou_api.model.GroupMember;
import com.example.iou_api.repository.GroupMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GroupMemberService {
    private final GroupMemberRepository groupMemberRepository;

    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    public GroupMember addMemberToGroup(GroupMember member) {
        return groupMemberRepository.save(member);
    }

    public List<GroupMember> getMembersByGroupId(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }

    public void removeMember(Long memberId) {
        groupMemberRepository.deleteById(memberId);
    }
}

