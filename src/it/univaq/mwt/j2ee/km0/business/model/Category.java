package it.univaq.mwt.j2ee.km0.business.model;

public class Category {
	
	private long oid;
	private String name;
	
	public Category(long oid, String name) {
		super();
		this.oid = oid;
		this.name = name;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
