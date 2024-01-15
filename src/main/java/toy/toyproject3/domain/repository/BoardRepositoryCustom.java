package toy.toyproject3.domain.repository;

import org.springframework.data.domain.Pageable;
import toy.toyproject3.domain.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findBoards(String title, String writer);
}
