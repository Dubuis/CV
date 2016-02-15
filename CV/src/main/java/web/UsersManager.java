package web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import business.model.Person;
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
	
	public List<Person> getPersonsWithCV() {
		List<Person> l = new ArrayList<Person>();
		for(Person p : persons.getPersons()) {
			if(!p.getActivities().isEmpty()) {
				l.add(p);
			}
		}
		return l;
	}
}
