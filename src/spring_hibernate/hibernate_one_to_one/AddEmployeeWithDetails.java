package hibernate_one_to_one;

import hibernate_one_to_one.entity.Detail;
import hibernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class AddEmployeeWithDetails {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            Employee employee = new Employee("Oleg", "Smirnov"
                    , "Sales", 4500);
            Detail detail = new Detail("Moscow", "89160134545"
                    , "OSmirniy@mail.ru");

            employee.setEmpDetail(detail);
            session.beginTransaction();

            session.save(employee);

            session.getTransaction().commit();
            System.out.println("DONE");


        }
        finally {
            session.close();
            factory.close();
        }
    }
}
