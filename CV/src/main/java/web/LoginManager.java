package web;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import business.model.Activity;
import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

public class LoginManager {
	IConnectedUser connectedUser;
	IPersons persons; // For check if current isn't already connected 
	
	private String id;
	private String pwd;
	
	public LoginManager(SessionSupervisor supervisor) {
		this.connectedUser = supervisor.getConnectedUser();
		this.persons = supervisor.getPersons();
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

	public void newActivity() {
		System.out.println(connectedUser.getLogged());
		Activity activity = new Activity();
		activity.setDate(new Date(System.currentTimeMillis()));
		connectedUser.addActivity(activity);
	}
}
