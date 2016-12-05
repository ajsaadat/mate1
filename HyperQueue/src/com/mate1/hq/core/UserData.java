package com.mate1.hq.core;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents the buckets that a user successfully retrieved information from.
 * @author ajsaadat
 *
 */
public class UserData {
	private String sessionID ; 
	private CopyOnWriteArrayList<ConsumedData> cDatas = new CopyOnWriteArrayList<>() ; 
	
	public UserData(String sessionID){
		if(sessionID == null || sessionID.isEmpty()){
			throw new IllegalArgumentException("Session can not be null or empty.") ; 
		}else{
			this.sessionID = sessionID ; 
		}
	}
	
	public UserData(String sessionID, ConsumedData cData){
		if(sessionID == null || sessionID.isEmpty()){
			throw new IllegalArgumentException("Session can not be null or empty.") ; 
		}else if (cData == null){
			throw new IllegalArgumentException("Consumed data can not be null.") ; 
		}else{
			this.sessionID = sessionID ; 
			cDatas.add(cData);
		}
	}

	public CopyOnWriteArrayList<ConsumedData> getDatas() {
		return cDatas;
	}

	public void setcDatas(CopyOnWriteArrayList<ConsumedData> cDatas) {
		this.cDatas = cDatas;
	}
	
	public void addcData(ConsumedData cData){
		cDatas.add(cData);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		if (sessionID == null) {
			if (other.sessionID != null)
				return false;
		} else if (!sessionID.equals(other.sessionID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SessionData [sessionID=" + sessionID + ", cDatas=" + cDatas + "]";
	}
	
}
