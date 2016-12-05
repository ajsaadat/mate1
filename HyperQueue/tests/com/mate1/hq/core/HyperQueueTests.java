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
	
	@Test(groups = {"unit tests", "hyper queue"})
	public void invalidBucketNameTest() throws BucketNotFoundException, ItemNotFoundInBucketException{
		HyperQueue hQueue = new HyperQueue() ; 
		hQueue.getData("mate1", -1) ;
	}
}
