package hw_0806;

import java.util.Arrays;
import java.util.Scanner;

//그리디 알고리즘
public class 회의실배정 {

	static class comp implements Comparable<comp> {
		int start, end;

		public comp(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(comp o) {
			// TODO Auto-generated method stub
			if(this.end == o.end) //끝 시간이 같다면
				return Integer.compare(this.start, o.start); //시작시간으로 정렬.
			return Integer.compare(this.end, o.end);
		}

	}

	static comp[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		arr = new comp[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (new comp(sc.nextInt(), sc.nextInt()));
		}

		// 끝나는 시간대로 정렬
		Arrays.sort(arr);

		// 끝나는시간과
		int count = 1;
		int tmp = arr[0].end; // 픽스
		for (int i = 1; i < N; i++) {
			if (tmp <= arr[i].start) {
				count++;
				tmp = arr[i].end;
			}
		}
		System.out.println(count);
	}

}
