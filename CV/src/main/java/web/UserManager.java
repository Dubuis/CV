package web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.model.Activity;
import business.model.Activity.ActivityType;
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
	
	/**
	 * @return the connectedUser
	 */
	public IConnectedUser getConnectedUser() {
		return connectedUser;
	}
	
	/**
	 * @return the persons
	 */
	public IPersons getPersons() {
		return persons;
	}

	public void login() {
		if(!persons.isConnected(id)) {
			connectedUser.login(id, pwd);
		}
	}
	
	public void logout() {
		connectedUser.logout();
	}
	

	public List<Activity> getCV() {
		if(connectedUser.getLogged() != null) {
			return connectedUser.getActivities();
		}
		return null;
	}
	
	public String getEmail() {
		if(connectedUser.getLogged() != null) {
			return connectedUser.getLogged().getMail();
		}
		return null;
	}
	
	public void newActivity() {
		Activity activity = new Activity();
		activity.setDate(new Date(System.currentTimeMillis()));
		connectedUser.addActivity(activity);
	}
	
	public void save() {
		connectedUser.save();
	}
}
