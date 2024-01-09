package toy.toyproject3.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toyproject3.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
