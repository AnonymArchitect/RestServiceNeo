package NeoDB;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class NeoDB {

	private final String strNeoHost = "localhost";
	 /**
	 * @return the strNeoHost
	 */
	private String getStrNeoHost() {
		return strNeoHost;
	}

	 public String getMovieDesriptionNeoDB(String MovieName) 
	 {
	     return callNeo(" { \"query\" : \"MATCH (movie:Movie { title: '" + MovieName + "' }) RETURN movie.id, movie.title, movie.genre, movie.studio\", \"params\" : {  } }");
	 }
	
	 public String getListOfMoviesNeoDB() 
	 {
	     return callNeo(" { \"query\" : \"MATCH (movie:Movie ) RETURN movie.id, movie.title\", \"params\" : {  } }");
	 }
	 
	 private String  callNeo(String query)
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
					 .post(Entity.entity(query, "application/json"));
			
			String value = response.readEntity(String.class);
			response.close();
			
			return value;
	 }
}
