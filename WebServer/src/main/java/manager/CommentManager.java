package manager;

import mybatis.model.complex.CommentComplex;
import web.model.CommentFO;

import java.util.List;

/**
 * Created by Irrielde on 13.3.2015.
 */
public interface CommentManager {


    Long addComment(CommentFO fo);

    List<CommentComplex> getCommentsPageForEventByID(Long eventId, Integer pageIndex);
}
