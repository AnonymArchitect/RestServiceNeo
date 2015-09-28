package NeoDB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("NeoDBRestservice")
public interface INeoDBRestservice {
	
 @GET
 @Path("GetMovieDesription")
 public String getMovieDesription(@QueryParam("MovieName") String MovieName);
 
 @GET
 @Path("GetListOfMovies")
 public String getListOfMovies();
 
}
