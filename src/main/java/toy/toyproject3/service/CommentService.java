package toy.toyproject3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Comment;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.domain.repository.CommentRepository;
import toy.toyproject3.domain.repository.MemberRepository;
import toy.toyproject3.web.dto.CommentAddRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void addComment(CommentAddRequest request, Long boardId, Long loginMemberId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Optional<Member> optionalMember = memberRepository.findById(loginMemberId);
        if (optionalBoard.isPresent() && optionalMember.isPresent()) {
            Board board = optionalBoard.get();
            Member member = optionalMember.get();
            Comment comment = new Comment(request.getContent(), board, member);
            commentRepository.save(comment);
        } else {
            throw new RuntimeException("잘못된 요청입니다.");
        }
    }
}
