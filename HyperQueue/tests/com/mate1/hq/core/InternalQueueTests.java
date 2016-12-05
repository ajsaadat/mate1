package com.mate1.hq.core;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mate1.hq.core.impl.InternalQueue;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;


public class InternalQueueTests {
	@Test(groups = {"unit tests", "internal queue"}, 
			expectedExceptions = IllegalArgumentException.class)
	public void nullConstrTest(){
		new InternalQueue(null) ;
	}

	@Test(groups = {"unit tests", "internal queue"}) 
	public void actualTest(){
		IInternalQueue iQueue = new InternalQueue("junit bucekt") ;
		Assert.assertNotNull(iQueue);
		Assert.assertNotNull(iQueue.getData()) ;
	}

	@Test(groups = {"unit tests", "internal queue"}) 
	public void getDataTest(){
		IInternalQueue iQueue = new InternalQueue("junit bucekt") ;
		Assert.assertNotNull(iQueue.getData()) ; 
	}

	@Test(groups = {"unit tests", "internal queue"}, expectedExceptions=ItemNotFoundInBucketException.class)
	public void getDataByIndexTest() throws ItemNotFoundInBucketException{
		IInternalQueue iQueue = new InternalQueue("junit bucekt") ;
		iQueue.getData(5) ;

	}
}
