import java.util.*;
// Market class stores all stocks and calls upon them to update prices at regular intervals. Also controls the creation of news
public class Market{

    private double gsIndexPrice; // total value of goldberg and saacs index = sum of all prices
    private LinkedList<Stock> stocks; // will contain all stocks on market
    private double marketStrength; // market strength of whole market
    private QuickSortStocks qs; // quick sort to be used when needed
    private Stack<News> news;
    private LinkedList<News> appliedNews; // old news, never to be longer than 4
    private String ticker;
    
    public Market(){
	fillMarket(); // method to add stocks to market
	gsIndexPrice = 0.0;
	updateIndexVal(); // method to update the value of the market
	qs = new QuickSortStocks();
	news = new Stack<News>();
	createNews(); // creates news items
	appliedNews = new LinkedList<News>();
    }

    // INITIALIZE HELPERS

    public void fillMarket(){
	Random r = new Random();
	double ms;
	if (r.nextInt(2) == 0)
	    ms = -.5;
	else
	    ms = .5;
	stocks = new LinkedList<Stock>();
	// The ten stocks to choose from
	Stock stock1 = new Stock("AAPL", .74, .4, ms); // ticker, beta, vol, market strength
	stocks.add(stock1);
	Stock stock2 = new Stock("TSLA", .23, 1.3, ms);
	stocks.add(stock2);
	Stock stock3 = new Stock("GS", -.13, .9, ms);
	stocks.add(stock3);
	Stock stock4 = new Stock("JPM", .07, .4, ms);
	stocks.add(stock4);
	Stock stock5 = new Stock("GOOG", .22, .89, ms);
	stocks.add(stock5);
	Stock stock6 = new Stock("MSFT", 1.1, .8, ms);
	stocks.add(stock6);
	Stock stock7 = new Stock("BAC", .19, .82, ms);
	stocks.add(stock7);
	Stock stock8 = new Stock("TWTR", -.84, .9, ms);
	stocks.add(stock8);
	Stock stock9 = new Stock("FB", .28, .6, ms);
	stocks.add(stock9);
	Stock stock10 = new Stock("RTN", .32, .21, ms);
	stocks.add(stock10);

    }

// initializes all news
    public void createNews(){
	LinkedList<News> newsSelect = new LinkedList<News>();
	String info1 = "Apple invents new iPhone that does your comp sci project";
	News n1 = new News(.1, info1, new String[]{"AAPL"});
	newsSelect.push(n1);
	String info2 = "Federal reserves announces it will contract money supply. Investors be warned";
	News n2 = new News(-.3, info2, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n2);
	String info3 = "Government will begin prosecuting several major financial CEOs for fraud";
	News n3 = new News(-.4, info3, new String[]{"GS", "JPM", "BAC"});
	newsSelect.push(n3);
	String info4 = "Tesla CEO Elon Musk announces car that runs on love";
	News n4 = new News(.8, info4, new String[]{"TSLA"});
	newsSelect.push(n4);
	String info5 = "Goldman Sacs opens a goat trading divsion, reaps record profits";
	News n5 = new News(.6, info5, new String[]{"GS"});
	newsSelect.push(n5);
	String info6 = "Apple sued for having actually stolen every single one of its ideas";
	News n6 = new News(-.5, info6, new String[]{"AAPL"});
	newsSelect.push(n6);
	String info7 = "JP Morgan announces major losses due to bad investments in the Mets, upwards of 20 billion lost.";
	News n7 = new News(-.8, info7, new String[]{"JPM"});
	newsSelect.push(n7);
	String info8 = "University of Michigan announces 87.3 Consumer Confidence Index, up 4 points from previous month.";
	News n8 = new News(.4, info8, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n8);
	String info9 = "Apple announces new product, iLive, lives life for you. Initial sale in the trillions of pesos.";
	News n9 = new News(.5, info9, new String[]{"AAPL"});
	newsSelect.push(n9);
	String info10 = "Tesla to open new factory in Central African Republic. Investors very excited about prospects";
	News n10 = new News(.7, info10, new String[]{"TSLA"});
	newsSelect.push(n10);
	String info12 = "Government shutodwn potent in thirty days";
	News n12 = new News(-.3, info12, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n12);
	String info13 = "France announced 300 billion dollar suit against Google for evaded taxes.";
	News n13 = new News(-.9, info13, new String[]{"GOOG"});
	newsSelect.push(n13);
	String info14 = "Facebook announces major user growth in Asian markets.";
	News n14 = new News(.4, info14, new String[]{"FB"});
	newsSelect.push(n14);
	String info15 = "Twitter losing membership fast in North America as tweeter gains popularity";
	News n15 = new News(-.7, info15, new String[]{"TWTR"});
	newsSelect.push(n15);
	String info16 = "Republicans in House will not back down on shutdown, says leadership";
	News n16 = new News(-.3, info16, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n16);
	String info17 = "North Korean markets now open to all social media, Kim Jung Ill announces";
	News n17 = new News(.5, info17, new String[]{"FB", "TWTR"});
	newsSelect.push(n17);
	String info18 = "New 'Cat of Wall Street' makes record gains for JP Morgan";
	News n18 = new News(.4, info18, new String[]{"JPM"});
	newsSelect.push(n18);
	String info19 = "Obama announces budget deal, funds government for four months";
	News n19 = new News(.5, info19, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n19);
	String info20 = "Raytheon announces deal as 'Official weapons provider of the USA'";
	News n20 = new News(.3, info20, new String[]{"RTN"});
	newsSelect.push(n20);
	String info21 = "Tesla's 'Love Car' sales through the roof, draws praise from environmental activists";
	News n21 = new News(.5, info21, new String[]{"TSLA"});
	newsSelect.push(n21);
	String info22 = "Job growth falls 100,000 short of expectations for past month";
	News n22 = new News(-.2, info22, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n22);
	String info23 = "Cat of Wall Street under charges for fraud, as is much of JPM";
	News n23 = new News(-.7, info23, new String[]{"JPM"});
	newsSelect.push(n23);
	String info24 = "Ron Paul announces potential candidacy for President again, polling looks good, plans to end the government";
	News n24 = new News(-.9, info24, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n24);
	String info25 = "Google settles suit with France for 50 billion pesos, many believe they dodged a bullet";
	News n25 = new News(.6, info25, new String[]{"GOOG"});
	newsSelect.push(n25);
	String info26 = "Apple to start paying its Chinese workers, investors happy";
	News n26 = new News(.3, info26, new String[]{"AAPL"});
	newsSelect.push(n26);
	String info27 = "China to allow Google services free of censoring, Google to expand Asian operations";
	News n27 = new News(.6, info27, new String[]{"GOOG"});
	newsSelect.push(n27);
	String info28 = "Sales of Apple's iPhone 8 through the roof";
	News n28 = new News(.5, info28, new String[]{"AAPL"});
	newsSelect.push(n28);
	String info29 = "Twitter loses a lot of customers";
	News n29 = new News(-.8, info29, new String[]{"TWTR"});
	newsSelect.push(n29);
	String info30 = "Raytheon hires new CEO, great track record for being great";
	News n30 = new News(.3, info30, new String[]{"RTN"});
	newsSelect.push(n30);
	String info31 = "Jesus returns. Investors rejoice";
	News n31 = new News(.9, info31, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n31);
	String info32 = "Coby dies. Investors mourn.";
	News n32 = new News(-.9, info32, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n32);
	String info33 = "Tesla builds bullet train in U.S. Reaps billions in profits";
	News n33 = new News(.4, info33, new String[]{"TSLA"});
	newsSelect.push(n33);
	String info34 = "Turns out Greece was lying again. They're once again in a ton of debt";
	News n34 = new News(-.8, info34, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n34);
	String info35 = "U.S. wins World Cup. USA USA USA USA";
	News n35 = new News(.8, info35, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n35);
	String info36 = "Goldman Sacs is actually just run by voldamort. Investors angry.";
	News n36 = new News(-.7, info36, new String[]{"GS"});
	newsSelect.push(n36);
	String info37 = "Isaac leads Stuy Soccer to Gold. Gooooooooooooo pegelegs!";
	News n37 = new News(.6, info37, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	newsSelect.push(n37);


	// put news items randomly into actual news stack
	Random rand = new Random();
        while (newsSelect.size() > 0){
	    int select = rand.nextInt(newsSelect.size());
	    news.push(newsSelect.remove(select));
	}
    }

    // MARKET RUNNERS

    // method that updates the market  every 5 seconds
    public void updateMarket(boolean init){
	    // randomly assign new news
	    //for (int j = 0; j < stocks.size(); j++) // FOR TESTING
	// System.out.println(stocks.get(j).getTicker());
	    Random r = new Random();
	    if (r.nextInt(5) == 1 && !news.empty() && init)
	      applyNews();
	    priceUpdate();
	    updateIndexVal();
	    updateStrength();
	
    }

    // tells each effected stock of new news
    public void applyNews(){
	News item = news.pop();
	appliedNews.add(item);
	String[] tags = item.getTags();

	for (int i = 0; i < tags.length; i++){
	    String searching = tags[i];

	    for (int j = 0; j < stocks.size(); j++){
		Stock checking = stocks.get(j);
		String tick = checking.getTicker();
		if (searching.equals(tick)){
		    checking.applyNews(item); // stock's applyNews method
		    break;
		}
	    }
	}
	}

    // updates price of each stock
    public void priceUpdate(){
	for (int i = 0; i < stocks.size(); i++)
	    stocks.get(i).priceUpdate(); // new price for each stock
    }

    // updates the overall value of market
    public void updateIndexVal(){
	Stock st;
	// recurse through the market
	gsIndexPrice = 0.0;
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    gsIndexPrice += st.getPrice(); // add each stock's price to index total
	}
	gsIndexPrice = (double)(Math.round(gsIndexPrice * 100)) / 100;
    }


    // updates market strength and gives the new one to each market
    public void updateStrength(){
	// method to change market strength value
	double changeInStrength = 0.0; // can be neg or pos
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    st.setMarketStrength(changeInStrength); // reset each stock's strength variable
	}
	} // perhaps to be implemented


    //  METHODS TO RETURN STOCKS SORTED

    public LinkedList<Stock> getStocks() {
    	return stocks;
    }

    public LinkedList<Stock> getAlphabetizedStocks(){
	LinkedList<Stock> sorted = qs.qsort(stocks, 0); // sorts by ticker name
	return sorted;
    }

    // returns a list of the most expensive stocks
    public LinkedList<Stock> getExpensiveStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 1); // sort by price
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = sorted.size() - 1; i >= sorted.size() - quantity; i--)
	    ret.add(sorted.get(i));
	return ret;
    }

    public LinkedList<Stock> getCheapestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 1);
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = 0; i < quantity; i++)
	    ret.add(sorted.get(i));
	return ret;
    }

    // return list of stocks with highest percent changes
    public LinkedList<Stock> getStrongestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = sorted.size() - 1; i >= sorted.size() - quantity; i--){
	    ret.add(sorted.get(i));
	}
	return ret;
    }


    // return list of stocks with weakest percent changes
    public LinkedList<Stock> getWeakestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = 0; i < quantity; i++){
	    ret.add(sorted.get(i));
	}
	return ret;
    }


    public Stock getStockTicker(String tick){
	for (int i = 0; i < stocks.size(); i++){
	    if (stocks.get(i).getTicker().equals(tick))
		return stocks.get(i);
	}
	return null;
    }

    public LinkedList<News> getOldNews(){
	while (appliedNews.size() > 4)
	    appliedNews.remove();
	return appliedNews;
    }

    public double getIndexVal(){
	return gsIndexPrice;
    }
    
    // FOR TESTING
    public static void main(String[] args){
	/*Market m = new Market();
	LinkedList<Stock> tp = m.getAlphabetizedStocks();
	for (Stock a : tp)
	    System.out.println(a + "Price: " + a.getPrice());
	//System.out.println(m.getIndexVal());
	for (int i = 0; i < 100; i++){
	    m.updateMarket(false);
	    
	}
	//System.out.println(m.getIndexVal());
	tp = m.getStocks();
	for (Stock a : tp)
	    System.out.println(a + " Percent change: " + a.getPercentChange());
	tp =  m.getWeakestStocks(4);
	System.out.println("Cheapest: ");
	for (Stock a : tp)
	    System.out.println(a + " Percent change: " + a.getPercentChange());
	tp = m.getStocks();
	for (Stock a : tp)
	System.out.println(a + "Are they still here: " + a.getPercentChange());
	tp = m.getStocks();
	for (Stock a : tp)
	    System.out.println( "Stocks original order: " + a);
	tp =  m.getWeakestStocks(4);
	System.out.println("Weakest: ");
	for (Stock a : tp)
	    System.out.println(a + " Percent change: " + a.getPercentChange());
	tp =  m.getExpensiveStocks(4);
	System.out.println("Most expensive: ");
	for (Stock a : tp)
	    System.out.println(a + " Price: " + a.getPrice());
	tp =  m.getCheapestStocks(4);
	System.out.println("Cheapest: ");
	for (Stock a : tp)
	    System.out.println(a + " Price: " + a.getPrice());
	tp =  m.getAlphabetizedStocks();
	System.out.println("By name: ");
	for (Stock a : tp)
	System.out.println(a );*/
	
    }



}
