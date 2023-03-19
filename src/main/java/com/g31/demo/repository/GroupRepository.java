package com.g31.demo.repository;

import com.g31.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 */

public interface GroupRepository extends JpaRepository<Group, Long> {

//    void loadGroup();
    Group getByGroupId(long groupId);
}
