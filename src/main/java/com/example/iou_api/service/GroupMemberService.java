package com.example.iou_api.service;

import com.example.iou_api.dto.GroupMemberDTO;
import com.example.iou_api.model.Group;
import com.example.iou_api.model.GroupMember;
import com.example.iou_api.model.User;
import com.example.iou_api.repository.GroupMemberRepository;
import com.example.iou_api.repository.GroupRepository;
import com.example.iou_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupMemberService {
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public GroupMemberDTO addMemberToGroup(GroupMemberDTO memberDTO) {
        if (memberDTO.getUserId() == null){
            throw new IllegalArgumentException("createdBy.id cannot be null");
        }

        // Check for existing group member with same groupId + userId
        Optional<GroupMember> existingGroupMember = groupMemberRepository.findByGroup_GroupIdAndUser_Id(memberDTO.getGroupId(), memberDTO.getUserId());
        if(existingGroupMember.isPresent()){
            throw new IllegalArgumentException("User with id is already a member of group id");
        }

        // Fetch the User from the database
        User existingUser = userRepository.findById(memberDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + memberDTO.getUserId() + " not found."));

        // Fetch the Group from the database
        Group existingGroup = groupRepository.findById(memberDTO.getGroupId())
                .orElseThrow(() -> new IllegalArgumentException("Group with ID " + memberDTO.getGroupId() + " not found."));


        // Create Group Member
        GroupMember groupMember = new GroupMember(existingGroup, existingUser, memberDTO.getNickname(), memberDTO.getRole());
        GroupMember savedGroupMember = groupMemberRepository.save(groupMember);

        // Convert back to DTO and return
        return new GroupMemberDTO(savedGroupMember.getUser().getId(), savedGroupMember.getGroup().getGroupId(), savedGroupMember.getRole(), savedGroupMember.getUserNickname(), savedGroupMember.getMemberId());
    }

    public List<GroupMember> getMembersByGroupId(Integer groupId) {
        return groupMemberRepository.findByGroup_GroupId(groupId);
    }

    public void removeMember(Integer memberId) {
        groupMemberRepository.deleteById(memberId);
    }
}

