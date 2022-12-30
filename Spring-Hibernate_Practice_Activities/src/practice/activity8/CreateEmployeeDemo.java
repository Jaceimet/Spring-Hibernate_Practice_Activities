package practice.activity8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import practice.activity8.entity.Employee;


public class CreateEmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Employee.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a employee object
			System.out.println("Creating a new employee object...");
			Employee tempEmployee = new Employee("Jose", "Juan", "Costco");
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the Employee...");
			session.save(tempEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
