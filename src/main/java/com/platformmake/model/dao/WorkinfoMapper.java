package com.platformmake.model.dao;

import com.platformmake.model.entity.Workinfo;
import com.platformmake.model.entity.WorkinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    long countByExample(WorkinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int deleteByExample(WorkinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int deleteByPrimaryKey(Integer workid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int insert(Workinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int insertSelective(Workinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    List<Workinfo> selectByExample(WorkinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    Workinfo selectByPrimaryKey(Integer workid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int updateByExampleSelective(@Param("record") Workinfo record, @Param("example") WorkinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int updateByExample(@Param("record") Workinfo record, @Param("example") WorkinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int updateByPrimaryKeySelective(Workinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workinfo
     *
     * @mbg.generated Thu Jul 23 09:40:58 CST 2020
     */
    int updateByPrimaryKey(Workinfo record);
    
    int hasRelatedSchedule(Integer planid);
    
    int hasRelatedUnfinishedSchedule(Integer planid);

    int hasFinishedNum(Integer planid);

    int getProduceNum(Integer planid);
}