package web;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import web.services.interfaces.IConnectedUser;
import web.services.interfaces.IPersons;

@ManagedBean
@SessionScoped
public class SessionSupervisor {
	
	private CvManager cvManager;
	private LoginManager loginManager;
	private RegistrationManager registrationManager;
	
	@EJB
	private IConnectedUser connectedUser;
	@EJB
	private IPersons persons;
	
	@PostConstruct
	public void init() {
		cvManager = new CvManager(this);
		loginManager = new LoginManager(this);
		setRegistrationManager(new RegistrationManager(this));
	}

	/**
	 * @return the cvManager
	 */
	public CvManager getCvManager() {
		return cvManager;
	}

	/**
	 * @param cvManager the cvManager to set
	 */
	public void setCvManager(CvManager cvManager) {
		this.cvManager = cvManager;
	}

	/**
	 * @return the loginManager
	 */
	public LoginManager getLoginManager() {
		return loginManager;
	}

	/**
	 * @param loginManager the loginManager to set
	 */
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
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
	
	public String indexIfNotConnected() throws IOException {
		if(connectedUser.getLogged() == null) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("index.xhtml");
            System.out.println("redirected");
		}
		else System.out.println("connected");
		return "";
	}
	
	public String indexIfConnected() throws IOException {
		if(connectedUser.getLogged() == null) System.out.println("connected");
		else{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("index.xhtml");
            System.out.println("redirected");
		}
		return "";
	}
	
	public String redirectMyCv() {
		return "myCV?faces-redirect=true";
	}

	public RegistrationManager getRegistrationManager() {
		return registrationManager;
	}

	public void setRegistrationManager(RegistrationManager registrationManager) {
		this.registrationManager = registrationManager;
	}

}
