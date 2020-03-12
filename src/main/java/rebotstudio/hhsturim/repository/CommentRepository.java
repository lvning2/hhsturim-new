package rebotstudio.hhsturim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rebotstudio.hhsturim.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPid(Integer pid);

}
