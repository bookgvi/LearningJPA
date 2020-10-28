package localhost.resources;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ResourceDepartment {
  @PersistenceContext
  EntityManager em;
}
