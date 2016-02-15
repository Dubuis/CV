package business.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.model.Activity;
import business.model.Activity.ActivityType;
import business.model.Person;



public class TestJpaDao {
	IDao dao = new JpaDao();
	
	Person person;
	
	@BeforeClass
	public void SetUp(){
		person = new Person();
		person.setMail("email@email.fr");
		person.setPassword("password");
		
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
		ArrayList<Activity> activities = new ArrayList<Activity>();
		activities.add(a1);
		activities.add(a2);
		person.setActivities(activities);
		// Ajout de la personne
		dao.save(person);
		dao.flush();
	}
	
	
	@Test
	public void testFindPersonne() {
		// Verification 
		Person find = dao.find(Person.class, "email@email.fr");
		assertTrue(person.equals(find));
	}
	
	@Test 
	void testFindAllPerson(){
		List<Person> Plist = dao.findAll(Person.class);
		assertTrue(Plist.size() > 0);
	}
	
	@Test
	void testRemovePersonne(){
		Person person1 = new Person();
		// on ajoute une nouvelle personne
		person1.setMail("email2@email.fr");
		dao.save(person1);
		dao.flush();
		
		// on supprime la personne 
		dao.remove(person1);
		
		// on verifie la bonne supression
		Person find = dao.find(Person.class, "email@email.fr");
		assertNull(find);
	}
	
	@Test
	void testRemoveByID(){
		Person person2 = new Person();
		// on ajoute une nouvelle personne
		person2.setMail("email4@email.fr");
		dao.save(person2);
		dao.flush();
		
		// on supprime la personne 
		dao.removeById(Person.class,"email4@email.fr");
		
		// on verifie la bonne supression
		Person find = dao.find(Person.class, "email4@email.fr");
		assertNull(find);
	}

}