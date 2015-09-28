package NeoDB;

import javax.jws.WebService;

@WebService(endpointInterface = "NeoDB.INeoDBWebservice", serviceName = "NeoDBWebservice")

public class NeoDBWebservice implements INeoDBWebservice {

	private static NeoDB neodb = new NeoDB();
	
	public String getMovieDesription(String name) {
		return neodb.getMovieDesriptionNeoDB(name);
	}

	public String getListOfMovies() 
	{
		return neodb.getListOfMoviesNeoDB();
	}
}
