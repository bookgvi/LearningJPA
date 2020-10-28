package localhost.services.WEB;

import localhost.models.Department;
import org.hibernate.loader.custom.CustomQuery;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DepartmentWEB {
  @PersistenceContext
  EntityManager em;

  public Department findOne(int id) {
    return em.find(Department.class, id);
  }

  public List<Department> findAll() {
    TypedQuery<Department> query = em.createQuery("SELECT d from Department d", Department.class);
    return query.getResultList();
  }

  public Department createOne(String name, String description) {
    Department department = new Department();
    department.setDescription(description);
    department.setName(name);
    em.persist(department);
    return department;
  }

  public Department changeOne(int id, String name, String description) {
    Department department = this.findOne(id);
    if (department != null) {
      department.setName(name);
      department.setDescription(description);
//      em.persist(department);
    }
    return department;
  }

  public void deleteOne(int id) {
    Department department = this.findOne(id);
    if (department != null) {
      em.remove(department);
    }
  }
}
