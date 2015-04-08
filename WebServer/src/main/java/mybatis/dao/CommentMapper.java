package mybatis.dao;

import mybatis.handler.LongIntParameters;
import mybatis.model.basic.Comment;
import mybatis.model.complex.CommentComplex;

import java.util.List;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    Comment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.comment
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int updateByPrimaryKey(Comment record);

    List<CommentComplex> selectCommentsByEventID(LongIntParameters eventIDAndPage);
}