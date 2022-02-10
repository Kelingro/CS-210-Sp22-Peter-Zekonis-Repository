package tables;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements a search-based table
 * using a list data structure.
 */
public class SearchTable extends Table {
	private List<List<Object>> list;

	/**
	 * Creates a table and initializes
	 * the data structure.
	 *
	 * @param tableName the table name
	 * @param columnNames the column names
	 * @param columnTypes the column types
	 * @param primaryIndex the primary index
	 */
	public SearchTable(String tableName, List<String> columnNames, List<String> columnTypes, Integer primaryIndex) {
		setTableName(tableName);
		setColumnNames(columnNames);
		setColumnTypes(columnTypes);
		setPrimaryIndex(primaryIndex);

		list = new LinkedList<>();
	}

	@Override
	public void clear() {
		list.clear();
	}
	
	private class RowKeyComparator implements Comparator<Object>{

		@Override
		public int compare(Object o1, Object o2) {
			
			return keyOf(o1).compareTo(keyOf(o2));
			
		}
		@SuppressWarnings("unchecked")
		private Comparable<Object> keyOf(Object obj) {
			if(obj instanceof List<?> row) {
				return (Comparable<Object>)row.get(primaryIndex);
			}
			else {
				return (Comparable<Object>) obj;
			}
		}
		
	}

	@Override
	public boolean put(List<Object> row) {
		int index= Collections.binarySearch(list, row, new RowKeyComparator());
		if(index>=0) {
			list.set(index, row);
			return true;
		}
		else {
			list.add(-index-1, row);
			return false;
			}
		}

	@Override
	public boolean remove(Object key) {
		int index= Collections.binarySearch(list, key,new RowKeyComparator());
		if(index>=0) {
			list.remove(index);
			return true;
		}
		else {
			return false;
			}
	}

	@Override
	public List<Object> get(Object key) {
		int index= Collections.binarySearch(list, key, new RowKeyComparator());
		if(index>=0) {
			return list.get(index);
		}
		else {
			return null;
			}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public int capacity() {
		return size();
	}

	@Override
	public Iterator<List<Object>> iterator() {
		return new Iterator<>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < list.size();
			}

			@Override
			public List<Object> next() {
				return list.get(index++);
			}
		};
	}
}