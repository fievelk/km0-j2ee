package it.univaq.mwt.j2ee.kmZero.presentation.users;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class PasswordForm extends ValidatorForm{
	
	private long oid;
	private String oldPassword;
	private String newPassword;
	private String confirm_password;	
	
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
	
}