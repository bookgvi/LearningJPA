package localhost.models;

import com.google.gson.Gson;
import localhost.services.Validators.ListenerNotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Objects;

@Entity
@NamedQueries({
  @NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e"),
  @NamedQuery(name = Employee.GET_BY_DEPARTMENT, query = "SELECT e FROM Employee e WHERE e.department = :department")
})
@EntityListeners({ ListenerNotNull.class })
public class Employee {

  public static final String GET_BY_DEPARTMENT = "Employee.GET_BY_DEPARTMENT";
  public static final String FIND_ALL = "Employee.findAll";

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
  @ManyToOne
  @JoinColumn(name = "dep_id")
  private Department department;

  @Transient
  private transient int depId;

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

  public Department getDepartment() {
    return this.department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public HashMap<String, Object> getMapForJson() {
    HashMap<String, Object> employeeMap = new HashMap<>();

    employeeMap.put("id", this.id);
    employeeMap.put("name", this.name);
    employeeMap.put("salary", this.salary);
    employeeMap.put("department", this.department.getMapForJson());
    return employeeMap;
  }

  public String toJson() {
    Gson gson = new Gson();
    HashMap<String, Object> employeeMap = this.getMapForJson();
    return gson.toJson(employeeMap);
  }

  @Override
  public String toString() {
    return
      "\n id: " + this.id +
        "\n name: " + this.name +
        "\n salary: " + this.salary +
        "\n department: {" + this.department.toString() + "} \n";
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
