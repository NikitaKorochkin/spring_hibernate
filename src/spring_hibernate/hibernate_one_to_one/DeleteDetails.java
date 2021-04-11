package hibernate_one_to_one;

import hibernate_one_to_one.entity.Detail;
import hibernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDetails {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            session.beginTransaction();

            Detail detail = session.get(Detail.class, 1);
//          detail.getEmployee().setEmpDetail(null);   -   таким образом мы можем удалить детали работника из базы данных, не удалив при этом самого работника
            session.delete(detail);

            session.getTransaction().commit();
            System.out.println("DONE");

        }
        finally {
            session.close();
            factory.close();
        }
    }
}
