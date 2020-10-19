package localhost.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
  @Id
  protected long id;
  protected String name;
  protected int salary;

  public Employee() {
  }

  public Employee(long id) {
    this.id = id;
  }

  public long getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public int getSalary() {
    return this.salary;
  }

  @Override
  public String toString() {
    return "Employee: \n" +
      "id: " + this.id +
      "\n name: " + this.name +
      "\n salary: " + this.salary + "\n";
  }
}
