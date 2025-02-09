package com.example.iou_api.dto;

public class GroupDTO {
    private String name;

    private Integer createdById;

    private Integer groupId;

    public GroupDTO() {}

    // Receiving constructor
    public GroupDTO(String name, Integer createdById) {
        this.name = name;
        this.createdById = createdById;
    }

    // Sending constructor
    public GroupDTO(String name, Integer createdById, Integer groupId) {
        this.name = name;
        this.createdById = createdById;
        this.groupId = groupId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
