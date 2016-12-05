package com.mate1.hq.core;
/**
 * Represents the data that was given to user previously. 
 * This information contains the name of bucket and 
 * location of data within that bucket.
 * @author ajsaadat
 *
 */
class ConsumedData{
	private String name ; 
	private int index ;
	
	public ConsumedData(String name, int index){
		this.name = name ; 
		this.index = index ; 
	}
	
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ConsumedData other = (ConsumedData) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumedData [name=" + name + ", index=" + index + "]";
	} 
}