package com.example.RecyclingApp.entity;

import com.example.RecyclingApp.dto.CommunityDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="community_table")
public class CommunityEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String firstname;

    @Column
    private String title;

    @Column(length = 500, nullable = false)
    private String textarea;

    @Column
    private int views;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommunityFileEntity> communityFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static CommunityEntity toSaveEntity(CommunityDTO communityDTO){
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setFirstname(communityDTO.getFirstname());
        communityEntity.setTitle(communityDTO.getTitle());
        communityEntity.setTextarea(communityDTO.getTextarea());
        communityEntity.setViews(0);
        communityEntity.setFileAttached(0);
        return communityEntity;
    }

    public static CommunityEntity toUpdateEntity(CommunityDTO communityDTO) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setId(communityDTO.getId());
        communityEntity.setFirstname(communityDTO.getFirstname());
        communityEntity.setTitle(communityDTO.getTitle());
        communityEntity.setTextarea(communityDTO.getTextarea());
        communityEntity.setViews(communityDTO.getViews());
        return communityEntity;
    }

    public static CommunityEntity toSaveFileEntity(CommunityDTO communityDTO) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setFirstname(communityDTO.getFirstname());
        communityEntity.setTitle(communityDTO.getTitle());
        communityEntity.setTextarea(communityDTO.getTextarea());
        communityEntity.setViews(0);
        communityEntity.setFileAttached(1);
        return communityEntity;
    }
}
