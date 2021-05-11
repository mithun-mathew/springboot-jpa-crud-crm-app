package net.mithunmathew.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mithunmathew.crud.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	// entity manager
	private EntityManager entityManager;
	
	// constructor injection
	@Autowired
	public  EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// create query
		Query query = entityManager.createQuery("from Employee");
		
		// execute and get result
		List<Employee> employees = query.getResultList();
		
		// return result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get employee
		Employee employee = entityManager.find(Employee.class, theId);
		
		// return employee		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		// save or update employee
		Employee employee = entityManager.merge(theEmployee);
		
		// get id from db
		theEmployee.setId(employee.getId());
	}

	@Override
	public void deleteById(int theId) {
		
		// create query
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		
		query.executeUpdate();
	}

}
