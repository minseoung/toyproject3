package toy.toyproject3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.domain.repository.CommentRepository;
import toy.toyproject3.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
}
