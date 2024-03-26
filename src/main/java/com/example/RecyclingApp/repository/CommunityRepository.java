package com.example.RecyclingApp.repository;

import com.example.RecyclingApp.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    @Modifying
    @Query(value = "update CommunityEntity c set c.views=c.views+1 where c.id=:id")
    void updateViews(@Param("id")Long id);
}
