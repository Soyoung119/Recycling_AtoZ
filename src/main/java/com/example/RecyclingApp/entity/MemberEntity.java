package com.example.RecyclingApp.entity;

import lombok.*;
import jakarta.persistence.*;

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

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String emailAddress;

    private String password;

    private String city;

    private String county;
}
