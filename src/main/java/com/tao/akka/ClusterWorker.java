package com.tao.akka;

import akka.actor.ActorSelection;
import akka.actor.Address;
import akka.actor.UntypedActor;
import akka.cluster.Member;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ClusterWorker extends UntypedActor{

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	Address addr = null;
	
	@Override
	public void preStart() {
		
	}
	
	@Override
	public void onReceive(Object message) throws Throwable {
		
		if(message instanceof Address) {
			addr = (Address) message;
		}
		if(message.equals("reg")) {
			register();
		}
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.info(getSelf().toString()+message.toString());
	}
	
	public void register() {
		log.info(addr.toString()+"/user/master");
        //ActorSelection master = getContext().actorSelection("akka.tcp://ClusterSystem@127.0.0.1:12345/user/master");
		ActorSelection master = getContext().actorSelection(addr.toString()+"/user/master");
		
        master.tell(new CommandMessages.Command(CommandMessages.WORKER_REGISTRATION), this.getSelf());
	}

}
