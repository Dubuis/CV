package web;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import business.model.Activity;
import business.model.Person;
import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

public class RegistrationManager {
	
	IPersons persons;
	IConnectedUser connectedUser;
	
	private String id;
	private String pwd;
	private String pwd2;
	
	public RegistrationManager(SessionSupervisor supervisor) {
		super();
		this.persons = supervisor.getPersons();
		this.connectedUser = supervisor.getConnectedUser();
	}
	
	@PostConstruct
	public void init() {
		System.out.println(this + " created");
	}
	
	@PreDestroy
	public void close() {
		System.out.println("\nINFO : ");
		System.out.println(this + " destroyer");
	}
	
	public String addPerson (){
		try {
			if(connectedUser.getLogged() != null) {
				return "";
			}
			if(pwd.equals(pwd2)){
				Person P1 = new Person();
				P1.setMail(id);
				P1.setPassword(pwd);
				persons.addPerson(P1);
				return "index?faces-redirect=true";
			}
			FacesMessage message = new FacesMessage( "les mots de passe sont différents" );
			FacesContext.getCurrentInstance().addMessage("erreurpwd", message );
			return "";
		}
		catch (Exception e) {
			FacesMessage message = new FacesMessage( "l'identifiant mail est déjà existant" );
			FacesContext.getCurrentInstance().addMessage("erreurid", message );
		}
		return "";
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
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	
}