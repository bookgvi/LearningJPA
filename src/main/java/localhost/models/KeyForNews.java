package localhost.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeyForNews implements Serializable {
  private int id;
  private String lang;

  public KeyForNews(int id, String lang) {
    this.id = id;
    this.lang = lang;
  }

  public KeyForNews() {
  }

  public int getId() {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getLang() {
    return this.lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }
}
