package localhost.services.ProvidersExceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NullPointer implements ExceptionMapper<NullPointerException> {
  @Override
  public Response toResponse(NullPointerException e) {
    String msg = e.getMessage() != null ? e.getMessage() : "Entity not found";
    return Response
      .status(Response.Status.BAD_REQUEST)
      .type(MediaType.TEXT_HTML)
      .entity(msg)
      .build();

  }
}
