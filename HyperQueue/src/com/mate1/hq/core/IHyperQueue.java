package com.mate1.hq.core;

import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public interface IHyperQueue {

	/**
	 * Gets the data from indicate internal queue. 
	 *
	 * @param name of the queue from which the data should be retrieved from. 
	 * @return a String if indicated queue contains any information, otherwise 
	 * and error will be returned. 
	 * @throws BucketNotFoundException 
	 */
	String getData(String name, int index) throws BucketNotFoundException, ItemNotFoundInBucketException;

	/**
	 * Adds data to the indicated queue. If indicated queue[name] does not exist, 
	 * system will create a new queue and add the data to the new queue.
	 *
	 * @param name of the queue to add data to. Name of the queue can not be null or empty. 
	 * If null/empty is provided, and {@link IllegalArgumentException} will be thrown.
	 * @param data to be added to the indicated queue. If data is null or empty nothing will 
	 * be added to the indicated queue. 
	 */
	void addData(String name, String data);

}