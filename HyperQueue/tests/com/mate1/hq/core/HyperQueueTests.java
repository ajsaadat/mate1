package com.mate1.hq.core;

import org.testng.annotations.Test;

import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public class HyperQueueTests {
	
	@Test(groups = {"unit tests", "hyper queue"}, expectedExceptions = IllegalArgumentException.class)
	public void nullBucketNameTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.getData(null, 0) ;
	}
	
	@Test(groups = {"unit tests", "hyper queue"}, expectedExceptions = BucketNotFoundException.class)
	public void invalidGetBucketNameTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.getData("mate1", -1) ;
	}
	
	@Test(groups = {"unit tests", "hyper queue"}, expectedExceptions = ArrayIndexOutOfBoundsException.class)
	public void invalidGetIndexTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.addData("mate1", "online-dating");
		hQueue.getData("mate1", -1) ;
	}
	
	@Test(groups = {"unit tests", "hyper queue"}, expectedExceptions = IllegalArgumentException.class)
	public void invalidAddBucketNameTest() {
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.addData(null, "some data");
	}
	
	@Test(groups = {"unit tests", "hyper queue"})
	public void invalidAddDataTest() {
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.addData("mate1", null);
	}
	
	@Test(groups = {"unit tests", "hyper queue"})
	public void actualTest() {
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.addData("mate1", "online-dating");
	}
	
	
}
