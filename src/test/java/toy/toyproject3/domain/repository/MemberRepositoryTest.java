package toy.toyproject3.domain.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.toyproject3.domain.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("기본 CRUD 작동 확인")
    void crud() {
        //given
        Member member1 = new Member("test1", 1, "test1", "1111");
        Member member2 = new Member("test2", 1, "test2", "2222");
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();
        //when
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAll();
        em.flush();
        em.clear();
        //then
        assertThat(findMember1.getId()).isEqualTo(member1.getId());
        assertThat(findMember2.getId()).isEqualTo(member2.getId());
        assertThat(members.size()).isEqualTo(2);

        memberRepository.deleteById(findMember1.getId());
        List<Member> members2 = memberRepository.findAll();

        assertThat(members2.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("로그인 아이디로 회원 찾기")
    void findByloginid() {
        //given
        Member member1 = new Member("test1", 1, "test1", "1111");
        memberRepository.save(member1);
        //when
        Member findMember = memberRepository.findByLoginid("test1").get();
        //then
        assertThat(findMember.getId()).isEqualTo(member1.getId());
    }
}