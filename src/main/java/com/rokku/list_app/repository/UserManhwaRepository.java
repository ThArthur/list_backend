package com.rokku.list_app.repository;

import com.rokku.list_app.models.UserManhwa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManhwaRepository extends JpaRepository<UserManhwa, Long> {
    List<UserManhwa> findByUserId(Long userId);
}