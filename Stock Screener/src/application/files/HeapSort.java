package application.files;

import java.util.Comparator;

/** Implementation of the heapsort algorithm. (page 449) */
public class HeapSort 
{
	/**
	 * Sort the array using heapsort algorithm.
	 * pre: Table contains Comparable items.
	 * post: Table is sorted.
	 * @param table, the array to be sorted.
	 */
	public static <T extends Comparable<T>> void sort(T[] table,@SuppressWarnings("rawtypes") Comparator comp)
	{
		buildHeap(table);
		shrinkHeap(table,comp);
	}
	/**
	 * Optimized heapsort algorithm, if the array is already in heap order
	 * then the algorithm skis straight to the sorting of the heap.
	 * pre: Table contains Comparable items.
	 * post: Table is sorted.
	 * @param table, the array to be sorted.
	 * @param heap, true if the heap is ALREADY in heap order. false otherwise.
	 */
	public static <T extends Comparable<T>> void sort(T[] table, boolean heap, @SuppressWarnings("rawtypes") Comparator comp)
	{
		if(!heap)
		{
			buildHeap(table);
			shrinkHeap(table,comp);
		}
		else
		{
			shrinkHeap(table,comp);
		}
	}
	/**
	 * Transforms the table into a heap.
	 * pre: The array contains at least one item.
	 * post: All items in the array are in heap order. 
	 * @param table, the array to be transformed into a heap.
	 */
	private static <T extends Comparable<T>> void buildHeap(T[] table)
	{
		int n = 1;
		
		while(n < table.length)
		{
			n++;
			int child = n - 1;
			int parent = (child - 1) / 2;
			
			while(parent >= 0 && table[parent].compareTo(table[child]) < 0)
			{
				swap(table, parent, child);
				child = parent;
				parent = (child - 1) / 2;
			}
		}
	}
	/**
	 * Transforms a heap into a sorted array.
	 * pre: All items in the array are in heap order.
	 * post: The array is sorted.
	 * @param table, the array to be sorted.
	 *///updated by Yunan
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> void shrinkHeap(T[] table,@SuppressWarnings("rawtypes") Comparator comp)
	{
		int n = table.length;
		//Check if the heap is a minHeap or maxHeap
		boolean minHeap = false;
		if(comp.compare(table[0],table[n-1]) < 0){
			minHeap = true;
		}
		while(n > 0)
		{
			
			n--;
			
			swap(table, 0, n);
			int parent = 0;
			
			while(true)
			{
				int leftChild = 2 * parent + 1;
				
				if(leftChild >= n)
				{
					break; //No more children.
				}
				
				int rightChild = leftChild + 1;
				int maxChild = leftChild;
				
				int compareLeftRight = comp.compare(table[leftChild],table[rightChild]);			
				if(minHeap){
					compareLeftRight = 0 - compareLeftRight;
					
				}
				
				if(rightChild < n && compareLeftRight < 0)
				{
					maxChild = rightChild;
				}
				
				int compareParentChild = comp.compare(table[parent],table[maxChild]);
				if(minHeap){
					compareParentChild = 0 - compareParentChild;
				}		
				if(compareParentChild < 0)
				{
					swap(table, parent, maxChild);
					parent = maxChild;
				}
				else //Heap property is restored.
				{
					break; //Exit the loop.
				}
			}
		}
	}
	/**
	 * Swap the items in table[i] and table[j]
	 * @param table, the array that contains the items.
	 * @param i, the index of one item.
	 * @param j, the index of the other item.
	 */
	private static <T extends Comparable<T>> void swap(T[] table, int i, int j)
	{
		T temp = table[i];
		table[i] = table[j];
		table[j] = temp;
	}
}
