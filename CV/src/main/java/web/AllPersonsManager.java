package web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import business.model.Person;
import web.services.interfaces.IPersons;

/**
 * @author DUBUIS Michael
 *
 */
@ManagedBean
@ApplicationScoped
public class AllPersonsManager {
	@EJB
	IPersons persons;
	
	private List<Person> registeredPersons;
	private List<Person> personsWithCv;
	
	public List<Person> getRegisteredPersons() {
		registeredPersons = persons.getPersons();
		return registeredPersons;
	}
	
	public List<Person> getPersonsWithCv() {
		personsWithCv = new ArrayList<Person>();
		for(Person p : persons.getPersons()) {
			if(p.getActivities() != null) {
				personsWithCv.add(p);
			}
		}
		return personsWithCv;
	}
}
