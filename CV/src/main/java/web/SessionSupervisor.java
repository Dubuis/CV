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
	
	@EJB
	private IConnectedUser connectedUser;
	@EJB
	private IPersons persons;
	
	@PostConstruct
	public void init() {
		cvManager = new CvManager(connectedUser, persons);
		loginManager = new LoginManager(connectedUser, persons);
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
	 * @param connectedUser the connectedUser to set
	 */
	public void setConnectedUser(IConnectedUser connectedUser) {
		this.connectedUser = connectedUser;
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
}
