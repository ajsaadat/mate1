package com.mate1.hq.core;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mate1.hq.core.impl.SessionManager;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.InvalidSessionException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public class SessionManagerTests {

	@Test(groups = {"unit tests", "session manager tests"})
	public void signletonTest(){
		ISessionManager sManager = SessionManager.getInstance() ; 
		ISessionManager tManager = SessionManager.getInstance() ; 
		Assert.assertEquals(sManager, tManager) ; 
		
	}
	
	@Test(groups = {"unit tests", "session manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void addNullBucketNameTest(){
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.addData(null, new String()); 
		
	}
	
	@Test(groups = {"unit tests", "session manager tests"})
	public void addNullDataTest(){
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.addData("mate1", null); 
	}
	
	@Test(groups = {"unit tests", "session manager tests"})
	public void addActualTest(){
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.addData("mate1", "online-dating"); 
		
	}
	
	@Test(groups = {"unit tests", "session manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullBucketNameTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.getData(null); 
	}
	
	
	@Test(groups = {"unit tests", "session manager tests"}, expectedExceptions = BucketNotFoundException.class)
	public void getInvalidBucketNameTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.getData("toto"); 
	}
	
	@Test(groups = {"unit tests", "session manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullSessionIDTest() throws BucketNotFoundException, ItemNotFoundInBucketException, InvalidSessionException{
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.getData(null, "mate1"); 
	}
	
	@Test(groups = {"unit tests", "session manager tests"}, expectedExceptions = InvalidSessionException.class)
	public void getInvalidSessionIDTest() throws BucketNotFoundException, ItemNotFoundInBucketException, InvalidSessionException{
		ISessionManager sManager = SessionManager.getInstance() ; 
		sManager.getData("invalid sessionID", "mate1"); 
	}
	
}
