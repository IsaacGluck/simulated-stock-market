import java.util.*;
import java.lang.*;

// Each individual stock's statistics and price changes controlled in Stock class
public class Stock{

    private String ticker; // name of stock
    private double price; // price
    private double high; // high since app's run
    private double low; // low since app's run
    private double beta; // degree to which stock mirrors market. b > 1 means exaggerated response to market movement. b < -1 means exaggerated response in opposite direction of market
    private double volatility; // degree to which stock fluctuates
    private double marketStrength; // measure of market as a whole between -1 and 1
    private double stockStrength; // health of company
    private RunMed bids; // a running median counter of bids
    private RunMed asks; // a running median counter of asks
    private LinkedList<Double> pastPrices; // for use in calculating percent change
    private double percentChange; // queue for use of fifo properties

    public Stock(String t, double b, double v, double ms){
	YahooApi yahooApi = new YahooApi();
	ticker = t;
	price = yahooApi.getData(t); // returns last close price from live api
	high = 0.0;
	low = 0.0;
	beta = b;
	volatility = v;
	marketStrength = ms;
	stockStrength = 0.0;

	bids = new RunMed();
	asks = new RunMed();
	pastPrices = new LinkedList<Double>(); // init data lists

	initialPurhcases(); // function to create some initial bids and asks
	
	low = price;
	high = price; // neither should have vals user didn't see
    }

    // populates bid and ask lists with values and sets price
    public void initialPurhcases(){
	for (int i = 0; i < 100; i++){
	    double newBid = newPurchase(); // calls function that creates realistic new purchase
	    bids.insert(newBid); // add new bid to the running list of bids
	    double newAsk = newPurchase();
	    asks.insert(newAsk);
	}
	priceUpdate(); // new price set at the average of the bid average and ask average
    }

    public void priceUpdate(){
	double bidMedian = bids.getMedian();
	double askMedian = asks.getMedian();
	price = (bidMedian + askMedian) / 2; // new price is average of their medians
	price = (double)(Math.round(price * 100)) / 100; // rounds to 10th
	double newBid = newPurchase(); // calls helper method to create new purchase
	bids.insert(newBid);
	double newAsk = newPurchase();
	asks.insert(newAsk);
	updatePercent(); // method that updates percent change
	if (price > high)
	    high = price;
	if (price < low)
	    low = price;
    }

    /*Adds new purchase
      PRECONDITIONS: 
      -1 <= marketStrength <= 1 -1 weakest, 1 strongest
      0 < volatility < 1. 1 means huge fluctuations
      ALGORITHM: random double (less than one) * volatility (degree of fluctuation in price) * marketStrength (-1 means it is really weak and price should decrease, 1 means it is really strong) * beta ( beta > 1 will exaggerate market's strength, 1 > beta > 0 will dampen market effect, beta < 0 will move in oppposite of market) = price change.
      The closer market strength is to zero, the more likely it is that the stock will move in the opposite direction that market strength and beta would otherwise indicate.
     */
    public double newPurchase(){
	Random r = new Random();
	if (marketStrength == 0)
	    marketStrength += .01;
	double marketEffect = marketStrength * beta + stockStrength; // strength less than one causes decrease in price, beta exaggerates/ mitigate's market's effect
	double d = r.nextDouble() * volatility * marketEffect; // higher volatility makes new purchase deviate further from current price
	int marketSentiment = Math.abs((int)(marketEffect * 10)) + 1; // +1 meant to avoid errors on nextInt when strength is zero
	marketSentiment = Math.abs(marketSentiment); // for use in next int
	if (r.nextInt(marketSentiment) == 1)
	    d = 0-d; // adds degree of fluctuation. A market sentiment closer to zero is more likely to yield a reverse in direction of price
	return (price + price*d);
    }

    public void updatePercent(){
	pastPrices.add(price);
	if (pastPrices.size() > 300)
	    pastPrices.remove(); // remove oldest price after 300 passed
	double oldP = pastPrices.peek(); // oldest value in queue
	percentChange = ((price - oldP)/price) * 100;
	percentChange = (double)(Math.round(percentChange * 100)) / 100; // rounds to 10th
	if (percentChange == 0.0)
	    percentChange = 0.01; // little cheat to get around some errors
    }

    // accessor methods
    public String getTicker(){return ticker;}
    public double getPrice(){return price;}
    public double getBeta(){return beta;}
    public double getVolatility(){return volatility;}
    public double getPercentChange(){return percentChange;}
    public double getHigh(){return high;}
    public double getLow(){return low;}
    
    // outside classes can set a new market strength
    public void setMarketStrength(double ms){
	marketStrength += ms;
    }

    // creates a reaction in the market to news
    public void applyNews(News news){
	double effect = news.getEffect();
	if (effect < 0)
	    stockStrength += effect;
	else
	    stockStrength += effect;
	if (effect < -.4)
	    price -= .2;
	if (effect > .4)
	    price += .2;
    }

    // returns list of the last num prices
    public double[] getPastPrices(int num){
	double[] ret = new double[num]; // to be returned
	int listSize = pastPrices.size(); // to avoid multiple calls
	for (int i = num; i > 0; i--)
	    ret[num - i] = pastPrices.get(listSize - i);
	/*for (int i = 0; i < ret.length; i++)
	  System.out.println(ret[i]);*/ // FOR TESTING
	return ret;
    }

    public String toString(){
	return ticker;
    }

    // FOR TESTING
    public static void main(String[] args){
	Stock st = new Stock("AAPL", .2, .7, .3); 
	/*// ticker name, start price, beta, volatility,  market strength
	for (int i = 0; i < 100; i++){
	    st.priceUpdate();
	    System.out.println("Price: " + st.getPrice() + "$");
	    System.out.println("Percent change: " + st.getPercentChange() + "%");
	}
	System.out.println("Last eight prices: " + st.getPastPrices(8));
	for (int i = 0; i < 100; i++){
	    st.priceUpdate();
	    System.out.println("Price: " + st.getPrice() + "$");
	    System.out.println("Percent change: " + st.getPercentChange() + "%");
	}
	System.out.println("Last eight prices: " + st.getPastPrices(8));*/
	}



}
