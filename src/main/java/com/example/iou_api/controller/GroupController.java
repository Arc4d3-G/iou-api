package com.example.iou_api.controller;

import com.example.iou_api.dto.GroupDTO;
import com.example.iou_api.model.Group;
import com.example.iou_api.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/iou/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Create new group
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupDTO groupDTO) {
        try {
            GroupDTO createdGroup = groupService.createGroup(groupDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Find Group by id
    @GetMapping("/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Integer groupId) {
        Optional<Group> user = groupService.getGroupById(groupId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
