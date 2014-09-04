import java.util.*;

// Underlying class for storage of news items
public class News{

    private double newsEffect;
    private String information; // the actual info to be printed
    private String[] tags; // list of effected tickers

    public News(double effect, String info, String[] t){
	newsEffect = effect;
	information = info;
	tags = t;
    }

    public double getEffect(){return newsEffect;}
    public String getInfo(){return information;}
    public String[] getTags(){return tags;}

}
