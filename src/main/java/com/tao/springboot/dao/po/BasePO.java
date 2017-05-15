package com.tao.springboot.dao.po;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * BasePO.java
 * <p>
 * 所有domain类的父类
 * </p>
 * 
 * @author jinghao
 * @created 2015-3-23 下午03:16:38
 * @version 1.0.0
 *          <p>
 *          <b>last update </b> by jinghao @ 2015-8-12
 *          </p>
 */

public class BasePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6516482659783630936L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
