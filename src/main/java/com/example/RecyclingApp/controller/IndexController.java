package com.example.RecyclingApp.controller;

import com.example.RecyclingApp.dto.MemberDTO;
import com.example.RecyclingApp.entity.MemberEntity;
import com.example.RecyclingApp.repository.MemberRepository;
import com.example.RecyclingApp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService;

    @GetMapping("/recycling/index")
    public String index(){
        return "index";
    }

    @GetMapping("/recycling/articles/signup")
    public String signupForm(){
        return "articles/signup";
    }

    @PostMapping("/recycling/articles/create")
    public String signup(@ModelAttribute MemberDTO memberDTO){
        System.out.println(memberDTO.toString());
        memberService.save(memberDTO);
        return "articles/signin";
    }

    @GetMapping("/recycling/articles/signin")
    public String signinForm(){
        return "articles/signin";
    }

    @PostMapping("/recycling/articles/signin")
    public String signin(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){
        log.info("Try to sign-in: {}", memberDTO.toString());
        MemberDTO loginResult = memberService.signin(memberDTO);
        if(loginResult !=null){
            log.info("Login successfully. Redirect to /recycling/userindex");
            session.setAttribute("user", loginResult.getEmailAddress());
            session.setAttribute("firstname", loginResult.getFirstname());
            return "redirect:/recycling/userIndex";
        } else{
            log.info("Login failed. Return to /articles/signin");
            model.addAttribute("error", "Login failed. Try again! ");
            return "articles/signin";
        }
    }

    @GetMapping("/recycling/userIndex")
    public String userIndex(Model model){
        model.addAttribute("username","user");
        return "userIndex";
    }

    @GetMapping("/recycling/articles/admin/list")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "articles/list";
    }

    @GetMapping("/recycling/articles/admin/list/{id}")
    public String findById(@PathVariable Long id,Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "articles/detail";
    }

    @GetMapping("/recycling/articles/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("user");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "articles/update";
    }

    @PostMapping("/recycling/articles/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/recycling/articles/admin/list/" + memberDTO.getId();
    }

    @GetMapping("/recycling/articles/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        memberService.deleteById(id);
        rttr.addFlashAttribute("msg", "Deleted successfully!");
        return "redirect:/recycling/articles/admin/list";
    }

    @GetMapping("/recycling/logout")
        public String logout(HttpSession session){
            session.invalidate();
            return "userIndex";
        }

    @GetMapping("/recycling/items/vapes")
    public String vapes(){
        return "items/vapes";
    }

    @GetMapping("/recycling/items/plasticBottles")
    public String plasticBottles(){
        return "items/plasticBottles";
    }

    @GetMapping("/recycling/items/paper")
    public String paper(){
        return "items/paper";
    }

    @GetMapping("/recycling/items/foil")
    public String foil(){
        return "items/foil";
    }

    @GetMapping("/recycling/items/cardboard")
    public String cardboard(){
        return "items/cardboard";
    }

    @GetMapping("/recycling/items/drinkCans")
    public String drinkCans(){
        return "items/drinkCans";
    }

    @GetMapping("/recycling/items/batteries")
    public String batteries(){
        return "items/batteries";
    }

    @GetMapping("/recycling/items/glass")
    public String glass(){
        return "items/glass";
    }

    @GetMapping("/recycling/items/moreItems")
    public String moreItems(){
        return "items/moreItems";
    }

    @GetMapping("/recycling/about")
    public String about(){
        return "about";
    }

}