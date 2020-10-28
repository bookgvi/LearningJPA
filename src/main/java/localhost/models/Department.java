package localhost.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Department {
  @Id
  @Access(AccessType.FIELD)
  @NotNull
  @SequenceGenerator(name = "id_gen", sequenceName = "department_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "id_gen", strategy = GenerationType.SEQUENCE)
  private int id;
  private String name;
  private String description;
  private Collection<Employee> employees;

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

  @OneToMany
  @JoinColumn(name = "dep_id")
  public Collection<Employee> getEmployees() {
    return employees;
  }
  public void setEmployees(Collection<Employee> employeesID) {
    this.employees = employeesID;
  }

  @Override
  public String toString() {
    return "Employee: \n" +
      "id: " + this.id +
      "\n name: " + this.name +
      "\n description: " + this.description + "\n";
  }

}
