package tables;
import java.util.Iterator;
import java.util.List;

/**
 * Implements a hash-based table
 * using an array data structure.
 */
public class HashArrayTable extends Table {
	
	
	
	//Remy's code
//	private static class Node{
//		final int keyHash;
//		final Object key;
//		List<Object>value;
//		
//		public Node(Object key, List<Object>value) {
//			this.keyHash = HashArrayTable.HashFunction(key);
//			this.key = key;
//			this.value = value;
//		}
//	}
//	
//	private static final int DEFAULT_CAPACITY = 19;
//	private final int MAX_SIZE = Integer.MAX_VALUE - 8;
//	private Node[] hTable;
//	private float loadFactor;
//	private int capacity;
//	private int size;
//
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
//		this.capacity = DEFAULT_CAPACITY;
//		this.size = 0;
//		this.loadFactor = 0.5f;
//		this.hTable = new Node[this.capacity];
//		
//	}
//
//	@Override
//	public boolean put(List<Object> row) {
//		//same as get except:
//		//hit: update row accordingly
//		//miss: add new row accordingly
//		//
//		//bookmark for tombstone
//		if (row == null) throw new NullPointerException("row == null");
//		final Object key = row.get(getPrimaryIndex());
//		if (key == null)throw new NullPointerException("key == null");
//		
//		Node node = new Node(key, row);
//		
//		int index = hash1(node.keyHash);
//		int index2 = hash2(node.keyHash);
//		
//		if (hTable[index] != null && hTable[index].key.equals(key)) {
//			hTable[index] = node;
//			return true;
//		}
//		else if(hTable[index2] != null && hTable[index].key.equals(key)) {
//			hTable[index2]=node;
//			return true;
//		}
//		else {
//			int pos = index;
//					if (hTable[pos]==null) {
//						hTable[pos] = node;
//						size++;
//						return false;
//					}
//					else {
//						Node copy = hTable[pos];
//						hTable[pos] = node;
//						node = copy;
//					}
//					if(pos == index) {
//						pos = index2;
//					}
//					else {
//						pos = index;
//					}
//						rehash();
//						return put(row);
//				}
//	}
//
//	@Override
//	public boolean remove(Object key) {
//		//tbd
//		// with open addressing and tombstones
//		// calculate hash of key using hash function
//		//for each step number from 0 to capacity-1
//		//try element index of 1
//		if(null == key) throw new NullPointerException("key == null");
//		
//		final int keyHash = HashFunction(key);
//		int index = hash1(keyHash);
//		int index2 = hash2(keyHash);
//		if(hTable[index] != null && hTable[index].key.equals(key)) {
//			hTable[index] = null;
//			size--;
//			return true;
//		}
//		else if(hTable[index2] != null && hTable[index].key.equals(key)) {
//			hTable[index2] = null;
//			size --;
//			return true;
//		}
//		else {
//			return false;	
//		}
//	}
//
//	// might want to do all this first
//	@Override
//	public List<Object> get(Object key) {
//		// find hash of key
//		// if using hashing2 find hash 2
//		
//		//For double hash
//		//	also find hash2 of key using the second hash function
//		// for each possible location (for all possible step numbers):
//		// 		fore each step number from 0 to capacity-1;
//		//		try is wrap(hash + hash2*step)
//		
//		//Based on above logic
//		// for each possible location
//		// 		try the location
//		//
//		//		if the location is null
//		// 			miss return null
//		//
//		//		otherwise, it wasnt null:
//		//			check if the key in the row is the parameter key:
//		//				if it hits then return that row
//		if (null == key) throw new NullPointerException("key == null");
//		final int keyHash = HashFunction(key);
//		int index = hash1(keyHash);
//		int index2 = hash2(keyHash);
//		
//		if (hTable[index] != null && hTable[index].key.equals(key)) {
//			return hTable [index].value;
//		}
//		else if(hTable[index2] != null && hTable[index2].key.equals(key)) {
//			return hTable[index2].value;
//		}
//		else {
//			return null;
//		}
//	}
//	
//	public static int HashFunction(Object key) {
//		if (key instanceof String) {
//			final char[] buff = ((String)key).toCharArray();
//			int hash = 0;
//			for(int i = buff.length; --i>=0;) {
//				hash = buff[i] +hash*37;
//			}
//			return hash;
//		}
//		else {
//			return key.hashCode();
//		}
//	}
//	
//	@Override
//	public void clear() {
//		final Node[] old = this.hTable;
//		final int oldCapacity = capacity;
//		for(int i = oldCapacity; --i >= 0;) {
//			old[i] = null;
//			this.capacity = DEFAULT_CAPACITY;
//			this.hTable = new Node[this.capacity];
//			this.size = 0;
//		}	
//	}
//	
//
//	@Override
//	public int size() {
//		
//		return size;
//	}
//
//	@Override
//	public int capacity() {
//		return capacity;
//	}
//
//	@Override
//	public Iterator<List<Object>> iterator() {
//		// follow search table template
//		
//		// with open
//		//fields:
//			//index starts at 0
//		//hasNext:
//			//index skips null
//			// return whether index in bounds
//		//next
//			//if not hasNext throw no such element
//			// temp row is row at index
//			// increment index (optional: skip nulls/tombstones)
//			// return temp row
//		return new iteratorTable(this.hTable, this.size);
//	}
//	private int hash1(int keyHash) {
//		return keyHash%capacity;	
//	}
//	private int hash2(int keyHash) {
//		return(keyHash/capacity)%capacity;
//	}
//	public void rehash() {
//		// point backup reference to array
//		// reinitialize array with next prime number capacity
//		// reset size and contamination
//		
//		// with open addressing and tombstones
//		// for each element in backup
//			//if the element isnt nulll and isnt tombstone
//				// put the row (put method itself)
//		final int oldCapacity = capacity;
//		final int threshold = (int) Math.min(oldCapacity*loadFactor, MAX_SIZE +1);
//		
//		if (size < threshold) {
//			return;
//		}
//		final Node[] copy = this.hTable.clone();
//		final int oldSize = this.size;
//		capacity = Math.min(capacity*2+1, MAX_SIZE);
//		
//		if(oldCapacity == capacity) {
//			return;
//		}
//		hTable = new Node[capacity];
//		
//		for(int i = 0; i<oldCapacity; ++i) {
//			if(copy[i] != null) {
//				put(copy[i].value);
//			}
//		}
//		this.size = oldSize;
//	}
//	private static class iteratorTable implements Iterator<List<Object>> {
//		private final int size;
//		private final Node[] items;
//		private int index = 0;
//		
//		public iteratorTable(Node[] org, int size){
//			this.size = size;
//			this.items = new Node[size];
//			
//			for(int i = 0, j = 0; i < org.length; i++) {
//				if(null == org[i]) continue;
//				items[j++] = org[i];
//			}
//		}
//		
//		@Override
//		public boolean hasNext() {
//			// TODO Auto-generated method stub
//			return index<size;
//		}
//
//		@Override
//		public List<Object> next() {
//			// TODO Auto-generated method stub
//			return items[index++].value;
//		}
//		
//	}
}
