import java.util.*;
// Used to sort a portfolio. Can be sorted by quantity, though we ended up using it by Ticker for the portfolio printing
public class QuickSort {


    public static void swap( int x, int y, LinkedList<StockPosition> o ) {
		Collections.swap(o, x, y);
    }

    public LinkedList<StockPosition> qsort( LinkedList<StockPosition> d, int comparing ) { 
	if (comparing == 0) // 0 means you are sorting by ticker name
	    qsHelpTick( 0, d.size()-1, d );
	if (comparing == 1) // 1 means compare by quantity of shares held
	    qsHelpQuant( 0, d.size()-1, d );
	return d;
    }

    public static void qsHelpTick( int lo, int hi, LinkedList<StockPosition> d ) { 

	if ( lo >= hi )
	    return;

	int tmpLo = lo; 
	int tmpHi = hi;
	String pivot = d.get(lo).getTicker();
	StockPosition indPiv = d.get(lo);

	while( tmpLo < tmpHi ) {
	    //first, slide markers in as far as possible without swaps
	    while( d.get(tmpLo).getTicker().compareTo(pivot) < 0 )  tmpLo++;
	    while( d.get(tmpHi).getTicker().compareTo(pivot) > 0 )  tmpHi--;

	    if ( d.get(tmpLo).getTicker().compareTo(d.get(tmpHi).getTicker()) != 0 )
		swap( tmpLo, tmpHi, d );

	    //dupe vals found at markers
	    else if ( tmpLo < tmpHi ) { 
		//extra chk for Lo<Hi bc of double marker moves below

		String dupe = d.get(tmpHi).getTicker();

		//if dupe is pivot val, put in lower range
		if ( dupe == pivot ) {
		    swap( ++tmpLo, tmpHi, d );
		}
		else if ( dupe.compareTo(pivot) > 0 ) {
		    //slide upper marker inward 1 pos, then swap
		    swap( tmpLo, --tmpHi, d );
		    //slide upper marker inward again to get past 2nd dupe val
		    tmpHi--;
		}
		else { //dupe < pivot
		    swap( ++tmpLo, tmpHi, d );
		    tmpLo++;
		}
	    }
	}//end big while

	//pivot has been floating around... plant it where it belongs
	d.set(tmpLo, indPiv);

	//recurse on lower and upper ranges
	qsHelpTick( lo, tmpLo-1, d );
	qsHelpTick( tmpLo+1, hi, d );

    }//end qsHelp

    public static void qsHelpQuant( int lo, int hi, LinkedList<StockPosition> d ) {
	if ( lo >= hi )
	    return;

	int tmpLo = lo; 
	int tmpHi = hi;
	int pivot = d.get(lo).getNumShares();
	StockPosition pivotStock = d.get(lo);

	while( tmpLo < tmpHi ) {
	    //first, slide markers in as far as possible without swaps
	    while( d.get(tmpLo).getNumShares() < pivot )  tmpLo++;
	    while( d.get(tmpHi).getNumShares() > pivot )  tmpHi--;

	    if ( d.get(tmpLo).getNumShares() != d.get(tmpHi).getNumShares() )
		swap( tmpLo, tmpHi, d );

	    //dupe vals found at markers
	    else if ( tmpLo < tmpHi ) { 
		//extra chk for Lo<Hi bc of double marker moves below

		int dupe = d.get(tmpHi).getNumShares();

		//if dupe is pivot val, put in lower range
		if ( dupe == pivot ) {
		    swap( ++tmpLo, tmpHi, d );
		}
		else if ( dupe > pivot ) {
		    //slide upper marker inward 1 pos, then swap
		    swap( tmpLo, --tmpHi, d );
		    //slide upper marker inward again to get past 2nd dupe val
		    tmpHi--;
		}
		else { //dupe < pivot
		    swap( ++tmpLo, tmpHi, d );
		    tmpLo++;
		}
	    }
	}//end big while

	//pivot has been floating around... plant it where it belongs
	d.set(tmpLo, pivotStock);

	//recurse on lower and upper ranges
	qsHelpQuant( lo, tmpLo-1, d );
	qsHelpQuant( tmpLo+1, hi, d );
}

    //main method for testing
    public static void main( String[] args ) {

    }//end main

}//end class QuickSort
