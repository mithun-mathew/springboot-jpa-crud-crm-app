package net.mithunmathew.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mithunmathew.crud.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	// entity manager
	private EntityManager entityManager;
	
	// constructor injection
	@Autowired
	public  EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// get Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query
		List<Employee> employees = query.getResultList();
		
		// return result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// fetch employee
		Employee employee = currentSession.get(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {

		// get Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theEmployee);		
	}

	@Override
	public void deleteById(int theId) {
		
		// get Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete the employee
		Query query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		
		query.executeUpdate();
	}

}
