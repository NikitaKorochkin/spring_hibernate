package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** Данный класс позволяет нам менять значение полей работников в таблице employees
 *  в соответствии с указанными в запросе параметрами. Для примера я устанавливал
 *  значение зарплаты 1000 для работника с id = 1.
 *  */
public class SetField {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Способ без использования Query
//            Employee emp = session.get(Employee.class, 1);
//            emp.setSalary(4500);

            session.createQuery("update Employee set salary = 1000 where id = 1")
            .executeUpdate();

            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            factory.close();
        }
    }
}
