package business.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import business.model.Activity;
import business.model.Activity.ActivityType;
import business.model.Person;

public class TestJpaDao {
	IDao dao = new JpaDao();
	
	@Test
	public void test() {
		Person p = new Person();
		p.setMail("email@email.fr");
		p.setPassword("password");
		Activity a1 = new Activity();
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONDAY, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 3);
		calendar.set(Calendar.YEAR, 1988);
		d = calendar.getTime();
		a1.setDate(d);
		a1.setDescription("Je suis né");
		a1.setTitle("Naissance");
		a1.setType(ActivityType.other);
		a1.setWebAddress("http://nulpart.com/");
		Activity a2 = new Activity();
		a2.setDate(new Date(System.currentTimeMillis()));
		a2.setDescription("Je suis en train de booser");
		a2.setTitle("Aujourd'hui");
		a2.setType(ActivityType.other);
		a2.setWebAddress("NA");
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(a1);
		activities.add(a2);
		p.setActivities(activities);
		
		dao.save(p);
		dao.flush();
		
		Person find = dao.find(Person.class, "email@email.fr");
		
		assertTrue(p.equals(find));
		
		List<Person> persons = dao.findAll(Person.class);
		assertTrue(persons.contains(p));
		
		dao.remove(p);
		dao.flush();
		
		find = dao.find(Person.class, "email@email.fr");
		
		assertNull(find);
	}

}
