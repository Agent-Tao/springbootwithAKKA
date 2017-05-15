package com.tao.akka;

import com.tao.springboot.ClusterInfo;
import com.tao.springboot.SpringContextUtil;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.LeaderChanged;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.MemberUp;
import akka.cluster.ClusterEvent.MemberRemoved;
import akka.cluster.ClusterEvent.UnreachableMember;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ClusterListener extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  Cluster cluster = Cluster.get(getContext().system());

  //subscribe to cluster changes
  @Override
  public void preStart() {
    //#subscribe
    cluster.subscribe(getSelf(), ClusterEvent.initialStateAsEvents(), 
        MemberEvent.class, UnreachableMember.class,LeaderChanged.class);

  }

  //re-subscribe when restart
  @Override
  public void postStop() {
    cluster.unsubscribe(getSelf());
  }

  @Override
  public void onReceive(Object message) {
    if (message instanceof MemberUp) {
      MemberUp mUp = (MemberUp) message;
      log.info("!!!!!!!!!!!!!!!Member is Up: {}", mUp.member().address().toString());
      ClusterInfo ci = (ClusterInfo) SpringContextUtil.getBean("clusterInfo");
      ci.addWorker(mUp.member().address().toString());
    } else if (message instanceof UnreachableMember) {
      UnreachableMember mUnreachable = (UnreachableMember) message;
      log.info("Member detected as unreachable: {}", mUnreachable.member());

    } else if (message instanceof MemberRemoved) {
      MemberRemoved mRemoved = (MemberRemoved) message;
      log.info("Member is Removed: {}", mRemoved.member().address().toString());
      ClusterInfo ci = (ClusterInfo) SpringContextUtil.getBean("clusterInfo");
      ci.deleteWorker(mRemoved.member().address().toString());
    } else if (message instanceof MemberEvent) {
      // ignore
    } else if (message instanceof LeaderChanged) {
    	LeaderChanged leader = (LeaderChanged) message;
    	log.info("##############leader is changed: {}", leader.leader());
        ClusterInfo ci = (ClusterInfo) SpringContextUtil.getBean("clusterInfo");
        ci.setLeader(leader.leader().toString());
        LocalMaster lm = (LocalMaster) SpringContextUtil.getBean("localMaster");
        lm.register(leader.getLeader());
    }
    else {
      unhandled(message);
    }

  }
}
