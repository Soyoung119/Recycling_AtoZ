package com.example.RecyclingApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "community_file_table")
public class CommunityFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private CommunityEntity communityEntity;

    public static CommunityFileEntity toCommunityFileEntity(CommunityEntity communityEntity, String originalFileName, String storedFileName){
        CommunityFileEntity communityFileEntity = new CommunityFileEntity();
        communityFileEntity.setOriginalFileName(originalFileName);
        communityFileEntity.setStoredFileName(storedFileName);
        communityFileEntity.setCommunityEntity(communityEntity);
        return communityFileEntity;
    }
}
