package toy.toyproject3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.domain.repository.MemberRepository;
import toy.toyproject3.exception.BoardNotFoundException;
import toy.toyproject3.exception.MemberNotFound;
import toy.toyproject3.web.dto.BoardAddRequest;
import toy.toyproject3.web.dto.BoardResponse;
import toy.toyproject3.web.dto.BoardsRequest;
import toy.toyproject3.web.dto.BoardsResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long post(BoardAddRequest addRequest, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Board board = new Board(addRequest.getTitle(), addRequest.getContent(), member);
            boardRepository.save(board);
            return board.getId();
        } else {
            throw new MemberNotFound("존재하지 않는 회원입니다.");
        }
    }
    public List<BoardsResponse> findBoards(BoardsRequest boardsRequest) {
        List<Board> boards = boardRepository.findBoards(boardsRequest.getTitle(), boardsRequest.getWriter());
        List<BoardsResponse> boardsDto = boards.stream().map(board -> new BoardsResponse(board)).collect(Collectors.toList());
        return boardsDto;
    }

    public BoardResponse findBord(Long boardId, Long loginMemberId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            BoardResponse boardResponse = new BoardResponse(board, loginMemberId);
            return boardResponse;
        } else {
            throw new BoardNotFoundException("해당 게시물을 찾을 수 없습니다.");
        }
    }
}
