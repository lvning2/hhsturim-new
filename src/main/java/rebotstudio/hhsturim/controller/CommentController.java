package rebotstudio.hhsturim.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import rebotstudio.hhsturim.service.CommentService;
import rebotstudio.hhsturim.vo.CommentVo;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class CommentController {   // 评论相关


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getComById")
    @ApiOperation("获取地点的所有评论")
    public ResultVo getAllCommentByPid(Integer pid){
        List<CommentVo> commentVos = commentService.get(pid);
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,commentVos.size(),commentVos);
    }

    @PostMapping("/deleteCom")
    @ApiOperation("删除一条评论")
    public ResultVo deleteById(Long id){
        commentService.deleteById(id);
        return new ResultVo(StatusCode.DELETE_SUCCESS.code,StatusCode.DELETE_SUCCESS.dsc,null);
    }

    @PostMapping("/add")
    @ApiOperation("添加一条评论")
    public ResultVo addCom(Integer uid,Integer pid,String c){
        commentService.addComment(uid,pid,c);
        return new ResultVo(StatusCode.SAVE_SUCCESS.code,StatusCode.SAVE_SUCCESS.dsc,null);
    }

}
