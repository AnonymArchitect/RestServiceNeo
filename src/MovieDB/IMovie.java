package MovieDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("MovieDB")
public interface IMovie {
	
 @GET
 @Path("GetMovieDesription")
 public String getMovieDesription(@QueryParam("MovieName") String MovieName);
 
 @GET
 @Path("GetListOfMovies")
 public String getListOfMovies();
 
}
