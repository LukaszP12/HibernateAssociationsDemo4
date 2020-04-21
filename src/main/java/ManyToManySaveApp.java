import Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ManyToManySaveApp {

    public static void main(String[] args) {
        // stworzyc obiekt Configuration
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        // wczytanie adnotacji
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);
        conf.addAnnotatedClass(Department.class);
        conf.addAnnotatedClass(Employee.class);
        conf.addAnnotatedClass(Training.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();
        //Pobranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Training training = new Training("sales training");

        Employee employee = new Employee("Johnny","Depp",16000);
        Employee employee1 = new Employee("Miley","Cirus",16000);

        training.addEmployee(employee);
        training.addEmployee(employee1);

        session.persist(training);

        session.getTransaction().commit();

        factory.close();

    }
}
