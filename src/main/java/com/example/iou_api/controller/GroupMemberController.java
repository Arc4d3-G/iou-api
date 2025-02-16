package com.example.iou_api.controller;

import com.example.iou_api.dto.GroupMemberDTO;
import com.example.iou_api.model.GroupMember;
import com.example.iou_api.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/iou/group-members")
public class GroupMemberController {
    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    // Add group member (create)
    @PostMapping
    public ResponseEntity<?> addMemberToGroup(@RequestBody GroupMemberDTO groupMemberDTO) {
        try {
            GroupMemberDTO createdGroupMember = groupMemberService.addMemberToGroup(groupMemberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupMemberDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<GroupMember>> getMembersByGroupId(@PathVariable Integer groupId) {
        List<GroupMember> members = groupMemberService.getMembersByGroupId(groupId);
        return ResponseEntity.ok(members);
    }



}
