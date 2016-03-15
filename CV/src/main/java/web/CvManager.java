package web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import business.model.Activity;
import business.model.Activity.ActivityType;

/**
 * @author DUBUIS Michael
 *
 */
@ManagedBean
@ApplicationScoped
public class CvManager {

	/**
	 * Constructor
	 */
	public CvManager() {
	}
	
	public List<ActivityType> getTypes() {
		List<ActivityType> list = new ArrayList<Activity.ActivityType>();
		for(ActivityType a : Activity.ActivityType.values()) {
			list.add(a);
		}
		return list;
	}
}
