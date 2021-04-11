package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** В данном классе реализовано удаление работника(-ов) в соответствии с параметрами запроса.
 *  Для примера, я удалял работника по имени */
public class DeleteEmployee {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Без Query
//            Employee emp = session.get(Employee.class, 7);
//            session.delete(emp);

            session.createQuery("delete Employee " +
                    "where name = 'Julia'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            factory.close();
        }
    }
}
