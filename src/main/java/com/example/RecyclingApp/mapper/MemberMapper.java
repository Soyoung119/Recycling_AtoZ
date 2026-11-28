package com.example.RecyclingApp.mapper;

import com.example.RecyclingApp.dto.MemberDTO;
import com.example.RecyclingApp.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberEntity toEntity(MemberDTO memberDTO) {
        return new MemberEntity(
                memberDTO.getId(),
                memberDTO.getFirstname(),
                memberDTO.getLastname(),
                memberDTO.getEmailAddress(),
                memberDTO.getPassword(),
                memberDTO.getCity(),
                memberDTO.getCounty()
        );
    }

    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setFirstname(memberEntity.getFirstname());
        memberDTO.setLastname(memberEntity.getLastname());
        memberDTO.setEmailAddress(memberEntity.getEmailAddress());
        memberDTO.setCity(memberEntity.getCity());
        memberDTO.setCounty(memberEntity.getCounty());
        return memberDTO;
    }
}
