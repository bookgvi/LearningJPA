package localhost.services.ProvidersExceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalState implements ExceptionMapper<IllegalStateException> {
  @Override
  public Response toResponse(IllegalStateException e) {
    return Response
      .status(Response.Status.BAD_REQUEST)
      .type(MediaType.TEXT_HTML)
      .entity("Incorrect query")
      .build();
  }
}
