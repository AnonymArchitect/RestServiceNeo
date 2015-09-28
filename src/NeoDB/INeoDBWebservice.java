package NeoDB;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public interface INeoDBWebservice {

	@WebMethod()
	public String getMovieDesription(String name);

	@WebMethod()
	public String getListOfMovies();
	
}
