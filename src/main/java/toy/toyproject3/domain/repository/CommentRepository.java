package toy.toyproject3.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.toyproject3.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
