package com.tao.akka;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import akka.actor.ActorPath;
import akka.actor.ActorPaths;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.client.ClusterClient;
import akka.cluster.client.ClusterClientSettings;

public class ClusterClientMain {

	static Set<ActorPath> initialContacts() {
		return new HashSet<ActorPath>(Arrays.asList(
				ActorPaths.fromString("akka.tcp://OtherSys@host1:2552/system/receptionist"),
				ActorPaths.fromString("akka.tcp://OtherSys@host2:2552/system/receptionist")
				));
	}
	/*
	public static void main(String[] args) {
		
        ActorSystem system = ActorSystem.create("ClusterSystem");
		
		final ActorRef c = system.actorOf(ClusterClient.props(
				ClusterClientSettings.create(system).withInitialContacts(initialContacts())),"client");

		c.tell(new ClusterClient.Send("/user/serviceA", "hello", true) , ActorRef.noSender());
		c.tell(new ClusterClient.SendToAll("/user/serviceB", "hi") , ActorRef.noSender());

	}*/

}
