package mybatis.dao;

import mybatis.model.basic.UserRole;

public interface UserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    int insertSelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    int updateByPrimaryKey(UserRole record);
}