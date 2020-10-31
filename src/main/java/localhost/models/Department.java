package localhost.models;

import com.google.gson.Gson;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;

@Entity
public class Department {
  @Id
  @NotNull
  @SequenceGenerator(name = "id_gen", sequenceName = "department_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "id_gen", strategy = GenerationType.SEQUENCE)
  private int id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "department") //, orphanRemoval = true)
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

  public HashMap<String, Object> getMapForJson() {
    HashMap<String, Object> departmentMap = new HashMap<>();
    departmentMap.put("id", this.id);
    departmentMap.put("name", this.name);
    departmentMap.put("description", this.description);
    return departmentMap;
  }
  public String toJson() {
    HashMap<String, Object> departmentMap = this.getMapForJson();
    Gson gson = new Gson();
    return gson.toJson(departmentMap);
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
