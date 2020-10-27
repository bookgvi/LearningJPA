package localhost.services.newsService;

import localhost.models.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
