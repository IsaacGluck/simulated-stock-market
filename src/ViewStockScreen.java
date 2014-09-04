import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ViewStockScreen extends JPanel {
    protected JButton homeButton; // protected so that it can be accessed in MainFrame's actionlistener
    protected final JButton AAPL, TSLA, GS, JPM, GOOG, BAC, TWTR, MSFT, RTN, FB;
    private JLabel viewStocksLabel;

    public ViewStockScreen() { // eventually should take a ticker argument and display it accordingly
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);

		// Set up the ticker label
		viewStocksLabel = new JLabel("<html> <h1> <i>View Stocks</i> </h1> </html>"); // should use ticker
		viewStocksLabel.setBackground(Color.black);
		viewStocksLabel.setForeground(Color.white);
		add(viewStocksLabel); // add to panel
		viewStocksLabel.setBounds(10, 15, 175, 20);
		viewStocksLabel.setOpaque(true);

		// Set up button to view stocks page
		homeButton = new JButton("Home");
		add(homeButton);
		homeButton.setBounds(890, 10, 100, 30);

		// Set up tickers
		AAPL = new JButton("<html><b>Apple Inc.</b></html>");
		add(AAPL);
		AAPL.setBounds(200, 125, 200, 50);

		TSLA = new JButton("<html><b>Tesla Motors</b></html>");
		add(TSLA);
		TSLA.setBounds(200, 225, 200, 50);
		
		GS = new JButton("<html><b>Goldman Sachs</b></html>");
		add(GS);
		GS.setBounds(200, 325, 200, 50);

		JPM = new JButton("<html><b>JP Morgan</b></html>");
		add(JPM);
		JPM.setBounds(200, 425, 200, 50);

		GOOG = new JButton("<html><b>Google Inc.</b></html>");
		add(GOOG);
		GOOG.setBounds(200, 525, 200, 50);

		MSFT = new JButton("<html><b>Microsoft Corp.</b></html>");
		add(MSFT);
		MSFT.setBounds(600, 125, 200, 50);

		BAC = new JButton("<html><b>Bank of America</b></html>");
		add(BAC);
		BAC.setBounds(600, 225, 200, 50);

		TWTR = new JButton("<html><b>Twitter Inc.</b></html>");
		add(TWTR);
		TWTR.setBounds(600, 325, 200, 50);

		FB = new JButton("<html><b>Facebook Inc.</b></html>");
		add(FB);
		FB.setBounds(600, 425, 200, 50);

		RTN = new JButton("<html><b>Raytheon</b></html>");
		add(RTN); 
		RTN.setBounds(600, 525, 200, 50);
    }

    // Give each button an action listener
    public void giveActionListener(ActionListener a) {
		homeButton.addActionListener(a);
		AAPL.addActionListener(a);
		TSLA.addActionListener(a);
		GS.addActionListener(a);
		JPM.addActionListener(a);
		BAC.addActionListener(a);
		GOOG.addActionListener(a);
		TWTR.addActionListener(a);
		RTN.addActionListener(a);
		MSFT.addActionListener(a);
		FB.addActionListener(a);
    }
    
}
