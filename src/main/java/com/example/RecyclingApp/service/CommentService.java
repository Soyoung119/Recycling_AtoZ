package com.example.RecyclingApp.service;

import com.example.RecyclingApp.dto.CommentDTO;
import com.example.RecyclingApp.entity.CommentEntity;
import com.example.RecyclingApp.entity.CommunityEntity;
import com.example.RecyclingApp.repository.CommentRepository;
import com.example.RecyclingApp.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<CommunityEntity> optionalCommunityEntity = communityRepository.findById(commentDTO.getCommunityId());
        if(optionalCommunityEntity.isPresent()){
            CommunityEntity communityEntity = optionalCommunityEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, communityEntity);
            return commentRepository.save(commentEntity).getId();
        } else{
            return null;
        }
    }

    public List<CommentDTO> findAll(Long communityId) {
        CommunityEntity communityEntity = communityRepository.findById(communityId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByCommunityEntityOrderByIdDesc(communityEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, communityId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
