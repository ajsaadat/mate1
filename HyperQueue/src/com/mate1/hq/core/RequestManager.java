package com.mate1.hq.core;

import org.apache.log4j.Logger;

import com.mate1.hq.core.SessionManager.QueryResult;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.InvalidSessionException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public class RequestManager {
	
	private SessionManager sManager = SessionManager.getInstance() ; 
	private final Logger logger = Logger.getLogger(RequestManager.class) ;
	/**
	 * attempts to retrieve data from specified bucket[name] for a user[sessionID]
	 * @param sessionID, representing a returned user. if provided sessionID is invalid
	 * an {@link InvalidSessionException} will be thrown.
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}.
	 */
	public QueryResult getData(String sessionID, String name){
		try {
			logger.info("Trying to retrieve data from [" + name + "] for sessionID [" + sessionID + "]." );
			return sManager.getData(sessionID, name) ;
		} catch (BucketNotFoundException e) {
			logger.error("Bucket [" + name + "] was not found.");
			throw new IllegalStateException(e.getMessage()) ; 
		} catch (InvalidSessionException e) {
			logger.error("Provided sessionID [" + sessionID + "] was not found.");
			throw new IllegalStateException(e.getMessage()) ; 
		} catch (ItemNotFoundInBucketException e) {
			logger.error("Bucket [" + name + "] did not contain any items.");
			throw new IllegalStateException(e.getMessage()) ; 
		}
	}
	/**
	 *  attempts to retrieve data from specified bucket[name]
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}.
	 */
	public QueryResult getData(String name){
		try {
			return sManager.getData(name) ;
		} catch (BucketNotFoundException e) {
			logger.error("Bucket [" + name + "] was not found.");
			throw new IllegalStateException(e.getMessage()) ; 
		} catch (ItemNotFoundInBucketException e) {
			logger.error("Bucket [" + name + "] did not contain any items.");
			throw new IllegalStateException(e.getMessage()) ; 
		} 
	}
	
	public void addData(String qName, String data){
		sManager.addData(qName, data);
	}
}
