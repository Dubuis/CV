package web;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

@ManagedBean
@SessionScoped
public class UserManager {
	@EJB(name = "ConnectedUser")
	IConnectedUser connectedUser;
	
	@EJB
	IPersons persons; // For check if current isn't already connected 
	
	private String id;
	private String pwd;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void login() {
		System.out.println(connectedUser != null);
		if(!persons.isConnected(id)) {
			connectedUser.login(id, pwd);
		}
	}
	
	public void logout() {
		System.out.println("connectedUser : " + connectedUser);
	}
	// TODO
}
