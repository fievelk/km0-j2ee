package it.univaq.mwt.j2ee.kmZero.presentation.users;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class PasswordForm extends ValidatorForm{
	
	private long oid;
	private String password; // Password vecchia del DB
	private String oldPass; // Password vecchia del form
	private String newPass;
	private String confirmPass;	
	
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
}