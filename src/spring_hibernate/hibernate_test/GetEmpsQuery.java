package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/** Класс для получения списка работников, удовлетворяющих запросу.
 *  Для примера я получал список всех работников с именем "Timur" и
 *  зарплатой больше 1000. Таких работников в таблице 2, в результате
 *  работы метода main на экран выводятся 2 работника, удовлетворяющие
 *  параметрам запроса*/
public class GetEmpsQuery {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();


                        List<Employee> emps = session.createQuery("from Employee " +
                                "where name= 'Timur' AND salary > 1000")
                                .getResultList();

            for (Employee e: emps) {
                System.out.println(e);
            }

            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            factory.close();
        }
    }
}
