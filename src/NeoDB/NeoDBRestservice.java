package NeoDB;


public class NeoDBRestservice implements INeoDBRestservice {
	 
	private static NeoDB neodb = new NeoDB();
	
	@Override
	 public String getMovieDesription(String MovieName) 
	 {
		return neodb.getMovieDesriptionNeoDB(MovieName);
	 }
	 
	 @Override
	 public String getListOfMovies() 
	 {
		 return neodb.getListOfMoviesNeoDB();
	 }
 
}