package localhost.models;

import javax.persistence.*;

@Entity
@IdClass(KeyForNews.class)
@Access(value = AccessType.PROPERTY)
@NamedQuery(name = News.FIND_ALL, query = "SELECT n FROM News n")
public class News {

  public static final String FIND_ALL = "News.findAll";

  @Id
  @Access(value = AccessType.FIELD)
  @SequenceGenerator(
    name = "news_id",
    sequenceName = "news_id_seq",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "news_id"
  )
  private int id;
  private String lang;

  private String title;
  private int dateStamp;

  public News() {}

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  @Id
  public String getLang() {
    return this.lang;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column
  public String getTitle() {
    return this.title;
  }

  public void setDate(int dateStamp) {
    this.dateStamp = dateStamp;
  }

  @Column
  public int getDate() {
    return this.dateStamp;
  }

  @Override
  public String toString() {
    return this.id + ", " + this.lang + ", " + ", " + this.title + ", " + this.dateStamp;
  }
}
