package localhost.services.Validators;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@RequestScoped
public class ValidatorsBean {
  public void requireNonNul(Object field, String msg) {
    if (field == null) {
      throw new WebApplicationException(
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(msg)
          .build()
      );
    }
  }
  public void requireNonNul(String field, String msg) {
    assert field != null;
    if (field.equals("")) {
      throw new WebApplicationException(
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(msg)
          .build()
      );
    }
  }
  public void requireNonNul(Integer field, String msg) {
    assert field != null;
    if (field == 0) {
      throw new WebApplicationException(
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(msg)
          .build()
      );
    }
  }
}
