package com.mate1.hq.core;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

/**
 * Represents internal structure of the system. This class contains multiple {@link InternalQueue}s
 * representing different buckets, from which data can be retrieved or stored to. 
 */
public class HyperQueue {
	private CopyOnWriteArrayList<InternalQueue> hyperQueue = new CopyOnWriteArrayList<>();
	
	/**
	 * Gets the data from indicate internal queue. 
	 *
	 * @param name of the queue from which the data should be retrieved from. 
	 * @return a String if indicated queue contains any information, otherwise 
	 * and error will be returned. 
	 * @throws BucketNotFoundException 
	 */
	public synchronized String getData(String name, int index) throws BucketNotFoundException, ItemNotFoundInBucketException{
		InternalQueue inputQueue = new InternalQueue(name);
		if(hyperQueue.contains(inputQueue)){
			InternalQueue iQueue = hyperQueue.get(hyperQueue.indexOf(inputQueue)) ; 
			return iQueue.getData(index);
		}else{
			throw new BucketNotFoundException("Queue [" + name + "] does not exist.") ;
		}
	}
	
	/**
	 * Adds data to the indicated queue. If indicated queue[name] does not exist, 
	 * system will create a new queue and add the data to the new queue.
	 *
	 * @param name of the queue to add data to. Name of the queue can not be null or empty. 
	 * If null/empty is provided, and {@link IllegalArgumentException} will be thrown.
	 * @param data to be added to the indicated queue. If data is null or empty nothing will 
	 * be added to the indicated queue. 
	 */
	public synchronized void addData(String name, String data){
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Name of the queue can not be null") ;
		}else if(data == null || data.isEmpty()){
			return ; 
		}else{
			InternalQueue inputQueue = new InternalQueue(name);
			if(hyperQueue.contains(inputQueue)){
				InternalQueue iQueue = hyperQueue.get(hyperQueue.indexOf(inputQueue)) ;
				iQueue.addData(data);
			}else{
				InternalQueue iQueue = new InternalQueue(name) ;
				iQueue.addData(data);
				hyperQueue.add(iQueue) ;
			}
		}
	}


	@Override
	public String toString() {
		return "HyperQueue [hyperQueue=" + hyperQueue + "]";
	}
}
