package mybatis.dao;

import mybatis.handler.TwoLongParameters;
import mybatis.model.basic.LocalityFilter;
import mybatis.model.complex.AppUser;

import java.util.List;

public interface LocalityFilterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int insert(LocalityFilter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int insertSelective(LocalityFilter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    LocalityFilter selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int updateByPrimaryKeySelective(LocalityFilter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webserver.locality_filter
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    int updateByPrimaryKey(LocalityFilter record);

    /**
     *
     * @param params  user id, region id
     * @return
     */
    List<LocalityFilter> selectUserLocalityFilterByUserAndCountryIDs(TwoLongParameters params);

    int deleteByUserAndRegionIDs(TwoLongParameters twoLongParameters);


    List<LocalityFilter> selectUserLocalityFilterByUserID(Long userID);
}