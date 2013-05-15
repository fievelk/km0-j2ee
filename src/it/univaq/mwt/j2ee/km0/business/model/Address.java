package it.univaq.mwt.j2ee.km0.business.model;

public class Address {
	
	private long oid;
	private String address;
	private boolean is_default;
	//private double latitude;
	//private double longitude;
	

	public Address(long oid, String address, boolean is_default) {
		super();
		this.oid = oid;
		this.address = address;isahdiuhasd
		this.is_default = is_default;
	}

	public Address() {
		super();
	}

	public long getOid() {
		return oid;
	}
	
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isIs_default() {
		return is_default;
	}
	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}
	
	

}
