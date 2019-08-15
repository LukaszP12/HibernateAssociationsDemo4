import Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ManyToManyGetApp {

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


        int id =2;

        session.beginTransaction();

        String getTraining = "from Training";

        Query query = session.createQuery(getTraining);

        List<Training> resultList = query.getResultList();

        for (Training training : resultList){
            System.out.println("\n" + training);
        }

  /*    Training training = new Training("java training");

        Employee employee = session.get(Employee.class, 120);
        Employee employee1 = session.get(Employee.class, 121);

        training.addEmployee(employee);
        training.addEmployee(employee1);

        session.persist(training); */



 /*       Training training = session.get(Training.class, id);

        System.out.println(training);
        for (Employee employee : training.getEmployees()){
            System.out.println("- " + employee);
        } */


        session.getTransaction().commit();

        factory.close();
    }

}
