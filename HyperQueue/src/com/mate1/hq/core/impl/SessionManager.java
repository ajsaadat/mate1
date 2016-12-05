package com.mate1.hq.core.impl;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.mate1.hq.core.ConsumedData;
import com.mate1.hq.core.IHyperQueue;
import com.mate1.hq.core.ISessionManager;
import com.mate1.hq.core.UserData;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.InvalidSessionException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

/**
 * This class tracks users activity within the system. 
 * Through this class user can add and retrieve data from the underlying data structure. 
 * This class follows singleton design pattern. 
 */
public class SessionManager implements ISessionManager {
	private CopyOnWriteArrayList<UserData> sessionManager = new CopyOnWriteArrayList<>() ; 
	private IHyperQueue hQueue = new HyperQueue() ; 
	private final Logger logger = Logger.getLogger(SessionManager.class) ;
	
	private static ISessionManager sManager = new SessionManager() ; 
	
	private SessionManager(){
		
	}
	
	/**
	 * Gets the single instance of SessionManager. If an instance does not exist
	 * a new instance will be created.
	 *
	 * @return single instance of SessionManager
	 */
	public  static ISessionManager getInstance(){
		if(sManager == null){
			synchronized(SessionManager.class){
				if(sManager == null){
					sManager = new SessionManager() ; 
					return sManager ; 
				}
			}
		}
		return sManager ; 
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.ISessionManager#addData(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized void addData(String name, String data){
		hQueue.addData(name, data);
	}
	
	/**
	 * Adds {@link UserData} to the sessionManager. 
	 *
	 * @param sessionID identifies an user within the system. If a user has previously requested 
	 * data from the system, then he/she will have an session identifier. SessionID can not be null
	 * or empty, if so, an {@link IllegalArgumentException} will be thrown.
	 * @param name of the bucket from which user wants to retrieve data. Name can not be null
	 * or empty, if so, an {@link IllegalArgumentException} will be thrown.
	 * @param offset corresponds to the index of the bucket from which user successfully retrieved information. 
	 * 
	 */
	private synchronized void addSession(String sessionID, String name, int offset){
		if(name == null || name.isEmpty()){
			logger.error("Bucket name can not be empty.");
			throw new IllegalArgumentException("Provided bucket name can not be null or empty.") ; 
		}else if(sessionID == null || sessionID.isEmpty()){
			logger.error("SessionID can not be null.");
			throw new IllegalArgumentException("Provided session can not be null or empty") ; 
		}else{
			ConsumedData cData = new ConsumedData(name, offset) ; 
			UserData sData = new UserData(sessionID, cData) ; 
			logger.info("Attempting to retrieve information for [" + sData + "] from sessionManager.");
			if(sessionManager.contains(sData)){
				logger.info("System was able to retrieve information for [" + sData + "] from sessionManager.");
				UserData existingUserData = sessionManager.get(sessionManager.indexOf(sData)) ; 
				if(existingUserData.getDatas().contains(cData)){
					ConsumedData existingConsumedData = existingUserData.getDatas().get( existingUserData.getDatas().indexOf(cData)) ; 
					existingConsumedData.setIndex(offset);
				}else{
					existingUserData.addcData(cData);
				}
			}else{
				logger.info("System was not able to retrieve information for [" + sData + "] from sessionManager.");
				logger.info("Adding information [" + sData + "] to sessionManager.");
				sessionManager.add(sData) ;
			}
			
		}
		
	}
	
	
	/**
	 * Use this method to get the last index returned to the user holding 
	 * sessionID. 
	 *
	 * @param sessionID identifier signaling that a user has request information from
	 * the system before. If a null/emtpy is provided, an {@link IllegalArgumentException} is 
	 * thrown
	 * @param name of the bucket to retrieve information from.
	 * @return if sessionID is a valid one[exist with the ds], identifier for the last data returned to
	 * user is returned.
	 */
	private synchronized ConsumedData getConsumedData(String sessionID, String name) throws InvalidSessionException{
		if(sessionID == null || sessionID.isEmpty()){
			throw new IllegalArgumentException("SessionID information can not be null or empty.") ;
		}else if (name == null || name.isEmpty()){
			throw new IllegalArgumentException("Name of a bucket can not be null or empty") ; 
		}
		UserData userData = new UserData(sessionID) ; 
		logger.info("Attempting to retrieve information for [" + userData + "] from sessionManager.");
		if(sessionManager.contains(userData)){
			logger.info("System was able to retrieve information for [" + userData + "] from sessionManager.");
			 UserData sData = sessionManager.get(sessionManager.indexOf(userData));
			 ConsumedData inputData = new ConsumedData(name, 0) ; 
			if( sData.getDatas().contains(inputData)){
				return sData.getDatas().get(sData.getDatas().indexOf(inputData)); 
			}else{
				return new ConsumedData(name, 0) ; 
			}
		}else{
			throw new InvalidSessionException("Provided sessionID [" + sessionID + "] does not exist within the datastore.");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.ISessionManager#getData(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized QueryResult getData(String sessionID, String name) throws BucketNotFoundException, 
	InvalidSessionException, ItemNotFoundInBucketException{
		ConsumedData cData = getConsumedData(sessionID, name) ;
		String  data = hQueue.getData(cData.getName(), cData.getIndex() + 1) ;  
		addSession(sessionID, name, cData.getIndex() + 1);
		return new QueryResult(data, sessionID) ; 
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.mate1.hq.core.ISessionManager#getData(java.lang.String)
	 */
	@Override
	public synchronized QueryResult getData(String name) throws BucketNotFoundException, ItemNotFoundInBucketException{
		String sessionID = UUID.randomUUID().toString() ; 
		int index = 0 ; 
		String data = hQueue.getData(name, index) ;
		addSession(sessionID, name, index);
		return new QueryResult(data, sessionID) ; 

	}
	
	/**
	 * Representing data retrieved from the system for particular user.
	 * @author ajsaadat
	 *
	 */
	public class QueryResult{
		private String data ; 
		private String sessionID ; 
		
		public QueryResult (String data, String sessionID){
			this.sessionID = sessionID ; 
			this.data = data ; 
		}
		
		public String getData() {
			return data;
		}

		public String getSessionID() {
			return sessionID;
		}

		
	}
}
