package web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import business.model.Activity;
import business.model.Activity.ActivityType;
import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

/**
 * @author DUBUIS Michael
 *
 */
@ManagedBean
@ViewScoped
public class CvManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7343687619567798467L;

	@ManagedProperty("#{mail}")
	String mail;
	
	@EJB
	IPersons persons;
	
	@EJB
	IConnectedUser connectedUser;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public IConnectedUser getConnectedUser() {
		return connectedUser;
	}
	
	public List<ActivityType> getTypes() {
		List<ActivityType> list = new ArrayList<Activity.ActivityType>();
		for(ActivityType a : Activity.ActivityType.values()) {
			list.add(a);
		}
		return list;
	}
	
	public List<Activity> getActivities(ActivityType type) {
		System.out.println(mail);
		if(mail == null && connectedUser.getLogged() != null) {
			mail = connectedUser.getLogged().getMail();
		}
		List<Activity> activitiesTyped = new ArrayList<Activity>();
		for(Activity a : persons.getActivities(mail)) {
			if(a.getType().equals(type)) {
				activitiesTyped.add(a);
			}
		}
		
		Collections.sort(activitiesTyped, new Comparator<Activity>() {
			@Override
			public int compare(Activity o1, Activity o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		return activitiesTyped;
	}
	
	public String getLink(String mail) {
		String r = "showCV?faces-redirect=true&mail=" + new String(mail);
		System.out.println(r);
		return r;
	}
}
