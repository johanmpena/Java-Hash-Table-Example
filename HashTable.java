import java.io.*;

public class HashTable
{
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem;
	
	/*
	 * Constructor
	 * --------------
	 */
	HashTable(int size){
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1);
	}
	
	/*
	 * Display Table Method
	 * ---------------------
	 * Display values in table
	 */
	public void displayTable() {
		System.out.println("Table: ");
		for(int j = 0; j <arraySize; j++) {
			if(hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else 
				System.out.print("** ");
		}
		System.out.println(" ");
		}
	
	/*
	 * Hash Function 1 Method
	 * ------------------------
	 * Hashes keys 
	 */
	public int hashFunc1(int key) {
		return key % arraySize;
	}
	
	/*
	 * Hash Function 2 Method
	 * -------------------------
	 * Hashes keys a second time.
	 */
	public int hashFunc2(int key) {
		return 5 - key % 5;
	}

	/*
	 * Insert Method 
	 * --------------------------
	 * Inserts method into hash table.
	 */
	public void insert (int key, DataItem item) {
		int hashVal = hashFunc1(key);				// hash the key
		int stepSize = hashFunc2(key);				// get step size
		
		while(hashArray[hashVal] != null && 
				       hashArray[hashVal].getKey() != -1)
		{
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		hashArray[hashVal] = item;
	}
	
	/*
	 * Delete Method
	 * ------------------------------
	 * Deletes item in hash table and returns value
	 * deleted. 
	 */
	public DataItem delete(int key) {
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		
		while(hashArray[hashVal] != null)
		{
			if(hashArray[hashVal].getKey() == key)
			{
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return temp;
			}
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}
	
	/*
	 * Find Method
	 * ----------------------------------
	 * Assumes table not full.
	 */
	public DataItem find(int key){
		int hashVal = hashFunc1(key);
		int stepSize = hashFunc2(key);
		
		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].getKey() == key)
				return hashArray[hashVal];
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}
}