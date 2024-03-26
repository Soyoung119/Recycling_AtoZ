package com.example.RecyclingApp.dto;

import com.example.RecyclingApp.entity.CommunityEntity;
import com.example.RecyclingApp.entity.CommunityFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private Long id;
    private String firstname;
    private String title;
    private String textarea;
    private int views;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private List<MultipartFile> communityFile; // save.html to controller
    private List<String> originalFileName;
    private List<String> storedFileName;
    private int fileAttached; //(Attached? 1Y: , 0:N)

    public CommunityDTO(Long id, String firstname, String title, int views, LocalDateTime createdTime) {
        this.id = id;
        this.firstname = firstname;
        this.title = title;
        this.views = views;
        this.createdTime = createdTime;
    }

    public static CommunityDTO toCommunityDTO(CommunityEntity communityEntity){
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(communityEntity.getId());
        communityDTO.setFirstname(communityEntity.getFirstname());
        communityDTO.setTitle(communityEntity.getTitle());
        communityDTO.setTextarea(communityEntity.getTextarea());
        communityDTO.setViews(communityEntity.getViews());
        communityDTO.setCreatedTime(communityEntity.getCreatedTime());
        communityDTO.setUpdatedTime(communityEntity.getUpdatedTime());
        if(communityEntity.getFileAttached() == 0){
            communityDTO.setFileAttached(communityEntity.getFileAttached());
        } else{
            List<String> origianlFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            communityDTO.setFileAttached(communityEntity.getFileAttached());
//            communityDTO.setOriginalFileName(communityEntity.getCommunityFileEntityList().get(0).getOriginalFileName());
//            communityDTO.setStoredFileName(communityEntity.getCommunityFileEntityList().get(0).getOriginalFileName());
            for(CommunityFileEntity communityFileEntity : communityEntity.getCommunityFileEntityList()){
                origianlFileNameList.add(communityFileEntity.getOriginalFileName());
                storedFileNameList.add(communityFileEntity.getStoredFileName());
            }
            communityDTO.setOriginalFileName(origianlFileNameList);
            communityDTO.setStoredFileName(storedFileNameList);

        }
        return communityDTO;
    }
}
