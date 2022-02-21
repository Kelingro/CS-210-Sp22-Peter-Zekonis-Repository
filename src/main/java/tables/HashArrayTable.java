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
		this.loadFactor = 0.65;
		this.hTable = new Object[this.capacity];
		 
		clear();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean put(List<Object> row) {
		Object key = row.get(getPrimaryIndex());
		int recycle = -1;
		int index = HashFunction(key);
		int index2 = 0;
		for(int i = 1; i <= hTable.length; i++) {
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
					if((double)(size+contamination)/hTable.length >= loadFactor) rehash();
					return false;
				} 
			}
			else if(hTable[wrap(index+index2)] == TOMBSTONE) {
				if(recycle == -1) {
				recycle = wrap(index+index2);
				index2 = perfectSquare(i);
				}
				else {
					index2 = perfectSquare(i);
					continue;
				}
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
		final int keyHash = HashFunction(row);
		int index2 = 0;
			for(int i = 1; i <= hTable.length; i++) {
				if(hTable[wrap(keyHash+index2)] == null) {
					return false;
				}
				if(hTable[wrap(keyHash+index2)] == TOMBSTONE) {
					index2 = perfectSquare(i);
					continue;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get(Object key) {
		final int keyHash = HashFunction(key);
		int index2 = 0; 
			for(int i = 1; i < hTable.length; i++) {
				if(hTable[wrap(keyHash + index2)] == null) {
					return null;
				}
				else if(hTable[wrap(keyHash + index2)] == TOMBSTONE) {
					index2 = perfectSquare(i);
					continue;
				}
				else if(((List<Object>)hTable[wrap(keyHash + index2)]).get(getPrimaryIndex()).equals(key)) {
					return (List<Object>) hTable[wrap(keyHash + index2)];
				}
				else {
					index2 = perfectSquare(i);
				}
			}
		return null;
	}
	
	public static int HashFunction(Object key) {
		if (key instanceof String) {
			final char[] buff = ((String)key).toCharArray();
			int hash = 0;
			for(int i = buff.length; --i>=0;) {
				hash = 12 + hash*37;
			}
			return hash;
		}
		else {
			return key.hashCode();
		}
//		return key.hashCode();
	}
	
	@Override
	public void clear() {
		final Object[] old = this.hTable;
		final int oldCapacity = hTable.length;
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
				if(index >= hTable.length) {
					return false;
				}
				if(hTable[index]==null || hTable[index]==TOMBSTONE) {
					while(hTable[index] == null||hTable[index] == TOMBSTONE) {
						index++;
						if(index >= hTable.length) {
							return false;
						}
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
		final int oldCapacity = hTable.length;
		size = 0;
		Object[] copy = hTable;
		int newC = 0;
		newC = nextPrime(oldCapacity);
		System.out.println(newC);
		hTable = new Object[newC];
		
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
		if(count >= 1) {
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