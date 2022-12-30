package practice.activity8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import practice.activity8.entity.Employee;

public class ReadEmployeeDemo {

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
			Employee tempEmployee1 = new Employee("Jimmy", "Kun", "Philips");
			Employee tempEmployee2 = new Employee("Tommas", "Black", "Acme");
			Employee tempEmployee3 = new Employee("Sara", "Doe", "Nike");
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the Employee...");
			session.save(tempEmployee1);
			session.save(tempEmployee2);
			session.save(tempEmployee3);
			
			// commit transaction
			session.getTransaction().commit();
			
			// NEW CODE
			
			// find out employees id: primary key
			System.out.println("Save employee, Generate id:" + tempEmployee2.getId());
			
			// get a session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve employee based on ID: primary key
			System.out.println("/nGetting employee with id:" + tempEmployee2.getId());
			
			Employee myEmployee = session.get(Employee.class,tempEmployee2.getId());
			
			System.out.println("Get complete: " + myEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
