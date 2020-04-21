package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training")
    private Integer idTraining;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "id_training"),inverseJoinColumns = @JoinColumn(name = "emp_id"))
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Training() {
    }

    public Training(String name) {
        this.name = name;
    }

    public Integer getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Integer idTraining) {
        this.idTraining = idTraining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Training{" +
                "idTraining=" + idTraining +
                ", name='" + name + '\'' +
                '}';
    }

    public void addEmployee(Employee employee){
        if(employees == null){
            employees = new ArrayList<>();
        } else {
          employees.add(employee);
        }
    }



}