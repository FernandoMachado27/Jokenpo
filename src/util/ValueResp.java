package util;

import java.io.Serializable;

public class ValueResp implements Serializable{
	
	private String vencedor;

	public ValueResp(String vencedor) {
		this.vencedor = vencedor;
	}

	public String getVencedor() {
		return vencedor;
	}

}
