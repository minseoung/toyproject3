package toy.toyproject3.domain.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import toy.toyproject3.domain.entity.Board;
import toy.toyproject3.domain.entity.QBoard;
import toy.toyproject3.domain.entity.QMember;

import java.util.List;

import static toy.toyproject3.domain.entity.QBoard.*;
import static toy.toyproject3.domain.entity.QMember.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Board> findBoards(String title, String writer) {
        return queryFactory
                .select(board)
                .from(board)
                .orderBy(board.lastModifiedDate.desc())
                .join(board.member, member)
                .fetchJoin()
                .where(titleEq(title), writerEq(writer))
                .fetch();
    }

    private Predicate titleEq(String title) {
        return StringUtils.hasText(title) ? board.title.contains(title) : null;
    }

    private Predicate writerEq(String writer) {
        return StringUtils.hasText(writer) ? board.member.loginid.contains(writer) : null;
    }
}
