package web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedProperty;

import business.model.Activity;
import business.model.Activity.ActivityType;
import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

public class CvManager implements Serializable {
	private static final long serialVersionUID = 7343687619567798467L;

	@ManagedProperty("#{mail}")
	String mail;
	
	IPersons persons;
	
	IConnectedUser connectedUser;
	
	public CvManager(IConnectedUser connectedUser, IPersons persons) {
		super();
		this.connectedUser = connectedUser;
		this.persons = persons;
	}
	
	@PostConstruct
	public void init() {
		System.out.println(this + " created");
	}
	
	@PreDestroy
	public void close() {
		System.out.println("\nINFO : ");
		System.out.println("connectedUSer.logged : " + connectedUser.getLogged());
		System.out.println();
		System.out.println(this + " destroyer");
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public List<ActivityType> getTypes() {
		List<ActivityType> list = new ArrayList<Activity.ActivityType>();
		for(ActivityType a : Activity.ActivityType.values()) {
			list.add(a);
		}
		return list;
	}
	
	public List<Activity> getActivities(ActivityType type) {
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
	
	public void newActivity() {
		if(connectedUser.getLogged() == null) {
			return;
		}
		Activity newActivity = new Activity();
		connectedUser.addActivity(newActivity);
	}
	
	public void addActivity(Activity a) {
		if(connectedUser.getLogged() == null) {
			return;
		}
		connectedUser.addActivity(a);
		connectedUser.save();
	}
}
