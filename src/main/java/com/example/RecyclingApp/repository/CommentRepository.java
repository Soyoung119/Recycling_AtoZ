package com.example.RecyclingApp.repository;

import com.example.RecyclingApp.entity.CommentEntity;
import com.example.RecyclingApp.entity.CommunityEntity;
import com.example.RecyclingApp.entity.CommunityFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByCommunityEntityOrderByIdDesc(CommunityEntity communityEntity);
}
