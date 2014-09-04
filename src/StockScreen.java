import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import org.math.plot.*;

public class StockScreen extends JPanel {
    private Plot2DPanel plot; // from jmathplots, it extends JPanel
    protected JButton backButton; // protected so that it can be accessed in MainFrame's actionlistener
    protected JButton homeButton;
    private JLabel tickerLabel;
    private JLabel statsLabel;
    private JLabel newsLabel;

    protected JTextField quantity;
    protected JButton buyButton, sellButton;
    private JLabel buyLabel;
    private JLabel quantityLabel;
    protected JLabel errorMessage;

    private Market market;
    private Stock stock;

    public StockScreen(Market m) { // eventually should take a ticker argument and display it accordingly
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);


		market = m; // get market from the MainFrame
		stock = market.getStocks().get(0); // base stock, will be updated when clicked
	
		// Set up the ticker label
		tickerLabel = new JLabel("<html> <h1> <i>" + stock.getTicker() + "</i> </h1> </html>"); // should use ticker
		tickerLabel.setBackground(Color.black);
		tickerLabel.setForeground(Color.white);
		add(tickerLabel); // add to panel
		tickerLabel.setBounds(10, 15, 100, 20);
		tickerLabel.setOpaque(true);
	
		// Set up button to view stocks page
		backButton = new JButton("Back");
		add(backButton);
		backButton.setBounds(890, 10, 100, 30);

		homeButton = new JButton("Home");
		add(homeButton);
		homeButton.setBounds(780, 10, 100, 30);
	
		// Set up graph
		plot = new Plot2DPanel();
		double[] x = {32}; //getX(ticker) FROM HERE --> TIME
		double[] y = {0}; //getY(ticker)
		plot.addLinePlot(stock.getTicker() + " Price vs. Time", x, y); // create line plot from jmathplots
		plot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), stock.getTicker() + " Price vs. Time"));
		plot.setBounds(170,50, 820, 390);
		add(plot); // add to main jpanel
	
		//Set up stats label
		String statsText = "<html><h1 align='center'; style='padding:5';> STATS </h1>"; // add the text to the stats panel
		statsText += "<h3 style='padding:5'></h3></html>";
	
		statsLabel = new JLabel(statsText, SwingConstants.CENTER);
		statsLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		add(statsLabel);
		statsLabel.setBounds(10, 50,150, 390);
		statsLabel.setOpaque(true);
	
		// Set up news label
		String newsText = "<html><h1 align='center'; style='padding:5';> NEWS </h1>"; // add the text to the news panel
		newsText += "<h3 style='padding:5'>Coby is whack</h3></html>";
			
		newsLabel = new JLabel(newsText, SwingConstants.CENTER); // instantiate news panel and give it a border
		newsLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		add(newsLabel); // add and position newsLabel panel
		newsLabel.setBounds(10, 450, 540, 190);
		newsLabel.setOpaque(true);

		buyLabel = new JLabel("<html> <h2> <i>Buy/Sell Stock in " + stock.getTicker() +"</i> </h2> </html>");
		buyLabel.setBounds(700, 450, 220, 50);
		buyLabel.setBackground(Color.black);
		buyLabel.setForeground(Color.white);
		add(buyLabel);

		errorMessage = new JLabel("<html> <h3 align:'center'> <i>Invalid Entry:</i> </h3> </html>", SwingConstants.CENTER);
		errorMessage.setBounds(700, 485, 200, 30);
		errorMessage.setBackground(Color.black);
		errorMessage.setForeground(Color.red);
		add(errorMessage);
	
		quantityLabel = new JLabel("<html> <h3> <i>Enter Quantity:</i> </h3> </html>");
		quantityLabel.setBounds(685, 510, 120, 30);
		quantityLabel.setBackground(Color.black);
		quantityLabel.setForeground(Color.white);
		add(quantityLabel);
	
		quantity = new JTextField();
		quantity.setBounds(800, 510, 120, 30);
		add(quantity);
	
		buyButton = new JButton("Buy");
		buyButton.setBounds(730, 545, 100, 30);
		add(buyButton);
	
		sellButton = new JButton("Sell");
		sellButton.setBounds(730, 585, 100, 30);
		add(sellButton);
    }

    public void giveActionListener(ActionListener a) {
		backButton.addActionListener(a);
		homeButton.addActionListener(a);
		buyButton.addActionListener(a);
		sellButton.addActionListener(a);
    }

    public void setStock(String ticker, int counter) { // Used in mainframe when button is pressed from view stock screen
    	stock = market.getStockTicker(ticker); // get the right stock show

    	tickerLabel.setText("<html> <h1> <i>" + stock.getTicker() + "</i> </h1> </html>");
		
	updateStatsLabel();// method to update text of statsLabel

		// reset what is displayed around buy/ sell options
		buyLabel.setText("<html> <h2> <i>Buy/Sell Stock in " + stock.getTicker() +"</i> </h2> </html>");
		errorMessage.setText("");
		quantity.setText("");

		// reseting news displayed
		String newsText = "<html><h1 align='center'; style='padding:5';> NEWS </h1><h3 style='padding:5'>"; // add the text to the news panel
		LinkedList<News> oldNews = market.getOldNews();
		for (int i = 0; i < oldNews.size(); i++){
		    int num = i+1;
		    newsText += num + ") " + oldNews.get(i).getInfo() + "<br>";
		}
		newsText += "</h3></html>";
		newsLabel.setText(newsText);
		
		this.updateGraph(counter);
	
    }

    public void updateStatsLabel(){
	String color;
	if (stock.getPercentChange() > 0)
	    color = "green";
	else
	    color = "red";
    	String updateStatText = "<html><h1>STATS</h1><h3>" + "Beta: " +
	    stock.getBeta() + "</h3><h3> Volatility: " + stock.getVolatility() + 
	    "</h3> <h3 style='color:" + color + "'> Change: " + stock.getPercentChange() + "% </h3><h3> Price: " + stock.getPrice() + "$ </h3> <h3> High: " + stock.getHigh() + 
	    "$ </h3><h3>Low: " + stock.getLow() + "$ </h3> </html>";
	statsLabel.setText(updateStatText);
    }

    public void updateGraph(int xval){
    	// Set up graph
		plot = new Plot2DPanel();
		double[] x = {   xval-9, xval-8, xval-7, xval-6, xval-5, xval-4, xval-3, xval-2, xval-1, xval };
		double[] y = stock.getPastPrices(10); // y axis points is prices
		plot.addLinePlot(stock.getTicker() + " Price vs. Time", x, y); // create line plot from jmathplots
		//plot.setFixedBounds(1, stock.getPrice() - 1, stock.getPrice() + 1);
		plot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), stock.getTicker() + " Price vs. Time"));
		plot.setBounds(170,50, 820, 390);
		add(plot); // add to main jpanel
		
		revalidate();
	
    }

}
