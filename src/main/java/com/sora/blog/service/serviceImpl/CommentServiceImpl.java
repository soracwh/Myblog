package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.CommentMapper;
import com.sora.blog.pojo.Comment;
import com.sora.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author Sora Chen
 * @Date 2022/3/6 3:11 下午
 * @Version 1.0
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
        List<Comment> allComment = commentMapper.getCommentsByBlogId(blogId);
        return sortComment(allComment);
    }

    @Transactional
    @Override
    public int insertComment(Comment comment) {
        comment.setCreateTime(new Date());
        if(comment.getParentId()==-1){
            comment.setParentId(null);
        }
        return commentMapper.insertComment(comment);
    }

    /**
     * 实现评论两层嵌套逻辑
     * */
    public List<Comment> sortComment(List<Comment> allComment){
        List<Comment> res = new ArrayList<>();
        HashMap<Long,List<Comment>> map = new HashMap<>();
        /**
         * 先找到根节点
         */
        for(Comment c : allComment){
            Long pId = c.getParentId();
            if(pId==null){
                res.add(c);
            }else{
                List<Comment> l = map.getOrDefault(pId,new ArrayList<>());
                l.add(c);
                map.put(pId,l);
            }
        }
        /**
         *以每个根节点进行dfs
         */
        for (Comment r : res) {
            Long id = r.getId();
            List<Comment> replyComment = new ArrayList<>();
            Stack<Long> stack = new Stack<>();
            stack.push(id);
            while(!stack.isEmpty()&&!map.isEmpty()){
                Long parentId = stack.pop();
                if(map.containsKey(parentId)){
                    List<Comment> l = map.get(parentId);
                    for (Comment comment : l) {
                        replyComment.add(comment);
                        stack.push(comment.getId());
                    }
                    map.remove(parentId);
                }
            }
            r.setReplyComment(replyComment);
        }
        return res;
    }

}
