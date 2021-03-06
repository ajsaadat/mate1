package com.mate1.hq.core.impl;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.mate1.hq.core.IInternalQueue;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

/**
 * Data structure representing a "queue". 
 * This structure has a name and a list of strings. 
 * @author ajsaadat
 *
 */
public class InternalQueue implements IInternalQueue {
	private final Logger logger = Logger.getLogger(InternalQueue.class) ;
	private String name ; 
	private ConcurrentLinkedQueue<String> internalQueue = new ConcurrentLinkedQueue<>() ; 
	
	public InternalQueue(String name){
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Queue name can not be null/empty") ;
		}
		this.name = name ; 
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IInternalQueue#addData(java.lang.String)
	 */
	@Override
	public void addData(String data){
		if(data == null || data.isEmpty()){
			logger.info("Nothing to add to the queue.");
			return ;
		}else if(!internalQueue.contains(data)){
			logger.info("adding [" + data + "] to the queue.");
			internalQueue.add(data);
		}else{
			return ; 
		}
	}
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IInternalQueue#getData()
	 */
	@Override
	public String getData(){
		if(internalQueue.isEmpty()){
			return "nothing was found in [" + name +"] queue." ;
		}else{
			String data = internalQueue.peek()  ; 
			logger.info("Returning [" + data + "].");
			return data ;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IInternalQueue#getData(int)
	 */
	@Override
	public String getData(int index) throws ItemNotFoundInBucketException{
		if(index >= internalQueue.size()){
			logger.error("Provided index [" + index +  "] is not within the internal structure.");
			throw new ItemNotFoundInBucketException("Index provided des not translate well into internal structure.") ;
		}
		String indexedData = (String)(internalQueue.toArray())[index] ;
		return indexedData ; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IInternalQueue#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof InternalQueue){
			InternalQueue inputQueue = (InternalQueue)obj ;
			if(inputQueue.name.equalsIgnoreCase(name)){
				return true ; 
			}else{
				return false ; 
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "InternalQueue [name=" + name + ", internalQueue=" + internalQueue + "]";
	}
}
