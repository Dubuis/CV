package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

import business.dao.IDao;
import business.dao.JpaDao;
import business.model.Activity;
import business.model.Activity.ActivityType;
import business.model.Person;
import web.services.interfaces.IConnectedUser;

@Stateful
public class ConnectedUser extends AbstractEJB implements IConnectedUser {
	//@Autowired
	IDao dao = new JpaDao();
	
	private Person logged = null;
	
	public ConnectedUser() {
		super();
	}
	
	public void test() {
		System.out.println(dao != null);
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
	public void login(String mail, String password) {
		if(logged != null) {
			logout();
		}
		if(mail == null || mail.isEmpty()
				|| password == null || password.isEmpty()) {
			return;
		}
		Person p = dao.find(Person.class, mail);
		if(p != null && p.getPassword().equals(password)) {
			logged = p;
		}
	}

	@Override
	public void logout() {
		dao.flush();
		logged = null;
	}

	@Override
	public Person getLogged() {
		return logged;
	}

	@Override
	public void addActivity(Activity activity) {
		if(logged == null) {
			return;
		}
		if(activity == null) {
			return;
		}
		List<Activity> activities = logged.getActivities();
		if(activities == null) {
			activities = new ArrayList<Activity>();
		}
		if(activities.contains(activity)) {
			activities.remove(activity);
		}
		activities.add(activity);
	}

	@Override
	public boolean removeActivity(Activity activity) {
		if(logged == null) {
			return false;
		}
		if(activity == null) {
			return false;
		}
		return logged.getActivities().remove(activity);
	}
	
	@Override
	public List<Activity> getActivities() {
		if(logged == null) {
			return null;
		}
		return logged.getActivities();
	}
	
	@Override
	public List<Activity> getActivities(ActivityType type) {
		if(logged == null) {
			return null;
		}
		ArrayList<Activity> list = new ArrayList<Activity>();
		for(Activity a : logged.getActivities()) {
			if(a.getType().equals(type)) {
				list.add(a);
			}
		}
		return list;
	}

	@Override
	public void save() {
		dao.flush();
	}

}
