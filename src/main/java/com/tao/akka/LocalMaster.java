package com.tao.akka;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.tao.Application;

import akka.actor.ActorRef;
import akka.actor.Address;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

@Service("localMaster")
public class LocalMaster {

	List<ActorRef> workerActors = new ArrayList<ActorRef>();
	
	//LoggingAdapter log = Logging.getLogger(Application.system, this);
	public void init(int num) {
		workerActors.add(Application.system.actorOf(Props.create(ClusterWorker.class), "w1"));
		workerActors.add(Application.system.actorOf(Props.create(ClusterWorker.class), "w2"));
		workerActors.add(Application.system.actorOf(Props.create(ClusterWorker.class), "w3"));
		workerActors.add(Application.system.actorOf(Props.create(ClusterWorker.class), "w4"));
	}

	public void register(Address addr) {
		//log.info("register##################################################");
		for(ActorRef w:workerActors) {
		        w.tell(addr, ActorRef.noSender());
		        w.tell("reg", ActorRef.noSender());	
		}
	}
}
