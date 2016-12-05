package com.mate1.hq.core.impl;

import org.apache.log4j.Logger;

import com.mate1.hq.core.IRequestManager;
import com.mate1.hq.core.ISessionManager;
import com.mate1.hq.core.impl.SessionManager.QueryResult;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.InvalidSessionException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public class RequestManager implements IRequestManager {
	
	private ISessionManager sManager = SessionManager.getInstance() ; 
	private final Logger logger = Logger.getLogger(RequestManager.class) ;
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IRequestManager#getData(java.lang.String, java.lang.String)
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IRequestManager#getData(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.IRequestManager#addData(java.lang.String, java.lang.String)
	 */
	@Override
	public void addData(String qName, String data){
		sManager.addData(qName, data);
	}
}
