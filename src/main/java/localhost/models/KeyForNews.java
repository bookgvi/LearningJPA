package localhost.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class KeyForNews implements Serializable {
  private int id;
  private String lang;

  public KeyForNews(int id, String lang) {
    this.id = id;
    this.lang = lang;
  }
  public KeyForNews(String lang) {
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

  @Override
  public String toString() {
    return this.id + ", " + this.lang;
  }

}
