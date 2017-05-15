package com.tao.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service("clusterInfo")
public class ClusterInfo {
	String _leader;
	List<String> _workers;
	
	public ClusterInfo() {
		_leader = "";
		_workers = new ArrayList<String>();
	}
	
	public void setLeader(String leader) {
		_leader = leader;
	}
	
	public void  addWorker(String worker) {
		_workers.add(worker);
	}
	
	public void  deleteWorker(String worker) {
		//_workers = _workers.stream().filter(w->!w.equals(worker)).collect(Collectors.toList());
		Iterator it = _workers.iterator();
		while(it.hasNext()) {
			String iworker = (String) it.next();
			if(iworker.equals(worker)) {
				it.remove();
				break;
			}
		}
	}
	
	public String getDesc() {
		return String.format("leader : %s <br> worker : %s",_leader,_workers.stream().collect(Collectors.joining(",")));
	}
	
}
