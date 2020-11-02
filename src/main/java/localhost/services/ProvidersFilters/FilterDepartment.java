package localhost.services.ProvidersFilters;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class FilterDepartment implements ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext responseContext) throws IOException {
    System.out.printf("QQQ: %s%n", containerRequestContext);
    Object entity = responseContext.getEntity();
    if (entity instanceof Throwable) {
      responseContext.setEntity(null);
      responseContext.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
    }}
}
