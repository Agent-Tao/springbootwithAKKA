package com.tao.springboot;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tao.Application;
import com.tao.akka.ClusterListener;
import com.tao.springboot.dao.db.ConfConstDao;
import com.tao.springboot.dao.po.ConfConst;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Controller
public class SampleController {

	@Resource
	ClusterInfo clusterInfo;
	
	@Resource
	ConfCacheService confCacheService;
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return clusterInfo.getDesc();      
    }
    
    @RequestMapping("/testjson")
    @ResponseBody
    JSONObject testjson() {
    	JSONObject jobj = new JSONObject();
    	jobj.put("ret", "OK"); 	
        return jobj;
    }
    
	@RequestMapping("getconf")
	@ResponseBody
	public JSONArray getConf(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("getConf received");
		return confCacheService.GetAll();
	}
	
    @RequestMapping("/dowork")
    @ResponseBody
    String dowork() {
    	Application.master.tell("let us do work!!!", ActorRef.noSender());
        return "roger that!!!";      
    }
}

