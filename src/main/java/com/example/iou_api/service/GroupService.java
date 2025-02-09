package com.example.iou_api.service;

import com.example.iou_api.dto.GroupDTO;
import com.example.iou_api.model.Group;
import com.example.iou_api.model.GroupMember;
import com.example.iou_api.model.Role;
import com.example.iou_api.model.User;
import com.example.iou_api.repository.GroupMemberRepository;
import com.example.iou_api.repository.GroupRepository;
import com.example.iou_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository, GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public GroupDTO createGroup(GroupDTO groupDTO) {
        if (groupDTO.getCreatedById() == null){
            throw new IllegalArgumentException("createdBy.id cannot be null");
        }

        // Check for existing group with same name + user
        Optional<Group> existingGroup = groupRepository.findByNameAndCreatedById(groupDTO.getName(), groupDTO.getCreatedById());
        if(existingGroup.isPresent()){
            throw new IllegalArgumentException("A group with the same name and owner already exists.");
        }

        // Fetch the User from the database
        User createdBy = userRepository.findById(groupDTO.getCreatedById())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + groupDTO.getCreatedById() + " not found."));

        // Save to DB
        Group group = new Group(groupDTO.getName(), createdBy);
        Group savedGroup = groupRepository.save(group);

        // Create Group Member
        GroupMember adminMember = new GroupMember(savedGroup, createdBy, createdBy.getName(), Role.ADMIN);
        groupMemberRepository.save(adminMember);

        // Convert back to DTO and return
        return new GroupDTO(savedGroup.getName(), savedGroup.getCreatedBy().getId(), savedGroup.getGroupId());
    }

    public Optional<Group> getGroupById(Integer groupId) {
        return groupRepository.findById(groupId);
    }

    public List<Group> getGroupsByCreatedBy(User createdBy) {
        return groupRepository.findByCreatedBy(createdBy);
    }
}
