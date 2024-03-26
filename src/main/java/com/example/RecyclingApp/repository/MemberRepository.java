package com.example.RecyclingApp.repository;

import com.example.RecyclingApp.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // find member's details by email (Select * from member_table where emailAddress=?)
    Optional<MemberEntity> findByEmailAddress(String emailAddress);

}