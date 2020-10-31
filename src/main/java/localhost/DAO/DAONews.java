package localhost.DAO;

import localhost.models.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DAONews {
  @PersistenceContext(unitName = "EmployeeServiceEE")
  EntityManager em;

  public News createOne(String title, int dateStamp, String lang) {
    News news = new News();
    news.setDate(dateStamp);
    news.setTitle(title);
    news.setLang(lang);
    em.persist(news);
    return news;
  }

  public List findAll() {
    Query query = em.createNamedQuery(News.FIND_ALL);
    return query.getResultList();
  }
}
