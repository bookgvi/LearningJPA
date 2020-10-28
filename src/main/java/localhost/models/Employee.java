package localhost.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Employee {
  /**
   * entityId = START_WITH - allocationSize + INCREMENT_BY
   *
   * @SequenceGenerator( name="email-seq-gen",
   * sequenceName="EMAIL_SEQ_GEN",
   * allocationSize=500
   * ) // initialValue=1 (default)
   * <p>
   * CREATE SEQUENCE EMAIL_SEQ_GEN START WITH 1 INCREMENT BY 500;
   * <p>
   * produces
   * <p>
   * entityId = 1 - 500 + 1 = -500 // EclipseLink error
   */
  @Id
  @SequenceGenerator(
    name = "employee_id",
    sequenceName = "employee_id_seq",
    allocationSize = 1
  )
  @GeneratedValue(generator = "employee_id", strategy = GenerationType.SEQUENCE)
  private long id;
  @NotNull
  private String name;
  @Min(0)
  private int salary;

  @NotNull
  @Column(name = "dep_id")
  private int depId;

//  @ManyToOne
//  @JoinColumn(name = "dep_id")
//  private Department department;

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

  public int getDepId() {
    return this.depId;
  }

  public void setDepId(int id) {
    this.depId = id;
  }

//  public Department getDepartment() {
//    return this.department;
//  }
//  public void setDepartment(Department department) {
//    this.department = department;
//  }

  @Override
  public String toString() {
    return "Employee: \n" +
      "id: " + this.id +
      "\n name: " + this.name +
      "\n salary: " + this.salary + "\n";
  }

  @Override
  public int hashCode() {
    int prime = 666;
    int hash = 777;
    hash = prime * hash + (int) this.id;
    hash = prime * hash + Objects.hashCode(this.name);
    hash = prime * hash + this.salary;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    final Employee someEmp = (Employee) obj;
    if (this.id != someEmp.id) return false;
    if (!this.name.equals(someEmp.name)) return false;
    if (this.salary != someEmp.salary) return false;
    return true;
  }
}
