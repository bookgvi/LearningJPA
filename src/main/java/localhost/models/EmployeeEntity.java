package localhost.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "learningjpa")
public class EmployeeEntity {
  private Long id;
  private String name;
  private Integer salary;
  private DepartmentEntity departmentByDepId;

  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "salary")
  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeEntity that = (EmployeeEntity) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(name, that.name) &&
      Objects.equals(salary, that.salary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, salary);
  }

  @ManyToOne
  @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
  public DepartmentEntity getDepartmentByDepId() {
    return departmentByDepId;
  }

  public void setDepartmentByDepId(DepartmentEntity departmentByDepId) {
    this.departmentByDepId = departmentByDepId;
  }
}
