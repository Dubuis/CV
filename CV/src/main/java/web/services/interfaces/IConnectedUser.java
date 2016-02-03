package web.services.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import business.model.Person;
import business.model.Activity;

@Local
public interface IConnectedUser {
	public void login(String mail, String password);
	public void logout();
	
	public Person getLogged();
	public void addActivity(Activity activity);
	public boolean removeActivity(Activity activity);
	public List<Activity> getActivities();
	
	public void save();
	
	public void test();
}
