package manager.impl;

import manager.CommentManager;
import mybatis.dao.CommentMapper;
import mybatis.handler.LongIntParameters;
import mybatis.model.basic.Comment;
import mybatis.model.complex.CommentComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.CommentFO;

import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 13.3.2015.
 */

@Service
public class CommentManagerImpl implements CommentManager {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Long addComment(CommentFO fo) {

        Comment comment = new Comment();

        if(fo.getIdUser() != null && fo.getIdUser() > 0){
            comment.setIdUser(fo.getIdUser());
        }

        comment.setIdEvent(fo.getIdEvent());
        comment.setText(fo.getText());
        comment.setTime(new Date());

        int rows = commentMapper.insert(comment);

        if(rows > 0){
            return comment.getId();
        }

        return -1L;
    }

    @Override
    public List<CommentComplex> getCommentsPageForEventByID(Long eventId, Integer pageIndex) {

        LongIntParameters eventIDandPageIndex = new LongIntParameters(eventId,pageIndex);

        return commentMapper.selectCommentsByEventID(eventIDandPageIndex);
    }
}
