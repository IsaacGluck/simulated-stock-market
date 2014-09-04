import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.BorderFactory;

public class HomeScreen extends JPanel{
    protected JButton viewStocksButton; // protected so that it can be accessed in MainFrame's actionlistener
    protected JButton sellAll;
    private JLabel homeLabel;
    private JLabel news;
    private JLabel expensiveStocks;
    private JLabel cheapStocks;
    private JLabel myPort;
    private JLabel gsIndex;
    private Market market; // Market and User taken from MainFrame
    private User user;



    public HomeScreen(Market m, User u) {
	setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
	setLayout(null); // it will be a card in the main deck
	setBackground(Color.black);

	// From MainFrame
	market = m;
	user = u;

	// Set up the home label
	homeLabel = new JLabel("<html> <h1> <i>Home</i> </h1> </html>");
	homeLabel.setBackground(Color.black);
	homeLabel.setForeground(Color.white);
	add(homeLabel); // add to panel
	homeLabel.setBounds(10, 15, 100, 20);
	homeLabel.setOpaque(true);

	// Set up Expensive Stocks Label
	String expensiveText = "<html> <h1 align='center'>Expensive Stocks</h1><h3 align='center'> ";
	LinkedList<Stock> hotties = market.getExpensiveStocks(5);
	Stock st;
	for (int i = 0; i < 5; i++){
	    st = hotties.get(i);
	    // System.out.println(st.getTicker()); FOR TESTING
	    expensiveText += st.getTicker() + " : " + st.getPrice() + "$ <br>";
	    }
	expensiveText += "</h3></html>";
	expensiveStocks = new JLabel(expensiveText, SwingConstants.CENTER);

	expensiveStocks.setBorder(BorderFactory.createLoweredBevelBorder());
	add(expensiveStocks);
	expensiveStocks.setBounds(10, 50, 320, 290);
	expensiveStocks.setOpaque(true);


	// Set up Cheap Stocks Label
	String cheapText = "<html> <h1 align='center'>Cheap Stocks</h1> <h3 align='center'>";
	LinkedList<Stock> weakies = market.getCheapestStocks(5);
	for (int i = 0; i < 5; i++){
	    st = weakies.get(i);
	    cheapText += st.getTicker() + " : " + st.getPrice() + "$ <br>";
	    }
	cheapText += "</h3></html>";
	cheapStocks = new JLabel(cheapText, SwingConstants.CENTER);

	cheapStocks.setBorder(BorderFactory.createLoweredBevelBorder());
	add(cheapStocks);
	cheapStocks.setBounds(340, 50, 320, 290);
	cheapStocks.setOpaque(true);


	// Set up button to view stocks page
	viewStocksButton = new JButton("View Stocks");
	add(viewStocksButton);
	viewStocksButton.setBounds(890, 10, 100, 30);

	// Set up news label
	String newsText = "<html><h1 align='center'> NEWS </h1></html>"; // add the text to the news panel

	news = new JLabel(newsText, SwingConstants.CENTER); // instantiate news panel and give it a border
	news.setBorder(BorderFactory.createLoweredBevelBorder());
	add(news); // add and position news panel
	news.setBounds(10, 350, 650, 290);
	news.setOpaque(true);

	// Set up Portfolio label
	String portText = "<html> <h1 align='center'>Portfolio</h1> </html><h3 align='center'>";
	LinkedList<StockPosition> portfolio = user.getPortfolio();
	StockPosition s;
	for (int i = 0; i < portfolio.size(); i++){
	    s = portfolio.get(i);
	    portText += s.getTicker() + " : " + s.getNumShares() + "<br>";
	    }
	portText += "$" + user.getMoney() +  "</h3></html>";
	myPort = new JLabel(portText, SwingConstants.CENTER);
	myPort.setBorder(BorderFactory.createLoweredBevelBorder());
	add(myPort);
	myPort.setBounds(670, 50, 320, 410);
	myPort.setOpaque(true);

	// Set up button to sell all held stocks
	sellAll = new JButton("Sell All Stocks");
	add(sellAll);
	sellAll.setBounds(665, 470, 330, 30);

	// Set up Goldberg Saacs Index label
	String gsText = "<html> <h1 align='center'>Goldberg Saacs Index</h1> <h3 align='center'>$" + 
	market.getIndexVal() + "</h3></html>";
	gsIndex = new JLabel(gsText, SwingConstants.CENTER);
	gsIndex.setBorder(BorderFactory.createLoweredBevelBorder());
	add(gsIndex);
	gsIndex.setBounds(670, 510, 320, 130);
	gsIndex.setOpaque(true);

    }

    public void updateText(){
	// Set up hot stocks label
    	String color;
		String expensiveText = "<html> <h1 align='center'>Expensive Stocks</h1>";
		LinkedList<Stock> hotties = market.getExpensiveStocks(5);
		Stock st;
		for (int i = 0; i < 5; i++){
		    st = hotties.get(i);
		    if (st.getPercentChange() > 0)
			color = "green";
		    else
			color = "red";
		    expensiveText += "<h3 align='center' style='color:" + color + "'>" + st.getTicker() + " : " + st.getPrice() + "$ " + st.getPercentChange() +"% </h3>";
		}
		expensiveText += "</html>";
		expensiveStocks.setText(expensiveText);
	
	
		// Cold stocks
		String cheapText = "<html> <h1 align='center'>Cheap Stocks</h1>";
		LinkedList<Stock> weakies = market.getCheapestStocks(5);
		for (int i = 0; i < 5; i++){
		    st = weakies.get(i);
		    if (st.getPercentChange() > 0)
			color = "green";
		    else
			color = "red";
		    cheapText += "<h3 align='center' style='color:" + color + "'>" + st.getTicker() + " : " + st.getPrice() + "$ " + st.getPercentChange() + "%  </h3>";
		    }
		cheapText += "</h3></html>";
		cheapStocks.setText(cheapText);
	
	
		// News
		String newsText = "<html><h1 align='center';> NEWS </h1>"; // add the text to the news panel
		LinkedList<News> oldNews = market.getOldNews();
		for (int i = 0; i < oldNews.size(); i++){
		    int num = i+1;
		    newsText += "<h3 align='center'>" + num + ") " + oldNews.get(i).getInfo() + "</h3";
		}
		newsText += "</html>";
		news.setText(newsText);
	
		// Portfolio
		String portText = "<html> <h1 align='center'>Portfolio</h1>";
		LinkedList<StockPosition> portfolio = user.getPortfolio();
		StockPosition s;
		for (int i = 0; i < portfolio.size(); i++){
		    s = portfolio.get(i);
		    String name = s.getTicker();
		    st = market.getStockTicker(name); // stock itself
		    int num = s.getNumShares();
		    double value = num * st.getPrice();
		    value = (double)(Math.round(value * 100)) / 100; 
		    portText += "<h3 align='center'>" + name + ": " + num + " shares worth $" + value + "</h3>"; } 

		if (user.getReturns() >= 0)
		    color = "green";
		else
		    color = "red";

		portText += "<br><br><h4 align='center' style='color:" + color + "'>$" + user.getMoney() + " available</h3>"; 
		portText += "<h4 align='center'> You had $" + user.getOriginalMoney() + " originally </html>";
		
		myPort.setText(portText);

		// Goldberg and Sacs Index update
		String gsText = "<html> <h1 align='center'>Goldberg Saacs Index</h1> <h3 align='center'>$" + 
		    market.getIndexVal() + "</h3><h5 align='center'> Aggregate value of the Goldberg Saac's market </h5></html>";
		gsIndex.setText(gsText);
    }

    public void giveActionListener(ActionListener a) {
	viewStocksButton.addActionListener(a);
	sellAll.addActionListener(a);
    }
}
