package com.tao.springboot.dao.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tao.springboot.dao.po.ConfConst;



/**
 * ConfConstDao.java
 * <p>
 * 参数
 * </p>
 * 
 * @author li.xia
 * @created 2015-4-1 下午03:16:38
 * @version 1.0.0
 */

@Repository
public interface ConfConstDao extends BaseDao<ConfConst> {

	public ConfConst getById(@Param("id") int id);

	public ConfConst getByKey(@Param("thekey") String thekey);

	public void updateById(@Param("id") int id,
			@Param("thevalue") String thevalue, @Param("thedesc") String thedesc);

	public void updateByKey(@Param("thekey") String key,
			@Param("thevalue") String value, @Param("thedesc") String thedesc);

	/**
	 * 取得配置信息列表
	 * 
	 * @return
	 */
	public List<ConfConst> getConfConstList();
	
	public List<ConfConst> getPage(@Param("sConditions") String sConditions,@Param("page_size") int page_size, @Param("row_from") int row_from);
	
	public int getCount(@Param("sConditions") String sConditions);
	
	public void remove(@Param("id") int id);
	
	public void add(ConfConst entity);
	
	public void update(@Param("sContent") String sContent,@Param("sConditions") String sConditions);

}
