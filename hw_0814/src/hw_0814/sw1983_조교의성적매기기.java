package hw_0814;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class sw1983_조교의성적매기기 {

	static class student implements Comparable<student> {

		int num;
		double score;
		

		@Override

		public int compareTo(student o) {
			// TODO Auto-generated method stub
			if (this.score < o.score)
				return -1;
			else if (this.score > o.score)
				return 1;
			else
				return 0;

		}

		public student(int num, double score) {
			super();
			this.num = num;
			this.score = score;
		}

	}

	// 1~10으로 보기위해 맨 앞에 아무값 넣음
	static String[] st = { "", "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			int N = sc.nextInt(); // N명
			int person = sc.nextInt(); // N명중 학점을 알고싶은 K번째 학생
			student[] arr = new student[N];

			for (int i = 0; i < N; i++) {
				// 중간+기말+과제 점수 입력
				int mid = sc.nextInt(); // 중간
				int fin = sc.nextInt(); // 기말
				int hw = sc.nextInt(); // 과제
				arr[i] = new student(i + 1, (double) (0.35 * mid + 0.45 * fin + 0.2 * hw));
			}

			// 내림차순 정렬
			Arrays.sort(arr, Collections.reverseOrder());

			int number = 0; // k번째 학생의 순위
			for (int i = 0; i < arr.length; i++) {
				if (person == arr[i].num) // k와 같은 값을 찾아
					number = i + 1; // 배열 인덱스가 0부터니까 +1번 더한 순위임
			}

			int idx = 0;
			for (int i = 1; i < st.length; i++) {
				if (number <= i * (N / 10)) {
					idx = i;
					break;
				}
			}

			System.out.println("#" + tc + " " + st[idx]);
		}

	}
}
