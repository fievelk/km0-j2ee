package it.univaq.mwt.j2ee.kmZero.business;

import java.util.List;

public class ResponseGrid {

	private String sEcho;
	private long iTotalRecords;
	private long iTotalDisplayRecords;
	private List rows;
	
	public ResponseGrid(String sEcho, long iTotalRecords, long iTotalDisplayRecords, List rows) {
		super();
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.rows = rows;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public long getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
