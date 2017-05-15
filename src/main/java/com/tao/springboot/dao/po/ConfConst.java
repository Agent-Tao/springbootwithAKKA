package com.tao.springboot.dao.po;

import javax.persistence.Entity;

/**
 * ConfConst.java
 * <p>
 * 系统软件配置
 * </p>
 * 
 * @author li.xia
 * @created 2015-4-1 下午03:16:38
 * @version 1.0.0
 */

@Entity
public class ConfConst extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070721545561186894L;
	private int const_id;
	private String const_key;
	private String const_value;
	private String const_desc;
	private String del_flag;

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public int getConst_id() {
		return const_id;
	}

	public void setConst_id(int const_id) {
		this.const_id = const_id;
	}

	public String getConst_key() {
		return const_key;
	}

	public void setConst_key(String const_key) {
		this.const_key = const_key;
	}

	public String getConst_value() {
		return const_value;
	}

	public void setConst_value(String const_value) {
		this.const_value = const_value;
	}

	public String getConst_desc() {
		return const_desc;
	}

	public void setConst_desc(String const_desc) {
		this.const_desc = const_desc;
	}

}
