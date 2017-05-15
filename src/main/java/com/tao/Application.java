package com.tao;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinGroup;
import akka.routing.RoundRobinPool;

import com.tao.akka.ClusterListener;
import com.tao.akka.ClusterMaster;
import com.tao.akka.ClusterWorker;
import com.tao.akka.LocalMaster;
import com.tao.springboot.SpringContextUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@EnableAutoConfiguration
@ImportResource("classpath:spring-dao.xml")
@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer{

	
	public static ActorSystem system;
	public static ActorRef master;
	public static void main(String[] args) {
		
        SpringApplication.run(Application.class, args);
		
		// TODO Auto-generated method stub
    	String port="12345";
        // Override the configuration of the port

        Config config = ConfigFactory.parseString(
            "akka.remote.netty.tcp.port=" + port+",akka.remote.netty.tcp.hostname=127.0.0.1").withFallback(
            ConfigFactory.load());
    	
        //String pport = config.getString("akka.remote.netty.tcp.port");
        //System.out.println(pport);
        //String hostname = config.getString("akka.remote.netty.tcp.hostname");
        //System.out.println(hostname);
        
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        // Create an Akka system
        system = ActorSystem.create("ClusterSystem", config);
        
        // Create an actor that handles cluster domain events
        system.actorOf(Props.create(ClusterListener.class),
            "clusterListener"+port);
        
        master = system.actorOf(Props.create(ClusterMaster.class),"master");
        
        LocalMaster lm = (LocalMaster) SpringContextUtil.getBean("localMaster");
        lm.init(4);
        /*
        ActorRef router = system.actorOf(new RoundRobinPool(5).props(Props.create(ClusterWorker.class) ) ,
        		"router");
        
        router.tell("show me the money1", ActorRef.noSender());
        router.tell("show me the money2", ActorRef.noSender());
        router.tell("show me the money3", ActorRef.noSender());
        router.tell("show me the money4", ActorRef.noSender());
        */
        
        /*
        system.actorOf(Props.create(ClusterWorker.class), "w1");
        system.actorOf(Props.create(ClusterWorker.class), "w2");
        system.actorOf(Props.create(ClusterWorker.class), "w3");
		
		List<String> paths = Arrays.asList("/user/w1","/user/w2", "/user/w3");
		ActorRef router = system.actorOf(new RoundRobinGroup(paths).props(),"router");
		
        router.tell("show me the money1", ActorRef.noSender());
        router.tell("show me the money2", ActorRef.noSender());
        router.tell("show me the money3", ActorRef.noSender());
        router.tell("show me the money4", ActorRef.noSender());
        */
        
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8080);	
	}

}
