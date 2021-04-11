package hibernate_one_to_many_bi;

import hibernate_one_to_many_bi.entity.Department;
import hibernate_one_to_many_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestOneToManyBi {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();


        Session session = null;

/**     Добавление департамента с работниками в БД в соответствущие таблицы */

        try {
            session = factory.getCurrentSession();

            Department dep = new Department("Sales", 800
                    , 1500);
            Employee emp1 = new Employee("Nikita", "Korochkin"
                    , 800);
            Employee emp2 = new Employee("Elena", "Smirnova"
                    , 1500);
            Employee emp3 = new Employee("Anton", "Sidorov"
                    , 1000);

            dep.addEmployeeToDepartment(emp1);
            dep.addEmployeeToDepartment(emp2);
            dep.addEmployeeToDepartment(emp3);

            session.beginTransaction();

            session.save(dep);

            session.getTransaction().commit();
            System.out.println("DONE");
        }

/**     Получение списка всех работников из департамента */

         try {
            session = factory.getCurrentSession();

            session.beginTransaction();

            System.out.println("Get department");
            Department department = session.get(Department.class, 4);

            System.out.println("Show department");
            System.out.println(department);

            System.out.println("Подгрузим наших работников");
            department.getEmps().get(0);

            session.getTransaction().commit();
            System.out.println("Show employees from department");
            System.out.println(department.getEmps());
            System.out.println("DONE");
        }

/**      Удаление работника */

         try {
            session = factory.getCurrentSession();

            session.beginTransaction();
            Employee employee = session.get(Employee.class, 4);

            session.delete(employee);

            session.getTransaction().commit();
            System.out.println("DONE");
        }

        finally {
            session.close();
            factory.close();
        }
    }
}
