package com.mate1.hq.exceptions;

public class ItemNotFoundInBucketException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ItemNotFoundInBucketException(String message){
		super(message);
	}
}
