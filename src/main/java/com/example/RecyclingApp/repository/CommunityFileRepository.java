package com.example.RecyclingApp.repository;

import com.example.RecyclingApp.entity.CommunityFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityFileRepository extends JpaRepository<CommunityFileEntity, Long> {
}
