package localhost.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {
  /**
   * entityId = START_WITH - allocationSize + INCREMENT_BY
   *
   * @SequenceGenerator(
   *     name="email-seq-gen",
   *     sequenceName="EMAIL_SEQ_GEN",
   *     allocationSize=500
   * ) // initialValue=1 (default)
   *
   * CREATE SEQUENCE EMAIL_SEQ_GEN START WITH 1 INCREMENT BY 500;
   *
   *  produces
   *
   * entityId = 1 - 500 + 1 = -500 // EclipseLink error
   *
   */
  @Id
  @SequenceGenerator(
    name = "employee_id",
    sequenceName = "employee_id_seq",
    allocationSize = 1
  )
  @GeneratedValue(generator ="employee_id", strategy = GenerationType.SEQUENCE)
  private long id;
  @NotNull
  private String name;
  @Min(0)
  private int salary;

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
