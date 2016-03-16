package web;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.model.Activity;
import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

@ManagedBean
@SessionScoped
public class UserManager {
	@EJB(name = "ConnectedUser")
	IConnectedUser connectedUser;
	
	@EJB
	IPersons persons; // For check if current isn't already connected 
	
	
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
}
