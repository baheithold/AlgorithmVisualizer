package sortingAlgorithms;

import gui.SortingArrayPanel;

/**
 * @author Brett Heithold
 *
 */
public class BubbleSort {
	private SortingArrayPanel array;
	
	public BubbleSort(SortingArrayPanel array) {
		this.array = array;
	}
	
	public void Run() {
		boolean isSorted = false;
		int lastUnsortedIndex = array.length() - 1;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < lastUnsortedIndex; i++) {
				if (array.getValue(i) > array.getValue(i + 1)) {
					array.swap(i, i + 1);
					isSorted = false;
				}
			}
			lastUnsortedIndex--;
		}
	}
	
}
