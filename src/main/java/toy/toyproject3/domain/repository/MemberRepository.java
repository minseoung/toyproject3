package toy.toyproject3.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.toyproject3.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //로그인 아이디로 회원 찾기
    Optional<Member> findByLoginid(String loginid);

}
