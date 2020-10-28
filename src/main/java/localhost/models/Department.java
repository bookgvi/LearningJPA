package localhost.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Department {
  @Id
  @Access(AccessType.FIELD)
  private int id;
  private String name;
  private String description;
  private Collection<Employee> employeesID;

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

  @OneToMany(mappedBy = "department")
  public Collection<Employee> getEmployeesID() {
    return employeesID;
  }
  public void setEmployeesID(Collection<Employee> employeesID) {
    this.employeesID = employeesID;
  }

  @Override
  public String toString() {
    return "Employee: \n" +
      "id: " + this.id +
      "\n name: " + this.name +
      "\n description: " + this.description + "\n";
  }

}
