package hw_0807;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질_bj1697 {

	static class Node {
		int time, num;

		public Node(int time, int num) {
			super();
			this.time = time;
			this.num = num;
		}

	}

	static Node result;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int k = sc.nextInt();

		// 배열 k+1로 만들고 visit도 같은크기로
		int arr[] = new int[k + 1];
		boolean visit[] = new boolean[100001];

		// 걸으면 x-1 x+1로 이동
		// 숨바꼭질하면 *2

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, N)); // 수빈이의 위치를 넣음
		visit[N] = true; // 큐에 들어간건 방문했으므로 true

		int work1, work2, jump;

		while (!queue.isEmpty()) {

			result = queue.poll();
			if (result.num == k)
				break;

			// 큐에서 뺀 결과가 저 N 조건 초과하면 범위 벗어남. 그건 큐에 넣으면 안돼. 이미 방문한거 큐에 넣으면 안돼.
			// 큐에서 빼서 -1 +1 *2 각각 연산해서 큐에 넣어

			work1 = result.num + 1;
			work2 = result.num - 1;
			jump = result.num * 2;

			if ((work1 >= 0 && work1 <= 100000) && !visit[work1]) {
				visit[work1] = true;
				queue.add(new Node(result.time + 1, work1));
			}

			if ((work2 >= 0 && work2 <= 100000) && !visit[work2]) {
				visit[work2] = true;
				queue.add(new Node(result.time + 1, work2));
			}

			if ((jump >= 0 && jump <= 100000) && !visit[jump]) {
				visit[jump] = true;
				queue.add(new Node(result.time + 1, jump));
			}

		}

		System.out.println(result.time);
	}

}
