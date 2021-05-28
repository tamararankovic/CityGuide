package sbnz.app.fact;

public class Constants {

	//popularity
	public static final int POPULAR_MIN = 80;
	public static final int POPULAR_MIN_WHEN_ABOVE_AVERAGE = 70;
	public static final int UNPOPULAR_MAX = 20;
	public static final int UNPOPULAR_MAX_WHEN_ABOVE_AVERAGE = 30;
	
	//feature priority
	public static final int LOW_MIN = 30;
	public static final int REGULAR_MIN = 50;
	public static final int HIGH_MIN = 70;
	
	//similar user
	public static final double JACCARD_INDEX_MIN = 0.5;
	public static final double MIN_PERCENT_OF_SAME_TYPE_RATINGS = 50.0;
	
	//time spent at location
	public static final int MIN_RANDOM_TIME_IN_MINUTES = 10;
	public static final int MAX_RANDOM_TIME_IN_MINUTES = 120;
	
	//location type score
	public static final int MIN_LIKES = 3;
	public static final long MONTHS_IN_THE_PAST_TO_CHECK = 6;
	
	//location score
	public static final double DEFAULT_SCORE_WHEN_POPULARITY_INDETERMINABLE = 0.5; 
	public static final double DEFAULT_SCORE_WHEN_POPULAR = 1.0; 
	public static final double DEFAULT_SCORE_WHEN_UNPOPULAR = 0.0; 
	
	//location ranking
	public static final double AVAILABLE_MINUTES_PER_DAY = 360;
}
