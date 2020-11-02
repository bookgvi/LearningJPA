package localhost.services.Validators;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@RequestScoped
public class ValidatorsBean {
  public void requireNonNul(Object field, String fieldName) {
    assert field != null;
    if (field.equals("")) {
      throw new WebApplicationException(
        Response
          .status(Response.Status.BAD_REQUEST)
          .entity(fieldName + " не может быть пустым")
          .build()
      );
    }
  }
}
