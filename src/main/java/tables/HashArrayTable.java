//package tables;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Implements a hash-based table
// * using an array data structure.
// */
//public class HashArrayTable extends Table {
//private Object[] Node;
//		
//	private static final int DEFAULT_CAPACITY = 19;
//	//private int capacity = Node.length;
//	private int size;
//	private final Object TOMBSTONE = new Object();
//	private static final double LoadFactor = 0.75;
//	
//	/**
//	 * Creates a table and initializes
//	 * the data structure.
//	 *
//	 * @param tableName the table name
//	 * @param columnNames the column names
//	 * @param columnTypes the column types
//	 * @param primaryIndex the primary index
//	 */
//	public HashArrayTable(String tableName, List<String> columnNames, List<String> columnTypes, Integer primaryIndex) {
//		setTableName(tableName);
//		setColumnNames(columnNames);
//		setColumnTypes(columnTypes);
//		setPrimaryIndex(primaryIndex);
//		
//		clear();
//		
//	}
//
//	@Override
//	public boolean put(List<Object> key) {
//		if (null == key) throw new NullPointerException("key == null");
//		int recycle = -1;
//		int keyHash = HashFunction(key);
//		if (Node[wrap(keyHash)] != null && Node[keyHash].equals(key)) {
//			Node[wrap(keyHash)] = key;
//			return true;
//		}
//		else {
//			if(Node[wrap(keyHash)] != null && Node[wrap(keyHash)] == TOMBSTONE) {recycle = wrap(keyHash);}
//			int i = 1;
//		while(Node[wrap(keyHash+perfectSquare(i))] != null) {
//			if(Node[wrap(keyHash+perfectSquare(i))].equals(key)) {
//				Node[wrap(keyHash+perfectSquare(i))] = key;
//				return true;
//			}
//			else{
//				i++;
//			}
//		}
//		if(recycle>-1) {
//			Node[recycle] = key;
//			return true;
//		}
//		else {
//		Node[wrap(keyHash+perfectSquare(i))] = key;
//		size++;
//		if((size/capacity()) > LoadFactor ) {rehash();}
//		return true;
//		}
//	}
//}
//			
//
//
//	@Override
//	public boolean remove(Object key) {
//		if (null == key) throw new NullPointerException("key == null");
//		int keyHash = HashFunction(key);
//		if (Node[wrap(keyHash)] != null && Node[keyHash].equals(key)) {
//			Node[wrap(keyHash)] = TOMBSTONE;
//			return true;
//		}
//		else {
//			int i = 1;
//		while(Node[wrap(keyHash+perfectSquare(i))] != null) {
//			if(Node[wrap(keyHash+perfectSquare(i))].equals(key)) {
//				Node[wrap(keyHash+perfectSquare(i))] = TOMBSTONE;
//				return true;
//			}
//		}
//		}
//		return false;
//	}
//
//	// might want to do all this first
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object> get(Object key) {
//		if (null == key) throw new NullPointerException("key == null");
//		int keyHash = HashFunction(key);
//		if (Node[wrap(keyHash)] != null && Node[keyHash].equals(key)) {
//			return (List<Object>) key;
//		}
//		else {
//			int i = 1;
//		while(Node[wrap(keyHash+perfectSquare(i))] != null) {
//			if(Node[wrap(keyHash+perfectSquare(i))].equals(key)) {
//				return (List<Object>) key;
//			}
//			else{
//				i++;
//			}
//		}
//		}
//		return null;
//	}
//	
//	public static int HashFunction(Object key) {
//		if (key instanceof String) {
//			final char[] buff = ((String)key).toCharArray();
//			int hash = 0;
//			for(int i = buff.length; i>=0; i--) {
//				hash = buff[i] +hash*19;
//			}
//			return hash;
//		}
//		else {
//			return key.hashCode();
//		}
//	}
//	
//	private int wrap (int index) {
//	return Math.floorMod(index, Node.length);	
//	}
//	
//	private boolean isPrime(int number){
//	if(number%2 == 0) {
//		return false;
//	}	
//	int count = 0;
//	for (int i = 3; i<number; i++) {
//		if(number%i == 0) {
//			count++;
//		}
//		i++;
//	}
//	if(count == 1) {
//		return false;
//	}
//	else {
//		return true;
//	}
//	}
//	
//	private int nextPrime(int prev) {
//	int newNum = prev*2 +1;
//		if(newNum%4 != 3) {
//			newNum = newNum+2;
//		}
//		while (isPrime(newNum) == false) {
//			newNum = newNum+4;
//		}
//		return newNum;
//	}
//	
//	@Override
//	public void clear() {
//	Node = new Object[DEFAULT_CAPACITY];
//	size = 0;
//		}	
//
//	private int perfectSquare(int step) {
//		int stepVal = 0;
//		if(step%2 == 0) {
//			stepVal = step*step;
//		}
//		else {
//			stepVal = 0 - step*step;
//		}
//		return stepVal;
//	}
//	
//	@Override
//	public int size() {
//		
//		return size;
//	}
//
//	@Override
//	public Iterator<List<Object>> iterator() {
//		return new Iterator<>() {
//			int index = 0;
//			private 
//
//			@Override
//			public boolean hasNext() {
//				// TODO Auto-generated method stub
//				return index<size;
//			}
//	
//			@Override
//			public List<Object> next() {
//				// TODO Auto-generated method stub
//				return items[index++].value;
//			}
//		};
//	}
//	@SuppressWarnings("unchecked")
//	public void rehash() {
//		//point backup reference to array
//		//reinitialize array with next prime capacity
//		// reset size
//			//for each element in backup
//			//put row if row not tombstone or null
//		final int oldCapacity = Node.length;
//		size = 0;
//		
//		Object[] copy = this.Node.clone();
//		final int oldSize = this.size;
//		int capacity = capacity();
//		capacity = nextPrime(capacity);
//		Node = new Object[capacity];
//		for(int i = 0; i<oldCapacity; ++i) {
//			if(copy[i] != null && copy[i] != TOMBSTONE) {
//				put((List<Object>) copy[i]);
//			}
//		}
//		this.size = oldSize;
//	}
//
//	@Override
//	public int capacity() {
//		// TODO Auto-generated method stub
//		return Node.length;
//	}
//		
//}

package tables;
import java.util.Iterator;
import java.util.List;
import java.util.*;
/**
 * Implements a hash-based table
 * using an array data structure.
 */
public class HashArrayTable extends Table {
//	private static class Node{
//		final int keyHash;
//		final Object key;
//		List<Object>value;
//		
//		public Node(Object key, List<Object>value) {
//			this.keyHash = HashArrayTable.HashFunction(key);
//			this.key = key;
//			this.value = value;
//		
//	}
//	private Object[] Node;
	
	
	private static final int DEFAULT_CAPACITY = 19;
	private Object[] hTable;
	private double loadFactor;
	private int capacity;
	private int size;
	private final Object TOMBSTONE = new Object();
	private int contamination;

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
		
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.loadFactor = 0.75;
		this.hTable = new Object[this.capacity];
		
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean put(List<Object> row) {
		if (row == null) throw new NullPointerException("row == null");
		Object key = row.get(getPrimaryIndex());
		if (key == null)throw new NullPointerException("key == null");
		
//		Node node = new Node(key, row);
	 
		int index = HashFunction(key);
		int index2 = 1;
		
		if (hTable[wrap(index)] != null && ((List<Object>)hTable[wrap(index)]).get(getPrimaryIndex()).equals(key)) {
			hTable[wrap(index)] = row;
			return true;
		}
		int recycle = -1;
		if(hTable[wrap(index)] != null && hTable[wrap(index)].equals(TOMBSTONE)) {
			recycle = index;
		}
		for(int i=1; i<=capacity(); i++) {
			if(hTable[wrap(index+index2)] == null) {
				if (recycle != -1) {
					hTable[recycle] = row;
					size++;
					contamination--;
					return false;
				}
				else {
					hTable[wrap(index + index2)] = row;
					size++;
					if((double)(size+contamination)/capacity >= loadFactor) rehash();
					return false;
				}
			}
			else if(hTable[wrap(index+index2)] == TOMBSTONE && recycle == -1) {
				recycle = wrap(index+index2);
				index2 = perfectSquare(i);
			}
			else if(((List<Object>)hTable[wrap(index+index2)]).get(getPrimaryIndex()).equals(key)) {
				hTable[wrap(index + index2)] = row;
				return true;
				}
			else {
				index2 = perfectSquare(i);
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object row) {
		if(null == row) throw new NullPointerException("key == null");
		final int keyHash = HashFunction(row);
		
		int index2 = 1;
		if(hTable[wrap(keyHash)] != null &&  ((List<Object>)hTable[wrap(keyHash)]).get(getPrimaryIndex()).equals(row)) {
			hTable[wrap(keyHash)] = TOMBSTONE;
			size--;
			contamination++;
			return true;
		}
		else {
			for(int i = 0; i < capacity(); i++) {
				if(hTable[wrap(keyHash+index2)] == null) {
					return false;
				}
				else if(((List<Object>)hTable[wrap(keyHash+index2)]).get(getPrimaryIndex()).equals(row)) {
					hTable[wrap(keyHash+index2)] = TOMBSTONE;
					size--;
					contamination++;
					return true;
				}
				else {
					index2 = perfectSquare(i);
				}
			}
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get(Object key) {
		if (null == key) throw new NullPointerException("key == null");
		final int keyHash = HashFunction(key);
		int index2 = 1;
		if (hTable[wrap(keyHash)] != null && hTable[wrap(keyHash)] == (key)) {
			return (List<Object>) hTable[wrap(keyHash)];
		}
		else {
			for(int i = 1; i<=capacity(); i++) {
				if(hTable[wrap(keyHash + index2)] == null) {
					return null;
				}
				else if(hTable[wrap(keyHash + index2)] == (key)) {
					return (List<Object>) hTable[wrap(keyHash + index2)];
				}
				else {
					index2 = perfectSquare(i);
				}
			}
		}

		return null;
	}
	
	public static int HashFunction(Object key) {
		if (key instanceof String) {
			final char[] buff = ((String)key).toCharArray();
			int hash = 0;
			for(int i = buff.length; --i>=0;) {
				hash = buff[i] +hash*37;
			}
			return hash;
		}
		else {
			return key.hashCode();
		}
	}
	
	@Override
	public void clear() {
		final Object[] old = this.hTable;
		final int oldCapacity = capacity;
		for(int i = oldCapacity; --i >= 0;) {
			old[i] = null;
			this.capacity = DEFAULT_CAPACITY;
			this.hTable = new Object[this.capacity];
			this.size = 0;
		}	
	}
	

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public int capacity() {
		return hTable.length;
	}

	@Override
	public Iterator<List<Object>> iterator() {
		return new Iterator<>() {
			int index = 0;
			@Override
			public boolean hasNext() {
				if(hTable[index]==null||hTable[index]==TOMBSTONE) {
					while(hTable[index]==null||hTable[index]==TOMBSTONE) {
						index++;
					}
				}
				return index < hTable.length;
			}
			@SuppressWarnings("unchecked")
			@Override
			public List<Object> next() {
			if (hasNext() != true) {
				throw new NoSuchElementException();
			}
			Object temp = hTable[index];
			index++;
			return (List<Object>) temp;
			}
		};
	}
	public int perfectSquare(int step) {
		int stepVal = 0;
		if(step%2 == 0) {
			stepVal = step*step;
		}
		else {
			stepVal = 0 - step*step;
		}
		return stepVal;
	}
	@SuppressWarnings("unchecked")
	public void rehash() {
		final int oldCapacity = capacity;
		size = 0;
		Object[] copy = this.hTable.clone();
		capacity = nextPrime(oldCapacity);
		if(oldCapacity == capacity) {
			return;
		}
		System.out.println(capacity);
		hTable = new Object[capacity];
		
		for(int i = 0; i<oldCapacity; ++i) {
			if(copy[i] != null && copy[i] != TOMBSTONE) {
				put((List<Object>) copy[i]);
			}
		}
	}
	
	private boolean isPrime(int number){
		if(number%2 == 0) {
			return false;
		}	
		int count = 0;
		for (int i = 3; i<number; i++) {
			if(number%i == 0) {
				count++;
			}
			i++;
		}
		if(count == 1) {
			return false;
		}
		else {
			return true;
		}
		}
		
		private int nextPrime(int prev) {
		int newNum = prev*2 +1;
			if(newNum%4 != 3) {
				newNum = newNum+2;
			}
			while (isPrime(newNum) == false) {
				newNum = newNum+4;
			}
			return newNum;
		}
		
	private int wrap (int index) {
		return Math.floorMod(index, hTable.length);	
		}
}