package practice.activity8;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import practice.activity8.entity.Employee;

public class QueryEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create session
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query employee
			List<Employee> theEmployees = session.createQuery("from Employee").list();
			
			//display employee
			displayEmployees(theEmployees);
			
			// query employee: company = 'acme'
			theEmployees = session.createQuery("from Employee s where s.company='acme'").list();
			
			//display the employee
			System.out.println("\n\nStudents who work for the company ACME");
			displayEmployees(theEmployees);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}
		finally {
			factory.close();
		}
		
		

	}

	private static void displayEmployees(List<Employee> theEmployees) {
		for (Employee tempEmployee : theEmployees) {
			System.out.println(tempEmployee);
		}
	}

}
