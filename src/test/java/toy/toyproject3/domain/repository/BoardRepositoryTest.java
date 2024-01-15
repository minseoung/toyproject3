package toy.toyproject3.domain.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired BoardRepository boardRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("게시물 올리기")
    void post() {
        //given
        Member member = new Member("최민성", 22, "chlalstjd", "1234");
        memberRepository.save(member);

        Board board = new Board("aa", "aa", member);
        boardRepository.save(board);
        //when
        Board findBoard = boardRepository.findById(board.getId()).get();
        //then
        assertThat(findBoard.getId()).isEqualTo(board.getId());
    }

    @Test
    @DisplayName("게시글 목록")
    void list() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Board> boards = boardRepository.findBoards(null, null);
        System.out.println(boards.size());
    }

//    @Test
//    @DisplayName("게시글 가져오기")
//    void findBoards() {
//        Member member = new Member("최민성", 22, "chlalstjd", "1234");
//        memberRepository.save(member);
//
//        Board board = new Board()
//        boardRepository.save();
//    }
}