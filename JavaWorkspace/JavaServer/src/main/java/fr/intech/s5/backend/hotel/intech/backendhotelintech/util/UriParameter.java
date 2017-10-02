package fr.intech.s5.backend.hotel.intech.backendhotelintech.util;

public class UriParameter {

	private Long uriId;
	
	private String uriValue;

	public UriParameter() {

	}

	public UriParameter(Long uriId, String uriValue) {
		this.uriId = uriId;
		this.uriValue = uriValue;
	}

	public Long getUriId() {
		return uriId;
	}

	public void setUriId(Long uriId) {
		this.uriId = uriId;
	}

	public String getUriValue() {
		return uriValue;
	}

	public void setUriValue(String uriValue) {
		this.uriValue = uriValue;
	}
	
}
