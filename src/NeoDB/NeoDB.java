package NeoDB;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class NeoDB {

	private String getStrNeoHost() {
		
		Properties properties = new Properties();
		BufferedInputStream stream = null;
		try {
			stream = new BufferedInputStream(new FileInputStream("/home/RestService.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String neohost = properties.getProperty("neohost");
		
		return neohost;
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
