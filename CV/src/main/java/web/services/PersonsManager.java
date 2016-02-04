package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import business.dao.IDao;
import business.dao.JpaDao;
import business.model.Activity;
import business.model.Person;
import web.services.interfaces.IPersons;

@Stateless
public class PersonsManager extends AbstractEJB implements IPersons {
	//@Autowired
	IDao dao = new JpaDao();
	
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
