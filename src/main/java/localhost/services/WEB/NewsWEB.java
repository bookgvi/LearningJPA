package localhost.services.WEB;

import localhost.models.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class NewsWEB {
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

  public List<News> findAll() {
    TypedQuery<News> query = em.createQuery("SELECT n FROM News n", News.class);
    return query.getResultList();
  }
}
