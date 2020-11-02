package localhost.services.Validators;

import localhost.models.Employee;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ListenerNotNull {
  @PrePersist
  @PreUpdate
  public void NameNotNull(Employee employee) {
    if (employee.getName() == null | employee.getName().equals(""))
      throw new IllegalArgumentException("Name could not be empty");
  }
}
