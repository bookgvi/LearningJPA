package localhost.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Department {
  @Id
  @NotNull
  @SequenceGenerator(name = "id_gen", sequenceName = "department_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "id_gen", strategy = GenerationType.SEQUENCE)
  private int id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private Collection<Employee> employees;

  /**
   * Getters & Setters
   */
  public int getId() {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }
  public  void setDescription(String description) {
    this.description = description;
  }

  public Collection<Employee> getEmployees() {
    return employees;
  }
  public void setEmployees(Collection<Employee> employeesID) {
    this.employees = employeesID;
  }

  public void addEmployee(Employee employee) {
    this.employees.add(employee);
  }
  public void deleteEmployee(Employee employee) {
    this.employees.remove(employee);
  }

  @Override
  public String toString() {
    return
      "\n id: " + this.id +
      "\n name: " + this.name +
      "\n description: " + this.description +
      "\n employees: [" + this.employees.stream().map(Employee::getId) + "]\n";
  }

}
