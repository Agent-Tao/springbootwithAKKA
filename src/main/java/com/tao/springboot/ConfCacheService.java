package com.tao.springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.tao.springboot.dao.db.ConfConstDao;
import com.tao.springboot.dao.po.ConfConst;


/**
 * @author tao.jiang
 *
 */
@Service("confCacheService")
public class ConfCacheService {
	private static final Logger log = LoggerFactory
			.getLogger(ConfCacheService.class);
	@Resource
	ConfConstDao confConstDao;
	List<ConfConst> m_confs;

	public String GetConfByKey(String key) {
		if (m_confs == null) {
			m_confs = confConstDao.getConfConstList();
		}

		for (ConfConst conf : m_confs) {
			if (conf.getConst_key().equals(key)) {
				return conf.getConst_value();
			}
		}
		return "";
	}
	

	public JSONArray GetAll() {
		JSONArray rjson = new JSONArray();
		m_confs = confConstDao.getConfConstList();
		rjson.addAll(m_confs);
		return rjson;
	}

	public List<String> getMeshFilterList(String key) {
		String backlist = GetConfByKey(key);
		String[] backlistarray=backlist.split(",");
		return Arrays.asList(backlistarray);
	}
	
}
