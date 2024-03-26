package com.example.RecyclingApp.entity;


import com.example.RecyclingApp.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(unique = true)
    private String emailAddress;

    @Column
    private String password;

    @Column
    private String confirmPassword;

    @Column
    private String city;

    @Column
    private String county;

    public Long getId() {
        return id;
    }

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setFirstname(memberDTO.getFirstname());
        memberEntity.setLastname(memberDTO.getLastname());
        memberEntity.setEmailAddress(memberDTO.getEmailAddress());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setConfirmPassword(memberDTO.getConfirmPassword());
        memberEntity.setCity(memberDTO.getCity());
        memberEntity.setCounty(memberDTO.getCounty());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setFirstname(memberDTO.getFirstname());
        memberEntity.setLastname(memberDTO.getLastname());
        memberEntity.setEmailAddress(memberDTO.getEmailAddress());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setConfirmPassword(memberDTO.getConfirmPassword());
        memberEntity.setCity(memberDTO.getCity());
        memberEntity.setCounty(memberDTO.getCounty());
        return memberEntity;
    }
}
