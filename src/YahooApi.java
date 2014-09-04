import java.util.*;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.net.ConnectException;

public class YahooApi {

    private static String baseURL = "http://download.finance.yahoo.com/d/quotes.csv?s="; // base url for the api
    // http://www.gummy-stuff.org/Yahoo-data.htm list of acronyms for yahoo api
    private static String baseURLend = "&f=p=.csv"; // b2 b3 are ask and bid real time

    public double getData(String ticker) {
	try {
	    URL interWebs = new URL(baseURL + ticker + baseURLend); // create a URL instance of the API

	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("149.89.1.30", 3128)); // the proxy in the Stuy computer lab

	    URLConnection interwebConnect = interWebs.openConnection(proxy); // make a URL connection out of the URL 	
	    // make way to choose lab settings

	    InputStreamReader isr = new InputStreamReader(interwebConnect.getInputStream()); // Retrieve the csv into a InputStreamReader
			
	    BufferedReader in = new BufferedReader(isr); // For efficiency, thank you oracle API

	    String data = in.readLine(); // retrieve the data
	    int cuttingPoint = data.indexOf(','); // this will be the price
	    String ret = data.substring(0, cuttingPoint);
	    try{
		return Double.parseDouble(ret);
	    }
	    catch(Exception excep){
		return 100.72;
	    }
	} catch (IOException e) {
	    try {
		URL interWebs = new URL(baseURL + ticker + baseURLend); // create a URL instance of the API
	
		URLConnection interwebConnect = interWebs.openConnection();
	
		InputStreamReader isr = new InputStreamReader(interwebConnect.getInputStream()); // Retrieve the csv into a InputStreamReader
				
		BufferedReader in = new BufferedReader(isr); // For efficiency, thank you oracle API
	
		String data = in.readLine(); // retrieve the data
		int cuttingPoint = data.indexOf(','); // this will be the price
		String ret = data.substring(0, cuttingPoint);
		try{
		    return Double.parseDouble(ret);
		}
		catch(Exception excep){
		    return 100.72;
		}
				
	    } catch (Exception excep) {
		return 100.72;
	    }
	}


    }

    public static void main(String[] args) {
    	YahooApi yo = new YahooApi();
		System.out.println(yo.getData("GOOG"));
    }

}
