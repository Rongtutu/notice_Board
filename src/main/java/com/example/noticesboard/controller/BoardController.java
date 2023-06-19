package com.example.noticesboard.controller;

import com.example.noticesboard.adapter.MemberAdapter;
import com.example.noticesboard.entity.Board;
import com.example.noticesboard.entity.dto.BoardDto;
import com.example.noticesboard.serivce.BoardService;
import com.example.noticesboard.serivce.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/main")
    public String main(Model model) {
        List<Board> list = boardService.listBoard();

        model.addAttribute("list", list);
        return "/main";
    }

    @GetMapping("/write")
    public String write() {
        return "/write";
    }

    @PostMapping("/writeOk")
    public String writeOk(BoardDto boardDto,
                          @AuthenticationPrincipal MemberAdapter memberAdapter,
                          Principal principal) {
        boardService.writeBoard(boardDto, memberAdapter, principal);

        return "redirect:/main";
    }

    @GetMapping("/view")
    public String view(@RequestParam String id, Model model, @AuthenticationPrincipal MemberAdapter memberAdapter) {
        model.addAttribute("board", boardService.view(id));
        model.addAttribute("user", memberAdapter.getMember());
        return "/view";
    }

    @PostMapping("/modify")
    public String modify(Long id, Model model) {
        String idx = id.toString();
        model.addAttribute("board", boardService.view(idx));
        return "/modify";
    }

    @PostMapping("/modifyOk")
    public String modifyOk(BoardDto boardDto) {
        boardService.modify(boardDto);
        return "redirect:/main";
    }


    @PostMapping("/del")
    public String del(Long id) {
        String idx = id.toString();
        boardService.del(idx);
        return "redirect:/main";
    }



}
