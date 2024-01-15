package toy.toyproject3;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.Member;
import toy.toyproject3.domain.repository.BoardRepository;
import toy.toyproject3.domain.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        Member member1 = new Member("유재석", 52, "dbwotjr52", "1234");
        Member member2 = new Member("박명수", 54, "qkraudtn54", "1234");
        Member member3 = new Member("정준하", 53, "wjdwnsgk53", "1234");
        Member member4 = new Member("정형돈", 47, "wjdgudehs47", "1234");
        Member member5 = new Member("하동훈", 44, "gkehdgns44", "1234");
        Member member6 = new Member("노홍철", 44, "shghdcjf44", "1234");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);

        for (int i = 0; i < 10; i++) {
            Board board1 = new Board("유재석의 출석체크" + (i+1), "안녕하세요~!", member1);
            Board board2 = new Board("박명수의 출석체크" + (i+1), "안녕하세요~!", member2);
            Board board3 = new Board("정준하의 출석체크" + (i+1), "안녕하세요~!", member3);
            Board board4 = new Board("정형돈의 출석체크" + (i+1), "안녕하세요~!", member4);
            Board board5 = new Board("하동훈의 출석체크" + (i+1), "안녕하세요~!", member5);
            Board board6 = new Board("노홍철의 출석체크" + (i+1), "안녕하세요~!", member6);

            boardRepository.save(board1);
            boardRepository.save(board2);
            boardRepository.save(board3);
            boardRepository.save(board4);
            boardRepository.save(board5);
            boardRepository.save(board6);
        }
    }
}
