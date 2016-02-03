package web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import web.services.interfaces.IPersons;

@ManagedBean
public class UsersManager {
	@EJB
	IPersons persons;

	/**
	 * @return the persons
	 */
	public IPersons getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(IPersons persons) {
		this.persons = persons;
	}
	
	// TODO
}
