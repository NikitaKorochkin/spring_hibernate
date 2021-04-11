package hibernate_many_to_many;

import hibernate_many_to_many.entity.Child;
import hibernate_many_to_many.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** При таком отношении таблицы связываются при помощи вспомогательной таблицы  */
public class TestManyToMany {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        Session session = null;

/**        Добавление объектов в БД в соответствующие таблицы для последнего прописанного cascade

        try {
            session = factory.getCurrentSession();

            Section section1 = new Section("Dance");
            Child child1 = new Child("Olga", 12);
            Child child2 = new Child("Grisha", 8);
            Child child3 = new Child("Pasha", 9);

            section1.addChildToSection(child1);
            section1.addChildToSection(child2);
            section1.addChildToSection(child3);

            session.beginTransaction();

            session.persist(section1);

            session.getTransaction().commit();
            System.out.println("DONE!");
        }
 */

/**          Добавление объектов в БД в соответствующие таблицы для CascadeType.ALL
        try {
            session = factory.getCurrentSession();

            Section section1 = new Section("Volleyball");
            Section section2 = new Section("Chess");
            Section section3 = new Section("Math");
            Child child1 = new Child("Igor", 10);

            child1.addSectionToChild(section1);
            child1.addSectionToChild(section2);
            child1.addSectionToChild(section3);

            session.beginTransaction();

            session.save(child1);

            session.getTransaction().commit();
            System.out.println("DONE!");
        }
 */

/**     Получение списка объектов класса Child из таблицы Section
        try {
            session = factory.getCurrentSession();

            session.beginTransaction();

            Section section = session.get(Section.class, 3);

            System.out.println(section);
            System.out.println(section.getChildren());

            session.getTransaction().commit();
            System.out.println("DONE!");
        }
*/

/**     Получение списка объектов класса Section из таблицы Child

        try {
            session = factory.getCurrentSession();

            session.beginTransaction();

            Child child = session.get(Child.class, 10);

            System.out.println(child);
            System.out.println(child.getSections());

            session.getTransaction().commit();
            System.out.println("DONE!");
        }
*/

/**     Удаление объекта класса Child из таблицы Section

        try {
            session = factory.getCurrentSession();

            session.beginTransaction();

            Child child = session.get(Child.class, 10);

            session.delete(child);

            session.getTransaction().commit();
            System.out.println("DONE!");
        }
 */

        finally {
            session.close();
            factory.close();
        }
    }
}
