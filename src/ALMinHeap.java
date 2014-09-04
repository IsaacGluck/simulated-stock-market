/*****************************************************
 * class ALHeap
 * Implements a min heap using an ArrayList as underlying container
 * Used in Running Median (which is used in stock pricing)
 *****************************************************/

import java.util.ArrayList;

public class ALMinHeap {

    //instance vars
    private ArrayList<Double> _heap; //underlying container is array of Integers
    private int _size;

    /*****************************************************
     * default constructor  ---  inits empty heap
     *****************************************************/
    public ALMinHeap() 
    { 
	_heap = new ArrayList<Double>();
	_size = 0;
    }



    /*****************************************************
     * toString()  ---  overrides inherited method
     * Returns either 
     * a) a level-order traversal of the tree (simple version)
     * b) ASCII representation of the tree (bit more complicated, much more fun)
     *****************************************************/
    public String toString() 
    { 
	//simple version:
	//return _heap.toString(); 

	//prettier version:
	String lvlOrdTrav = "heap size " + _heap.size() + "\n";

	if ( _heap.size() == 0 ) return lvlOrdTrav;

	int h = 1; //init height to 1
	for( int i = 0; i < _heap.size(); i++ ) {
	    lvlOrdTrav += i + ":" + _heap.get(i) + " ";
	    if ( i >= Math.pow(2,h) - 2 ) {
		lvlOrdTrav += "\n";
		h++;
	    }
	}
	return lvlOrdTrav;
    }//O(n)

    public int size(){
	return _size;
    }

    /*****************************************************
     * boolean isEmpty()
     * Returns true if no meaningful elements in heap, false otherwise
     *****************************************************/
    public boolean isEmpty() { return _heap.isEmpty(); } //O(1)



    /*****************************************************
      * double peekMin()
      * Returns min value in heap
      * Postcondition: Heap remains unchanged.
      *****************************************************/
    public double peekMin() { 
	if ( _heap.size() < 1 )
	    return -1.0;
	else
	    return _heap.get(0); 
    } //O(1)



    /*****************************************************
     * add(Double) 
     * Inserts an element in the heap
     * Postcondition: Tree maintains heap property.
     *****************************************************/
    public void add( Double addVal ) { 

	//Add value as last node, to maintain balance, completeness of tree
	_heap.add( addVal );
	_size++;

	int addValPos = _heap.size() - 1;
	int parentPos;

	while( addValPos > 0 ) { //potentially swap until reach root

	    //pinpoint parent
	    parentPos = (addValPos-1) / 2;

	    if ( addVal < _heap.get(parentPos) ) {//addVal < parent
		swap( addValPos, parentPos );
		addValPos = parentPos;
	    }
	    else 
		break;
	}
    } //O(logn)



    /*****************************************************
     * removeMin()  ---  means of removing an element from heap
     * Removes and returns least element in heap.
     * Postcondition: Tree maintains heap property.
     *****************************************************/
    public double removeMin() {

	if ( _heap.size() == 0 ) 
	    return -1.0;
	else
	    _size--;

	//store root value for return at end of fxn
	double retVal = peekMin();

	//store val about to be swapped into root
	double foo = _heap.get( _heap.size() - 1);

	//swap last (rightmost, deepest) leaf with root
	swap( 0, _heap.size() - 1 );

	//lop off last leaf
	_heap.remove( _heap.size() - 1);

	// walk the now-out-of-place root node down the tree...
	int pos = 0;
	int minChildPos;

	while( pos < _heap.size() ) {

	    //choose child w/ min value, or check for child
	    minChildPos = minChildPos(pos);

	    //if no children, then i've walked far enough
	    if ( minChildPos == -1 ) 
		break;
	    //if i am less than my least child, then i've walked far enough
	    else if ( foo <= _heap.get(minChildPos) ) 
		break;
	    //if i am > least child, swap with that child
	    else {
		swap( pos, minChildPos );
		pos = minChildPos;
	    }
	}
	//return removed value
	return retVal;
    }//O(logn)



    /*****************************************************
     * minChildPos(int)  ---  helper fxn for removeMin()
     * Returns index of least child, or 
     * -1 if no children, or if input pos is not in ArrayList
     * Postcondition: Tree unchanged
     *****************************************************/
    private int minChildPos( int pos ) {
	int retVal;
	int lc = 2*pos + 1; //index of left child
	int rc = 2*pos + 2; //index of right child

	//pos is not in the heap or pos is a leaf position
	if ( pos < 0 || pos >= _heap.size() || lc >= _heap.size() )
	    retVal = -1;
	//if no right child, then left child is only option for min
	else if ( rc >= _heap.size() )
	    retVal = lc;
	//have 2 children, so compare to find least 
	else if ( _heap.get(lc) < (_heap.get(rc)) )
	    retVal = lc;
	else
	    retVal = rc;
	return retVal;
    }//O(1)



    //************ aux helper fxns ***************
    private double minOf( double a, double b ) {
	if ( a < b )
	    return a;
	else
	    return b;
    }

    //swap for an ArrayList
    private void swap( int pos1, int pos2 ) {
	_heap.set( pos1, _heap.set( pos2, _heap.get(pos1) ) );	
    }
    //********************************************



    //main method for testing
    public static void main( String[] args ) {

	/*
	ALMinHeap pile = new ALMinHeap();

	pile.add(2);
	System.out.println(pile);
	pile.add(4);
	System.out.println(pile);
	pile.add(6);
	System.out.println(pile);
	pile.add(8);
	System.out.println(pile);
	pile.add(10);
	System.out.println(pile);
	pile.add(1);
	System.out.println(pile);
	pile.add(3);
	System.out.println(pile);
	pile.add(5);
	System.out.println(pile);
	pile.add(7);
	System.out.println(pile);
	pile.add(9);
	System.out.println(pile);

	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	System.out.println("removing " + pile.removeMin() + "...");
	System.out.println(pile);
	*/
    }//end main()

}//end class ALHeap
