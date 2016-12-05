package com.mate1.hq.core;

import com.mate1.hq.core.impl.HyperQueue;
import com.mate1.hq.core.impl.SessionManager.QueryResult;
import com.mate1.hq.exceptions.BucketNotFoundException;
import com.mate1.hq.exceptions.InvalidSessionException;
import com.mate1.hq.exceptions.ItemNotFoundInBucketException;

public interface ISessionManager {

	/**
	 * Adds data to the underlying {@link HyperQueue}. 
	 * 
	 *
	 * @param name representing the name of the bucket which provided data should be added. 
	 * Name can not be null or empty, if so, an {@link IllegalArgumentException} will be thrown, 
	 * @param data that user wants to add to the provided bucket. 
	 */
	void addData(String name, String data);

	/**
	 * Use this method to retrieve information from datastore for returning users[users with sessionID].
	 * @param sessionID identifies a user who has accessed the system before.
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}
	 * @throws BucketNotFoundException will be thrown if bucket name is invalid.
	 * @throws InvalidSessionException will be thrown if the sessionID is invalid.
	 * @throws ItemNotFoundInBucketException will be thrown if the bucket did not contain any data.
	 */
	QueryResult getData(String sessionID, String name)
			throws BucketNotFoundException, InvalidSessionException, ItemNotFoundInBucketException;

	/**
	 * Use this method to retrieve information from datastore for first time users
	 * [users without sessionID].
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}
	 * @throws BucketNotFoundException will be thrown if bucket name is invalid.
	 * @throws InvalidSessionException will be thrown if the sessionID is invalid.
	 * @throws ItemNotFoundInBucketException will be thrown if the bucket did not contain any data.
	 */
	QueryResult getData(String name) throws BucketNotFoundException, ItemNotFoundInBucketException;

}