package web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

@ManagedBean
@SessionScoped
public class LoginManager {
	@EJB
	IConnectedUser connectedUser;
	
	@EJB
	IPersons persons; // For check if current isn't already connected 
	
	private String id;
	private String pwd;
	
	public String getId() {
		return id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public IConnectedUser getConnectedUser() {
		return connectedUser;
	}
	
	public boolean isConnected() {
		return connectedUser.getLogged() != null;
	}
	public boolean isNotConnected() {
		return !isConnected();
	}
	
	public void login() {
		if(!persons.isConnected(id)) {
			connectedUser.login(id, pwd);
		}
	}
	public void logout() {
		connectedUser.logout();
	}
}
