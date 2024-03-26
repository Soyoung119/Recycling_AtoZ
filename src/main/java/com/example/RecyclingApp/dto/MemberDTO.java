package com.example.RecyclingApp.dto;

import com.example.RecyclingApp.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String password;
    private String confirmPassword;
    private String city;
    private String county;

    public MemberDTO(Long id, String firstname, String lastname, String emailAddress, String password, String confirmPassword, String city, String county) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.city = city;
        this.county = county;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                '}';
    }

    public MemberEntity toEntity() {
        return new MemberEntity(id, firstname, lastname, emailAddress, password, confirmPassword, city, county);
    }

    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setFirstname(memberEntity.getFirstname());
        memberDTO.setLastname(memberEntity.getLastname());
        memberDTO.setEmailAddress(memberEntity.getEmailAddress());
        memberDTO.setConfirmPassword(memberEntity.getConfirmPassword());
        memberDTO.setCity(memberEntity.getCity());
        memberDTO.setCounty(memberEntity.getCounty());
        return memberDTO;
    }


}
