package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
	
	@PostConstruct
	public void init() {
		System.out.println(this + " created");
	}
	
	@PreDestroy
	public void close() {
		System.out.println(this + " destroy");
	}

	@Override
	public List<Person> getPersons() {
		dao.refresh(dao.findAll(Person.class).toArray());
		return dao.findAll(Person.class);
	}

	@Override
	public Person getPerson(String id) {
		dao.refresh(dao.find(Person.class, id));
		return dao.find(Person.class, id);
	}

	@Override
	public List<Activity> getActivities(String id) {
		dao.refresh(dao.find(Person.class, id));
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

	@Override
	public void addPerson(Person P){
		dao.save(P);
		dao.flush();
	}

}
