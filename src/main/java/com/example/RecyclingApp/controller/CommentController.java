package com.example.RecyclingApp.controller;

import com.example.RecyclingApp.dto.CommentDTO;
import com.example.RecyclingApp.repository.CommentRepository;
import com.example.RecyclingApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recycling/community/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO){
        System.out.println("commentDTO = " + commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if(saveResult!=null){
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getCommunityId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("It doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
