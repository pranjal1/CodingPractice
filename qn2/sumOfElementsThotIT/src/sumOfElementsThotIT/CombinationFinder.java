package sumOfElementsThotIT;

import java.util.ArrayList;
import java.util.List;


public class CombinationFinder {
	private static int originalTotal;
	
	public static void findMatch(int[] list, int total, int numToUse) {
		originalTotal = total;
		ArrayList<CustomObject> myList = new ArrayList<CustomObject>();
		int idx = 1;
		for (int i: list) {
			myList.add(
					new CustomObject(i, idx)
					);
			idx++;
		}
        List<CustomObject> currentCombination = new ArrayList<>();
        findCombinations(myList, total, numToUse, 0, currentCombination);
	}
	
	public static void findCombinationsWithTotal(List<CustomObject> list, int total, int numToUse) {
		originalTotal = total;
        List<CustomObject> currentCombination = new ArrayList<>();
        findCombinations(list, total, numToUse, 0, currentCombination);
    }

    private static void findCombinations(List<CustomObject> list, int total, int numToUse, int startIndex, List<CustomObject> currentCombination) {
    	if (numToUse == 0 && total == 0) {
    		System.out.println(currentCombination);
            System.out.print("Element " + currentCombination.get(0).getIdx());
            for (int i = 1; i < currentCombination.size(); i++) {
                System.out.print(" + Element " + currentCombination.get(i).getIdx());
            }
            System.out.println(" = " + originalTotal);
            return;
        }

        for (int i = startIndex; i < list.size(); i++) {
            CustomObject value = list.get(i);
            if (value.getValue() <= total) {
                currentCombination.add(value);
                findCombinations(list, total - value.getValue(), numToUse - 1, i + 1, currentCombination);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        
        int[] myList = new int[] {1,5,6,3,6,7};

        int myTotal = 13;
        int myNumToUse = 2;

        findMatch(myList, myTotal, myNumToUse);
    }
}




