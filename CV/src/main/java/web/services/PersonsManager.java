package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.springframework.beans.factory.annotation.Autowired;

import business.model.Person;
import web.services.interfaces.IPersons;
import business.dao.IDao;
import business.model.Activity;

@Stateless
public class PersonsManager extends AbstractEJB implements IPersons {
	@Autowired
	IDao dao;
	
	private List<String> connectedUsers;
	
	public PersonsManager() {
		super();
		connectedUsers = new ArrayList<String>();
	}

	@Override
	public List<Person> getPersons() {
		return dao.findAll(Person.class);
	}

	@Override
	public Person getPerson(String id) {
		return dao.find(Person.class, id);
	}

	@Override
	public List<Activity> getActivities(String id) {
		return dao.find(Person.class, id).getActivities();
	}

	@Override
	public boolean isConnected(String id) {
		return connectedUsers.contains(id);
	}

	@Override
	public void connect(Person p) {
		connectedUsers.add(p.getMail());
	}

	@Override
	public void disconnect(Person p) {
		connectedUsers.remove(p.getMail());
	}

}
