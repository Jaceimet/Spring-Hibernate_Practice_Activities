package practice.activity8;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import practice.activity8.entity.Employee;

public class UpdateEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int employeeId = 1;
			
			// get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve employee based on the id: primary key
			System.out.println("\nGetting employee with id: " + employeeId);
			
			Employee myEmployee = session.get(Employee.class, employeeId);
			
			System.out.println("Updating Employee...");
			myEmployee.setFirstName("Darwin");
			
			
			// Query modified employees List
			List<Employee> theEmployees = session.createQuery("from Employee").list();		
			
			// display the Employees
			displayEmployee(theEmployees);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
						
			
		}
		finally {
			factory.close();
		}

	}

	private static void displayEmployee(List<Employee> theEmployees) {
		for (Employee tempEmployee : theEmployees) {
			System.out.println(tempEmployee);
		}
	}

}
