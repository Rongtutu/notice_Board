package com.example.noticesboard.serivce;

import com.example.noticesboard.adapter.MemberAdapter;
import com.example.noticesboard.entity.Board;
import com.example.noticesboard.entity.dto.BoardDto;
import com.example.noticesboard.repository.BoardRepository;
import com.example.noticesboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    public void writeBoard(BoardDto boardDto,
                            @AuthenticationPrincipal MemberAdapter memberAdapter,
                            Principal principal
                            ) {

        LocalDateTime currentTime = LocalDateTime.now();

        Board board = Board.builder()
                .writer(principal.getName())
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .localDateTime(currentTime)
                .build();

        boardRepository.save(board);
    }

    public List<Board> listBoard() {
        return boardRepository.findAll();
    }
    public Optional<Board> view(String id) {
       return boardRepository.findById(id);
    }

    public void modify(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).orElse(null);
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        boardRepository.save(board);
    }

    public void del(String id) {
        Optional<Board> board = boardRepository.findById(id);
        boardRepository.delete(board.get());
    }
}
