package mybatis.dao;

import mybatis.model.Address;

public interface AddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    int insert(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    int insertSelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    Address selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    int updateByPrimaryKeySelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.address
     *
     * @mbggenerated Sun Nov 02 15:01:31 CET 2014
     */
    int updateByPrimaryKey(Address record);
}