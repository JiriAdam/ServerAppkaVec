package mybatis.dao;

import mybatis.model.UserRoleMN;

public interface UserRoleMNMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    int insert(UserRoleMN record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    int insertSelective(UserRoleMN record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    UserRoleMN selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    int updateByPrimaryKeySelective(UserRoleMN record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.user_role_mn
     *
     * @mbggenerated Fri Dec 12 21:32:13 CET 2014
     */
    int updateByPrimaryKey(UserRoleMN record);
}