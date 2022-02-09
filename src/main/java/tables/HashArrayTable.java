      package tables;

import java.util.Iterator;
import java.util.List;

/**
 * Implements a hash-based table
 * using an array data structure.
 */
public class HashArrayTable extends Table {
	private Object[] array;
	//capacity = array.length
	private int size;
	
	
	//add prime number method up here

	/**
	 * Creates a table and initializes
	 * the data structure.
	 *
	 * @param tableName the table name
	 * @param columnNames the column names
	 * @param columnTypes the column types
	 * @param primaryIndex the primary index
	 */
	public HashArrayTable(String tableName, List<String> columnNames, List<String> columnTypes, Integer primaryIndex) {
		setTableName(tableName);
		setColumnNames(columnNames);
		setColumnTypes(columnTypes);
		setPrimaryIndex(primaryIndex);
		
		clear();
	}
	
	private int HashFunction(Object key) {
		if (key instanceof String) {
			final char[] index = ((String)key).toCharArray();
			int hash = 0;
			for(int i = index.length; --i>=0;) {
				hash = index[i] +hash*17;
			}
			return hash;
		}
		else {
			return key.hashCode();
		}
	}

	@Override
	public void clear() {
		
		array = new Object[19];//some number less than 20
		size = 0;
		
	}

	@Override
	public boolean put(List<Object> row) {
		//same as get except:
		//hit: update row accordingly
		//miss: add new row accordingly
		return false;
	}

	@Override
	public boolean remove(Object key) {
		//tbd
		return false;
	}

	@Override
	public List<Object> get(Object key) {
		// find hash of key
		// if using hashing2 find hash 2
		
		//For ASQP
		//	for each step number from 0 to capacity -1:
			//try is wrap(hash + asqp(step))
		
		//For double hash
		//	also find hash2 of key using the second hash function
		// for each possible location (for all possible step numbers):
		//		try is wrap(hash + hash2*step)
		
		//for separate chaining
		// if the chain isnt null:
		//for the chain at the hash index, for each of its elements:
		//		try that element
		//otherwise miss, return null
		
		//Based on above logic
		// for each possible location
		// 		try the location
		//
		//		if the location is null
		// 			miss return null
		//
		//		otherwise, it wasnt null:
		//			check if the key in the row is the parameter key:
		//				if it hits then return that row
		
		return null;
	}
	
	//helper methods here
	
	//hash function
	private int hashFunction(Object key) {
		if (key instanceof String) {
			//original algorithm
			//sum of 0
			//for each character of the string key:
			// add something onto the sum based on that character
			// recommended do some other large math op
			
			return 0; //return resulting sum
		}
		else {
			return key.hashCode();
		}
		
	}
	//if open addressing
	//asqp offset function OR second hash function
	
	// a function which returns + or - perfect square at a particular step in the sequence
	// if we're at the 0th step in hte probe... hash +0... return 0 squared
	// if 1st step in the probe... hash -1 1 squared 1 odd = -
	// if 2nd step in the probe... hash +4 2 squared positive
	// if 3rd step in the probe... hash -9 3 squared 3 odd = -
	private int asqp(int step) {
		return 0;
		//if step is even
			// then return step squared
		// otherwise
			// then return negative (step squared)
	}
	
	// if double hashing
	private int hashFunction2(Object key) {
		// for some prime p< capacity n
		// return p -(hash1(key) mod p)
		// be sure to use the correct mod operation
	
		return 0;
	}
	
	//in java % is the mod op
	// doesnt work the way in modulus is division is defined in algebra
	//instead use Math.floorMod(numerator, denominator) -> remainder
	
	// modulus wrapper wrapping index for containing keys
	private int wrap(int index) {
		return Math.floorMod(index, array.length);
		
	}
	// may need wrap(hashfunction(key) + offset)
	
	//prime number generator if using asqp needs to be modified needs to be done not optional
	
	// start with 23
	// "next usable prime capacity"
	// start 23
		// double add 1 if it isnt prime then 2 until you find suitible prime
	private int nextPrime(int prev) {
		// start with the next being prev*2+1
		
		// while that next number is not prime
			//update next to increase by 2
		// return next prime
		return 0;
		//asqp only
			//if the next number isnt congruent to 3 modulo 4
				// step up by +2
			// while the next number isn't prime 
				// update the next to increase by 4
	}
	
	private boolean isPrime(int number) {
		// if the number is even return false
		
		// try all feasible factors of the odd number
		// start with 3, 5, 7, 9, largest odd number possible to be a factor sqrt (number) rounded down
			// if number is div. by the factor, return false
		
		// if it has factors return false
		
		// otherwise return true
		return 0;
	}
	

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public int capacity() {
		return array.length;
	}

	@Override
	public Iterator<List<Object>> iterator() {
		return null;
	}
}
