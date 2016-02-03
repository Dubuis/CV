package web.services.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import business.model.Person;
import business.model.Activity;

@Local
@Remote
public interface IPersons {
	public List<Person> getPersons();
	public Person getPerson(String id);
	public List<Activity> getActivities(String id);
	public boolean isConnected(String id);
	public void connect(Person p);
	public void disconnect(Person p);
}
