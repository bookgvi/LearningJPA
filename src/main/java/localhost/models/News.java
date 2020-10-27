package localhost.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class News {
  private KeyForNews id;
  private KeyForNews lang;
  private String title;
  private Date date;

  public void setId(KeyForNews id) {
    this.id = id;
  }

  @EmbeddedId
  public KeyForNews getId() {
    return this.id;
  }

  public void setLang(KeyForNews lang) {
    this.lang = lang;
  }

  @EmbeddedId
  public KeyForNews getLang() {
    return this.lang;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column
  public String getTitle() {
    return this.title;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Column
  public Date getDate() {
    return this.date;
  }

}
