package web.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import business.model.Activity;
import business.model.Person;

@Local
public interface IPersons {
	public List<Person> getPersons();
	public Person getPerson(String id);
	public List<Activity> getActivities(String id);
	public boolean isConnected(String id);
	public void connect(Person p);
	public void disconnect(Person p);
}
