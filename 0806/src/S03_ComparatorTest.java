

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author taeheekim
 *
 */
public class S03_ComparatorTest {
	
   //제레릭에는 해당배열의 원소 타입
   static class MyComparator implements Comparator<int[]>{

		@Override
		public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
//            return o1[0] - o2[0]; 두번쨰로 할건지 첫번쨰로 할건지
		}
	   
   }
   
   public static void main(String[] args) {
      
      int[][] arr = new int[][]{{1,10},{3,50},{2,80},{4,10}};   // {} 이거 하나를 student로 표현
      System.out.println("=========정렬 전=============");
      print(arr);
      Arrays.sort(arr, new MyComparator()); //컴퍼레이터를 만들어 줄 수 밖에 없다. 따로 
      System.out.println("=========정렬 후=============");
      print(arr);
   }
   
	private static void print(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
	}
}