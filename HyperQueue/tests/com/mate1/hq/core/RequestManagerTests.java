package com.mate1.hq.core;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestManagerTests {

	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void addNullBucketNameTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.addData(null, new String());
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void addNullDataTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void addActualTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", "online-dating");
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullBucketNameTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.getData(null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getNullSessionIDTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.getData(null, "online-dating");
	}
	
	@Test(groups = {"unit tests", "request manager tests"}, expectedExceptions = IllegalArgumentException.class)
	public void getInvalidBucketNameTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.getData("mate1", null);
	}
	
	@Test(groups = {"unit tests", "request manager tests"})
	public void actualGetTest(){
		RequestManager rManager = new RequestManager() ; 
		rManager.addData("mate1", "online-dating");
		Assert.assertNotNull(rManager.getData("mate1"));
	}
}
