package com.mate1.hq.core;

import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public interface IInternalQueue {

	/**
	 * adds data to the internal structure.
	 * @param data that user wants to add the internal structure. If null or empty
	 * is provided method will not perform any task. 
	 */
	void addData(String data);

	/**
	 * Retrieve data from the head of the internal that structure.
	 * @return data occupying the head of the internal data structure.
	 * if internal data structure is empty, a string indicating that fact will be 
	 * returned.
	 */
	String getData();

	String getData(int index) throws ItemNotFoundInBucketException;

	boolean equals(Object obj);

}