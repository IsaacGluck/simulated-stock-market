Goldberg Saacs Stock-Simulator
===============================
### By Isaac Gluck & Coby Goldberg 

####  External Libraries & APIs 
- Yahoo Finance API: https://code.google.com/p/yahoo-finance-managed/wiki/CSVAPI
	- further Yahoo Finance API documentation: http://www.gummy-stuff.org/Yahoo-data.htm
- JMathPlot from µ-Labs: https://sites.google.com/site/mulabsltd/products/jmathplot

#### Running the Project from the .jar
1. Simply double click the .jar file to run the program.
2. Have fun!

#### Running the Project from the Terminal
##### For Linux, OSX or Windows
1. Running with -cp or -classpath (On windows use ; instead of : for the -cp)
	1. In a new terminal, go to the directory where you downloaded the project
	2. Compile with: 
```
javac -cp path/to/project/pd89_Goldberg-Saacs_CobyG-IsaacG/lib/jmathplot.jar:. MainFrame.java
```
	3. Run with: 
```
java -cp path/to/project/pd89_Goldberg-Saacs_CobyG-IsaacG/lib/jmathplot.jar:. MainFrame
```

##### Using Linux or OSX

2. For multiple runs in a terminal session you can temporarily set the CLASSPATH variable
	1. In a new terminal, go to the directory where you downloaded the project
	2. Type: 
```
export CLASSPATH=path/to/project/pd89_Goldberg-Saacs_CobyG-IsaacG/lib/jmathplot.jar:.
```
	3. Compile with: 
```
javac MainFrame.java
```
	4. Run with: 
```
java MainFrame
```
3. For long time use you can format the `CLASSPATH` variable in the bash initializer file
	1. Go to the bash initializer file and open it with a text editor
		- On OSX `~/.bash_profile`
		- On Linux `~/.bashrc`
	2. Type: 
```
export CLASSPATH=path/to/project/pd89_Goldberg-Saacs_CobyG-IsaacG/lib/jmathplot.jar:.
```
	3. Compile and Run MainFrame per usual

##### FOR THOSE LOOKING FOR A MORE "REALISTIC" INVESTING EXPERIENCE:
1. Lower the "market strength" in Market to a value closer to 0.
2. Lower the stock's volatilities.
3. This will create much more realistic price movement and uglier graphs
