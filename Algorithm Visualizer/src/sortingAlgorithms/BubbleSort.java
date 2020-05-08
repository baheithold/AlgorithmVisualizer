package sortingAlgorithms;

/**
 * @author Brett Heithold
 *
 */
public class BubbleSort {
	private int[] array;
	
	public BubbleSort(int[] array) {
		this.array = array;
	}
	
	public void Run() {
		boolean isSorted = false;
		int lastUnsortedIndex = array.length - 1;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < lastUnsortedIndex; i++) {
				if (array[i] > array[i + 1]) {
					swap(i, i + 1);
					isSorted = false;
				}
			}
			lastUnsortedIndex--;
		}
	}
	
	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
}
