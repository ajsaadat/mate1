package com.mate1.hq.core;

import com.mate1.hq.core.impl.SessionManager.QueryResult;
import com.mate1.hq.exceptions.InvalidSessionException;

public interface IRequestManager {

	/**
	 * attempts to retrieve data from specified bucket[name] for a user[sessionID]
	 * @param sessionID, representing a returned user. if provided sessionID is invalid
	 * an {@link InvalidSessionException} will be thrown.
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}.
	 */
	QueryResult getData(String sessionID, String name);

	/**
	 *  attempts to retrieve data from specified bucket[name]
	 * @param name of the bucket to retrieve information from.
	 * @return a {@link QueryResult}.
	 */
	QueryResult getData(String name);

	void addData(String qName, String data);

}