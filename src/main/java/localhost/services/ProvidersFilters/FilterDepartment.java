package localhost.services.ProvidersFilters;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class FilterDepartment implements ContainerResponseFilter, ContainerRequestFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    System.out.printf("FILTER Request: %s%n", containerRequestContext);

  }

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext responseContext) throws IOException {
    Object entity = responseContext.getEntity();
    if (entity instanceof Throwable) {
      responseContext.setEntity(null);
      responseContext.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
    }}
}
