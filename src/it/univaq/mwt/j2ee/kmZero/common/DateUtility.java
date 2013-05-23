package it.univaq.mwt.j2ee.kmZero.common;

public class DateUtility {

	public static java.sql.Date convertUtilToSql(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static java.util.Date convertSqlToUtil(java.sql.Date date) {
		return new java.util.Date (date.getTime());
	}
	
}
