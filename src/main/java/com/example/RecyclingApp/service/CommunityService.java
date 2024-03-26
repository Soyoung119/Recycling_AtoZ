package com.example.RecyclingApp.service;

import com.example.RecyclingApp.dto.CommunityDTO;
import com.example.RecyclingApp.entity.CommunityEntity;
import com.example.RecyclingApp.entity.CommunityFileEntity;
import com.example.RecyclingApp.repository.CommunityFileRepository;
import com.example.RecyclingApp.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityFileRepository communityFileRepository;

    public void save(CommunityDTO communityDTO) throws IOException {
        if(communityDTO.getCommunityFile().isEmpty()){
            CommunityEntity communityEntity = CommunityEntity.toSaveEntity(communityDTO);
            communityRepository.save(communityEntity);
        } else{
            CommunityEntity communityEntity = CommunityEntity.toSaveFileEntity(communityDTO);
            Long savedId = communityRepository.save(communityEntity).getId();
            CommunityEntity community = communityRepository.findById(savedId).get();
            for(MultipartFile communityFile: communityDTO.getCommunityFile()) {
//                MultipartFile communityFile = communityDTO.getCommunityFile();
                String originalFilename = communityFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "" + originalFilename;
                String savePath = "/Users/soyoungahn/Documents/Final Project/RecylingApp/src/main/resources/static/userUploaded/" + storedFileName;
                communityFile.transferTo(new File(savePath));
                CommunityFileEntity communityFileEntity = CommunityFileEntity.toCommunityFileEntity(community, originalFilename, storedFileName);
                communityFileRepository.save(communityFileEntity);
            }
        }

    }
    @Transactional
    public List<CommunityDTO> findAll() {
        List<CommunityEntity> communityEntityList = communityRepository.findAll();
        List<CommunityDTO> communityDTOList = new ArrayList<>();
        for(CommunityEntity communityEntity: communityEntityList){
            communityDTOList.add(CommunityDTO.toCommunityDTO(communityEntity));
        }
        return communityDTOList;
    }

    @Transactional
    public void updateViews(Long id) {
        communityRepository.updateViews(id);
    }
    @Transactional
    public CommunityDTO findById(Long id){
        Optional<CommunityEntity> optionalCommunityEntity = communityRepository.findById(id);
        if(optionalCommunityEntity.isPresent()){
            CommunityEntity communityEntity = optionalCommunityEntity.get();
            CommunityDTO communityDTO = CommunityDTO.toCommunityDTO(communityEntity);
            return communityDTO;
        } else {
            return null;
        }
    }

    public CommunityDTO update(CommunityDTO communityDTO) {
        CommunityEntity communityEntity = CommunityEntity.toUpdateEntity(communityDTO);
        communityRepository.save(communityEntity);
        return findById(communityDTO.getId());
    }

    public void delete(Long id) {
        communityRepository.deleteById(id);
    }

    public Page<CommunityDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 5;
        Page<CommunityEntity> communityEntities =
        communityRepository.findAll(PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.DESC,"id")));

        System.out.println("communityEntities.getContent() = " + communityEntities.getContent());
        System.out.println("communityEntities.getTotalElements() = " + communityEntities.getTotalElements());
        System.out.println("communityEntities.getNumber() = " + communityEntities.getNumber());
        System.out.println("communityEntities.getTotalPages() = " + communityEntities.getTotalPages());
        System.out.println("communityEntities.getSize() = " + communityEntities.getSize());
        System.out.println("communityEntities.hasPrevious() = " + communityEntities.hasPrevious());
        System.out.println("communityEntities.isFirst() = " + communityEntities.isFirst());
        System.out.println("communityEntities.isLast() = " + communityEntities.isLast());

        Page<CommunityDTO> communityDTOS = communityEntities.map(community -> new CommunityDTO(community.getId(), community.getFirstname(), community.getTitle(), community.getViews(), community.getCreatedTime()));
        return communityDTOS;
    }
}
