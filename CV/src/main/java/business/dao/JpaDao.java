package business.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Service;

@Service
public class JpaDao implements IDao {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public JpaDao() {
		super();
		emf = Persistence.createEntityManagerFactory("CV");
		em = emf.createEntityManager();
		for(EntityType e : em.getMetamodel().getEntities()) {
			System.out.println(e.getName());
		}
	}
	
	@Override
	public boolean save(Object entity) {
		try {
			em.persist(entity);
		} catch (EntityExistsException
				| IllegalArgumentException
				| TransactionRequiredException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean[] save(Object... entities) {
		boolean[] booleans = new boolean[entities.length];
		for(int i = 0 ; i < entities.length ; i++) {
			booleans[i] = true;
			try {
				em.persist(entities[i]);
			} catch (EntityExistsException
					| IllegalArgumentException e) {
				e.printStackTrace();
				booleans[i] = false;
			}
			
		}
		return booleans;
	}

	@Override
	public <T> T find(Class<T> type, Serializable id) {
		return em.find(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] find(Class<T> type, Serializable... ids) {
		Object[] t = new Object[ids.length];
		for(int i = 0 ; i < ids.length ; i++) {
			t[i] = em.find(type, ids[i]);
		}
		return (T[]) t;
	}

	@Override
	public <T> List<T> findAll(Class<T> type) {
		try {
			return em.createNamedQuery("findAll", type).getResultList();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void flush() {
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public boolean remove(Object entity) {
		try {
			em.remove(entity);
		} catch (IllegalArgumentException
				| TransactionRequiredException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean[] remove(Object... entities) {
		boolean[] booleans = new boolean[entities.length];
		for(int i = 0 ; i < entities.length ; i++) {
			booleans[i] = true;
			try {
				em.remove(entities[i]);
			} catch (IllegalArgumentException
					| TransactionRequiredException e) {
				e.printStackTrace();
				booleans[i] =  false;
			}
		}
		return booleans;
	}

	@Override
	public <T> boolean removeById(Class<T> type, Serializable id) {
		T t = find(type, id);
		if(t == null) {
			return false;
		}
		return remove(t);
	}

	@Override
	public <T> boolean[] removeByIds(Class<T> type, Serializable... ids) {
		boolean[] booleans = new boolean[ids.length];
		for(int i = 0 ; i < ids.length ; i++) {
			T t = find(type, ids[i]);
			if(t == null) {
				booleans[i] = false;
			} else {
				booleans[i] = remove(t);
			}
		}
		return booleans;
	}

	@Override
	public boolean isAttached(Object entity) {
		return !em.contains(entity);
	}

	@Override
	public void refresh(Object... entities) {
		for(int i = 0 ; i < entities.length ; i++){
			em.refresh(entities[i]);
		}
	}

}
