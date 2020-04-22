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


        int id = 2;

        session.beginTransaction();

        String training = "from Training";

        Query query = session.createQuery(training);
        List<Training> resultList = query.getResultList();

        for (Training trainingObject : resultList){
            System.out.println(trainingObject.getName() + "has employeeId: " + trainingObject.getIdTraining() + "is done by employees: " + trainingObject.getEmployees());
        }

        Training training2 = new Training("java training");

        Employee employee = session.get(Employee.class, 127);
        Employee employee1 = session.get(Employee.class, 104);

        training2.addEmployee(employee);
        training2.addEmployee(employee1);

        session.persist(training2);

        int id_training = 10;

        Training trainingId10 = session.get(Training.class, id_training);

        System.out.println(training);
        for (Employee employeeId10 : trainingId10.getEmployees()){
            System.out.println("- " + employeeId10);
        }

        String trainingQuery = "from Training";
        Query query1 = session.createQuery(trainingQuery);
        List<Training> resultList1 = query1.getResultList();

        for (Training training1 : resultList1){
            System.out.println(training1.getName() + " is done by employees: " + training1.getEmployees());
        }

        session.getTransaction().commit();

        factory.close();
    }

}
