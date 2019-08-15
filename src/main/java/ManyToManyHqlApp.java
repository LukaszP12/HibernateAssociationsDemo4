import Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ManyToManyHqlApp {

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

       int minEmployeeNumber = 6;

       int trainingNumber = 1;
       int maxSalary = 12000;

       String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber";
       String course = "javascript";
       String getEmployee = "select e from Employee e join e.trainings t where t.name=:course";

       String getEmployee2 = "select e from Employee e where size(e.trainings) =: trainingNumber and e.salary < :maxSalary";

       session.beginTransaction();

       Query query = session.createQuery(getEmployee2);
         query.setParameter("trainingNumber",trainingNumber);
         query.setParameter("maxSalary",maxSalary);
    //   query.setParameter("course", course);
    //   Query query = session.createQuery(getTraining);
    //   query.setParameter("minEmployeeNumber", minEmployeeNumber);

      // List<Training> resultList = query.getResultList();
        List<Employee> resultList = query.getResultList();

  /*      for (Training training : resultList){
           System.out.println(training);
       } */

        for (Employee employee : resultList){
            System.out.println(employee);
        }

       session.getTransaction().commit();

       factory.close();
    }

}
