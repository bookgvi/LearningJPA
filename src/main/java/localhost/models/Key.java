package localhost.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Key implements Serializable {
  private int id;
  private String lang;

  public Key(int id, String lang) {
    this.id = id;
    this.lang = lang;
  }

  public Key() {
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
