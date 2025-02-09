package com.example.iou_api.repository;

import com.example.iou_api.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    List<GroupMember> findByGroup_GroupId(Integer groupId);

    Optional<GroupMember> findByGroup_GroupIdAndUser_Id(Integer groupId, Integer userId);

    Optional<GroupMember> findByGroup_GroupIdAndMemberId(Integer groupId, Integer memberId);
}
