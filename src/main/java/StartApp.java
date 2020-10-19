import localhost.models.Employee;
import localhost.services.employeeService.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StartApp {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
    EntityManager em = emf.createEntityManager();
    EmployeeService es = new EmployeeService(em);

    EntityTransaction transaction = em.getTransaction();

    transaction.begin();
    Employee employee = es.createOne("Ivan", 1200);
    transaction.commit();
    System.out.printf("New Employee: %s%n", employee.toString());

    em.close();
    emf.close();
  }
}
