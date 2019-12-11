package com.sequence;

public class InsertSort {
	
	public static int[] sort(int[] array){
		int j;
		for(int i = 1;i < array.length ;i++){
			int temp = array[i];
			j = i;
			while(j > 0 && temp < array[j-1]){
				array[j] = array[j-1];
				j--;
			}
			array[j] = temp;
		}
		return array;
	}
	public static void main(String[] args) {
		int[] arr = new int[]{4,2,8,9,5,7,6,1,3};
		InsertSort.sort(arr);
		
	}
}
