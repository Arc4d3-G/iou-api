package com.example.iou_api.repository;

import com.example.iou_api.model.Group;
import com.example.iou_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByCreatedBy(User createdBy);

    Optional<Group> findByNameAndCreatedById(String name, Integer createdById);
}
