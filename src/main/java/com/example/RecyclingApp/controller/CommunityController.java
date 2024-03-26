package com.example.RecyclingApp.controller;

import com.example.RecyclingApp.dto.CommentDTO;
import com.example.RecyclingApp.dto.CommunityDTO;
import com.example.RecyclingApp.repository.CommunityRepository;
import com.example.RecyclingApp.service.CommentService;
import com.example.RecyclingApp.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recycling/community")
public class CommunityController {
    private final CommunityService communityService;
    private final CommentService commentService;

//    @GetMapping("")
//    public String community(){
//        return "community/community";
//    }

    @GetMapping("save")
    public String saveForm(){
        return "community/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute CommunityDTO communityDTO) throws IOException {
        System.out.println("communityDTO = " + communityDTO);
        communityService.save(communityDTO);
        return "redirect:/recycling/community";
    }

//    @GetMapping("")
//    public String findAll(Model model){
//        List<CommunityDTO> communityDTOList = communityService.findAll();
//        model.addAttribute("communityList", communityDTOList);
//        return "community/community";
//    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable){
        communityService.updateViews(id);
        CommunityDTO communityDTO = communityService.findById(id);
        List<CommentDTO> commentDTOList = commentService.findAll(id);

        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("community", communityDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "community/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        CommunityDTO communityDTO = communityService.findById(id);
        model.addAttribute("communityUpdate", communityDTO);
        return  "community/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute CommunityDTO communityDTO, Model model){
        CommunityDTO community = communityService.update(communityDTO);
        model.addAttribute("community", community);
        return "community/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        communityService.delete(id);
        return "redirect:/recycling/community";
    }

    @GetMapping("")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
//        pageable.getPageNumber();
        Page<CommunityDTO> communityList = communityService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit)))-1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit -1) < communityList.getTotalPages()) ? startPage + blockLimit - 1 : communityList.getTotalPages();

        model.addAttribute("communityList", communityList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "community/paging";
    }
}
