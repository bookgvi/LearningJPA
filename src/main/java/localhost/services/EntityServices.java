package localhost.services;

import java.util.List;

public interface EntityServices<T> {
  T createOne(String name, int salary);
  void removeOne(long id);
  T raiseSalary(long id, int raise);
  T findOne(long id);
  List<T> findAll();
}
