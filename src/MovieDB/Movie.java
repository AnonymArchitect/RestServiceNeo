package MovieDB;


import java.io.UnsupportedEncodingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


public class Movie implements IMovie {
	 
	private final String strNeoHost = "localhost";
	 /**
	 * @return the strNeoHost
	 */
	private String getStrNeoHost() {
		return strNeoHost;
	}

	@Override
	 public String getMovieDesription(String MovieName) 
	 {
		 
		 ResteasyClient client = new ResteasyClientBuilder().build();
		 ResteasyWebTarget target = client.target("http://"+getStrNeoHost()+":7474/db/data/cypher");
		 String token = "neo4j" + ":" + "neo4j";
		 String base64Token = "";
		try {
			base64Token = DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     Response response = target.request().header("Authorization", "Basic " + base64Token)
	    		 .header("Accept", "application/json; charset=UTF-8") 
	    		 .header("Content-Type", "application/json")
	    		 .post(Entity.entity(" { \"query\" : \"MATCH (movie:Movie { title: '" + MovieName + "' }) RETURN movie.id, movie.title, movie.genre, movie.studio\", \"params\" : {  } }", "application/json"));
	
	     String value = response.readEntity(String.class);
	     response.close();
	     
		 return value;
	 }
	 
	 @Override
	 public String getListOfMovies() 
	 {
		 ResteasyClient client = new ResteasyClientBuilder().build();
		 ResteasyWebTarget target = client.target("http://"+getStrNeoHost()+":7474/db/data/cypher");
		 String token = "neo4j" + ":" + "neo4j";
		 String base64Token = "";
		try {
			base64Token = DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     Response response = target.request().header("Authorization", "Basic " + base64Token)
	    		 .header("Accept", "application/json; charset=UTF-8") 
	    		 .header("Content-Type", "application/json")
	    		 .post(Entity.entity(" { \"query\" : \"MATCH (movie:Movie ) RETURN movie.id, movie.title\", \"params\" : {  } }", "application/json"));
	
	     String value = response.readEntity(String.class);
	     response.close();
	
	     return value;
	 }
 
}