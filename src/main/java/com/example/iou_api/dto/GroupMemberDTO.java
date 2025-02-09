package com.example.iou_api.dto;

import com.example.iou_api.model.Role;

public class GroupMemberDTO {
    private Integer userId;
    private Integer groupId;
    private Role role;
    private String nickname;
    private Integer memberId;

    public GroupMemberDTO() {
    }

    // Receiving
    public GroupMemberDTO(Integer userId, Integer groupId, Role role, String nickname) {
        this.userId = userId;
        this.groupId = groupId;
        this.role = role;
        this.nickname = nickname;
    }

    // Sending
    public GroupMemberDTO(Integer userId, Integer groupId, Role role, String nickname, Integer memberId) {
        this.userId = userId;
        this.groupId = groupId;
        this.role = role;
        this.nickname = nickname;
        this.memberId = memberId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;

    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
