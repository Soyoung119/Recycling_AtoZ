package com.example.RecyclingApp.service;

import com.example.RecyclingApp.dto.MemberDTO;
import com.example.RecyclingApp.entity.MemberEntity;
import com.example.RecyclingApp.mapper.MemberMapper;
import com.example.RecyclingApp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    public void save(MemberDTO memberDTO){
        MemberEntity memberEntity = memberMapper.toEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO signin(MemberDTO memberDTO) {
        Optional<MemberEntity> byEmailAddress = memberRepository.findByEmailAddress(memberDTO.getEmailAddress());
        if(byEmailAddress.isPresent()){
            MemberEntity memberEntity = byEmailAddress.get();
            logger.debug("Login successful");
            if(memberEntity.getPassword().equals(memberDTO.getPassword())){
            MemberDTO dto = MemberMapper.toDTO(memberEntity);
            return dto;
            } else{
                logger.warn("Invalid Password");
                return null;
            }
        } else{
            logger.warn("Invalid Email");
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList){
            memberDTOList.add(MemberMapper.toDTO(memberEntity));
//            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            return MemberMapper.toDTO(optionalMemberEntity.get());
        } else{
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmailAddress(myEmail);
        if(optionalMemberEntity.isPresent()){
            return MemberMapper.toDTO(optionalMemberEntity.get());
        } else{
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        memberEntity.setFirstname(memberDTO.getFirstname());
        memberEntity.setLastname(memberDTO.getLastname());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setCity(memberDTO.getCity());
        memberEntity.setCounty(memberDTO.getCounty());
        memberRepository.save(memberEntity);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
