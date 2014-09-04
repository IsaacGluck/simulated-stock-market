// Used to store positions in a portfolio
public class StockPosition{

    private String ticker; // the stock that is this position
    private int quantityShares; // how many shares are owned of this stock
    private Stock stock;

    public StockPosition(Stock st, int numShares){
	stock = st;	
	ticker = st.getTicker();
	quantityShares = numShares;
    }

    public void addShares(int numShares){
	quantityShares += numShares;
    }

    // pre-condition: There are enough shares owned to sell
    public void sellShares(int numShares){
	quantityShares -= numShares;
    }

    // accessor methods
    public String getTicker(){return ticker;}
    public int getNumShares(){return quantityShares;}
    public Stock getStock(){return stock;}

}
