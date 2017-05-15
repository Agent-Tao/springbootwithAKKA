package com.tao.akka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.cluster.Member;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinGroup;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

public class ClusterMaster extends UntypedActor{

	//ActorRef m_router;
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	List<ActorRef> workerActors = new ArrayList<ActorRef>();
	
	Router router = new Router(new RoundRobinRoutingLogic());
	@Override
	public void preStart() {
		//getContext().actorOf(Props.create(ClusterWorker.class), "w1");
		//getContext().actorOf(Props.create(ClusterWorker.class), "w2");
		//getContext().actorOf(Props.create(ClusterWorker.class), "w3");
		//getContext().actorOf(Props.create(ClusterWorker.class), "w4");
		List<String> paths = Arrays.asList("/user/master/w1","/user/master/w2", "/user/master/w3", "/user/master/w4");

		//m_router = getContext().system().actorOf(new RoundRobinGroup(paths).props(),"router");
		
	}
	
	@Override
	public void onReceive(Object message) throws Throwable {

		if(message instanceof Member) {
			register((Member) message);
		} else if (message instanceof CommandMessages.Command) {
			CommandMessages.Command cmd = (CommandMessages.Command) message;
			if(cmd.getContent().equals(CommandMessages.WORKER_REGISTRATION)) {
		      getContext().watch(getSender());
		      workerActors.add(getSender());
		      router = router.addRoutee(new ActorRefRoutee(getSender()));
		      System.out.println("workerActors size " + workerActors.size());}
		} else if(message instanceof String) {
			log.info((String) message);
			log.info("forward############");
			log.info("count:"+workerActors.size());
			//workerActors.get(0).forward(message, getContext());
			router.route(message, getSender());
		}else {
			log.info("unknown msg");
		}
	}
	
	public void register(Member member) {
	
	}
}
