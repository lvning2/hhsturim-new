package rebotstudio.hhsturim.mapper;

import rebotstudio.hhsturim.entity.Comment;
import rebotstudio.hhsturim.vo.CommentVo;
import rebotstudio.hhsturim.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static CommentVo toVo(Comment comment){
        return CGlibMapper.mapper(comment, CommentVo.class);
    }

    public static List<CommentVo> toVoList(List<Comment> comments){
        List<CommentVo> commentVos=new ArrayList<>();
        for (Comment comment : comments){
            CommentVo mapper = CGlibMapper.mapper(comment, CommentVo.class);
            commentVos.add(mapper);
        }
        return commentVos;
    }


}
