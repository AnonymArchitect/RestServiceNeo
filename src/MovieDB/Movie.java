package MovieDB;


/*import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

*/

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


public class Movie implements IMovie {
	
/*	public class Authenticator implements ClientRequestFilter {

	    private final String user;
	    private final String password;

	    public Authenticator(String user, String password) {
	        this.user = user;
	        this.password = password;
	    }

	    public void filter(ClientRequestContext requestContext) throws IOException {
	        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        final String basicAuthentication = getBasicAuthentication();
	        headers.add("Authorization", basicAuthentication);

	    }

	    private String getBasicAuthentication() {
	        String token = this.user + ":" + this.password;
	        try {
	            return "Basic " +
	                 DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
	        } catch (UnsupportedEncodingException ex) {
	            throw new IllegalStateException("Cannot encode with UTF-8", ex);
	        }
	    }
	}*/

	
 @Override
 public String getMovieDesription(String MovieName) 
 {
	 
	 ResteasyClient client = new ResteasyClientBuilder().build();
	 ResteasyWebTarget target = client.target("http://localhost:7474/db/data/cypher");
	 
     Response response = target.request().post(Entity.entity(" { \"query\" : \"MATCH (movie:Movie { title: MovieName }) RETURN movie.id, movie.title, movie.genre, movie.studio\", \"params\" : {  } }", "application/json"));

     String value = response.readEntity(String.class);
     response.close();
     
	 return value;
 }
 
 @Override
 public String getListOfMovies() 
 {
	/*
	 	 
	 POST http://localhost:7474/db/data/cypher
		 Accept: application/json; charset=UTF-8
		 Content-Type: application/json
		 {
		   "query" : "MATCH (movie:Movie ) RETURN movie.id, movie.title",
		   "params" : {
		   }
		 }
	*/

	 ResteasyClient client = new ResteasyClientBuilder().build();
	 ResteasyWebTarget target = client.target("http://localhost:7474/db/data/cypher");
	 
     Response response = target.request().post(Entity.entity(" { \"query\" : \"MATCH (movie:Movie ) RETURN movie.id, movie.title\", \"params\" : {  } }", "application/json"));

     String value = response.readEntity(String.class);
     response.close();

     return value;
	 
 }
 
}