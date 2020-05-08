package sortingAlgorithms;

import algorithmVisualizer.SortingArray;

/**
 * @author Brett Heithold
 *
 */
public class BubbleSort {
	private SortingArray array;
	
	public BubbleSort(SortingArray array) {
		this.array = array;
	}
	
	public void Run() {
		boolean isSorted = false;
		int lastUnsortedIndex = array.length() - 1;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < lastUnsortedIndex; i++) {
				if (array.getValue(i) > array.getValue(i + 1)) {
					swap(i, i + 1);
					isSorted = false;
				}
			}
			lastUnsortedIndex--;
		}
	}
	
	private void swap(int i, int j) {
		int temp = array.getValue(i);
		array.setValue(i, array.getValue(j));
		array.setValue(j, temp);
	}
	
}
