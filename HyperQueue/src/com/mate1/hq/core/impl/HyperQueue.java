package com.mate1.hq.core.impl;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mate1.hq.core.IHyperQueue;
import com.mate1.hq.core.IInternalQueue;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

/**
 * Represents internal structure of the system. This class contains multiple {@link InternalQueue}s
 * representing different buckets, from which data can be retrieved or stored to. 
 */
public class HyperQueue implements IHyperQueue {
	private CopyOnWriteArrayList<InternalQueue> hyperQueue = new CopyOnWriteArrayList<>();
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IHyperQueue#getData(java.lang.String, int)
	 */
	@Override
	public synchronized String getData(String name, int index) throws BucketNotFoundException, ItemNotFoundInBucketException{
		IInternalQueue inputQueue = new InternalQueue(name);
		if(hyperQueue.contains(inputQueue)){
			IInternalQueue iQueue = hyperQueue.get(hyperQueue.indexOf(inputQueue)) ; 
			return iQueue.getData(index);
		}else{
			throw new BucketNotFoundException("Queue [" + name + "] does not exist.") ;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IHyperQueue#addData(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized void addData(String name, String data){
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Name of the queue can not be null") ;
		}else if(data == null || data.isEmpty()){
			return ; 
		}else{
			IInternalQueue inputQueue = new InternalQueue(name);
			if(hyperQueue.contains(inputQueue)){
				IInternalQueue iQueue = hyperQueue.get(hyperQueue.indexOf(inputQueue)) ;
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
