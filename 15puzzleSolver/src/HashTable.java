
/**
 * The HashTable class
 * @author Supratim Baruah
 * @version 2014/4/10
 * @param<K>
 * @param<V>
 */
public class HashTable<K, V> {

	private int SIZE;
	private V[] table;
	private int count = 0;
	private float loadfactor = .5f;
	private int threshold;

	/**
	 * Constructor for the hash table
	 * @param size size of the hashtable
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size)
	{
		table = (V[])new Object[size];
		this.SIZE = size;
		threshold = (int) (SIZE * loadfactor);
	}

	/**
	 * Creates a Hash for the key
	 * @param key key
	 * @return hash
	 */
	public int hashThis(K key)
	{
		int hash = key.hashCode() % SIZE;
		return hash;
	}

	/**
	 * Performs linear probing
	 * @param key key
	 * @return the index
	 */
	private int findHash(K key) {
	    int index = hashThis(key);
	    while (table[index] != null && !table[index].equals(key))
	    {
	        index = (index + 1) % SIZE;
	    }
	    return index;
	}

	/**
	 * Puts the value in the hash table
	 * @param key key
	 * @param value value
	 */
	public void put(K key, V value)
	{
		table[findHash(key)] = value;
		if (count++ >= threshold)
		{
			reSize(2 * table.length);
		}
	}

	/**
	 * gets the value from the hashTable
	 * @param key key
	 * @return the value
	 */
	public V get(K key)
	{
		return table[findHash(key)];

	}

	/**
	 * resizes the hash table
	 * @param newSize the new size
	 */
	@SuppressWarnings("unchecked")
	public void reSize(int newSize)
	{
		V[] newTable = (V[])new Object[newSize];
		for (int i = 0; i < SIZE; i++)
		{
			if (table[i] != null)
			{
				V temp = table[i];
				int index = temp.hashCode() % newSize;
				while (newTable[index] != null && !newTable[index].equals(temp))
				{
					index = (index + 1) % newSize;
				}
				newTable[index] = temp;
			}

		}
		table = newTable;
		SIZE = newSize;
		threshold = (int) (SIZE * loadfactor);
	}

}
