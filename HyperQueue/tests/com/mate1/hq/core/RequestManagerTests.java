package com.mate1.hq.core;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mate1.hq.core.impl.RequestManager;

public class RequestManagerTests {

	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void addNullBucketNameTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.addData(null, new String());
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void addNullDataTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void addActualTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", "online-dating");
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullBucketNameTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.getData(null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullSessionIDTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.getData(null, "online-dating");
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getInvalidBucketNameTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.getData("mate1", null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void actualGetTest(){
		IRequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", "online-dating");
		Assert.assertNotNull(rManager.getData("mate1"));
	}
}
