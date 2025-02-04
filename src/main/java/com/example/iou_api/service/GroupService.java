package com.example.iou_api.service;

import com.example.iou_api.model.Group;
import com.example.iou_api.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public List<Group> getGroupsByUserId(Long userId) {
        return groupRepository.findByCreatedBy(userId);
    }
}
