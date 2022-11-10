package util;

import java.io.Serializable;

public class ValueReq implements Serializable{
	
	private String p1, p2;

	public ValueReq(String p1, String p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public String getP1() {
		return p1;
	}
	
	public String getP2() {
		return p2;
	}
	

}
