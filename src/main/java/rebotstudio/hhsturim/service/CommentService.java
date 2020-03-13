package rebotstudio.hhsturim.service;

import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.Comment;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.mapper.CommentMapper;
import rebotstudio.hhsturim.repository.CommentRepository;
import rebotstudio.hhsturim.repository.UserRepository;
import rebotstudio.hhsturim.vo.CommentVo;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<CommentVo> get(Integer pid){          // 获取一个地点的所有评论
        List<Comment> byPid = commentRepository.findByPid(pid);
        List<CommentVo> commentVos = CommentMapper.toVoList(byPid);
        for (CommentVo commentVo: commentVos){
            Integer uid = commentVo.getUid();
            User user = userRepository.getOne(uid);
            if(user!=null){
                commentVo.setUsername(user.getUsername());
            }
        }
        return commentVos;
    }

    public void addComment(Integer uid,Integer pid,String c){       // 创建一个评论
        Comment comment=new Comment();
        comment.setUid(uid);
        comment.setPid(pid);
        comment.setContent(c);
        comment.setCreateTime(new Date());
        commentRepository.save(comment);
    }

    public void deleteById(Long id){            // 删除一个评论
        Comment one = commentRepository.getOne(id);
        commentRepository.delete(one);
    }

    public void updateComment(Long id,String c){        // 更新评论内容
        Comment one = commentRepository.getOne(id);
        one.setContent(c);
        commentRepository.save(one);
    }


}


