package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** Класс, реализующий добавление работника в таблицу в БД
 *  с использованием функционала Hibernate */

public class AddEmployee {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();

            Employee emp = new Employee("Julia", "Roberts"
                    , "MEDIA", 1200);

            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();

            System.out.println("DONE");
            System.out.println(emp);

        }
        finally {
            factory.close();
        }
    }
}
