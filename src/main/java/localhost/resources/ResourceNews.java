package localhost.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import localhost.models.News;
import localhost.DAO.DAONews;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Path("/news")
public class ResourceNews {
  @EJB
  DAONews DAONews;

  @GET
  @Consumes("application/json")
  public Response getAll() {
    List<News> newsList = DAONews.findAll();
    if (newsList.size() > 0) return Response.ok().entity(newsList).build();
    return Response.status(400).build();
  }

  @POST
  @Consumes("application/json")
  public Response createOne(InputStream payload) {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(payload));
    JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
    String title = jsonObject.get("title").getAsString();
    int dateStamp = jsonObject.get("date").getAsInt();
    String lang = jsonObject.get("lang").getAsString();

    News news = DAONews.createOne(title, dateStamp, lang);
    return Response.status(Response.Status.CREATED).entity(news).build();
  }

}
