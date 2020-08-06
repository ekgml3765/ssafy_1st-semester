import java.util.Arrays;
import java.util.Scanner;

public class 최소스패닝트리 {

	static int V, E;
	static int parents[];

	static int find(int x) {
		// 내가 대표자라면
		if (x == parents[x])
			return x;

		return parents[x] = find(parents[x]); //내 부모값을 부모의 부모를 찾아 바꿔버려

	}

	static boolean union(int x, int y) {
		int px = find(x); // x의 대표자 찾음
		int py = find(y); // y의 대표자를 찾음

		if (px != py) {
			parents[px] = py;
			return true;
		}
		return false;
	}

	static class kru implements Comparable<kru> {
		int a, b, c;

		public kru(int a, int b, int c) {
			this.a = a; // 정점
			this.b = b; // 정점
			this.c = c;
		}

		@Override
		public int compareTo(kru o) {
			return this.c - o.c;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			V = sc.nextInt(); // 정점의 수
			E = sc.nextInt(); // 간선의 수
			parents = new int[V + 1];
			
			// 자기자신의 부모를 가짐 makeset
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			kru[] arr = new kru[E];
			for (int i = 0; i < E; i++) {
				int a = sc.nextInt(); // 정점
				int b = sc.nextInt(); // 정점
				int c = sc.nextInt(); // 가중치
				arr[i] = new kru(a, b, c);
			}

			// 정렬
			Arrays.sort(arr);
			long result = 0; // 가중치의 합 결과
			
			// 간선을 연결 = 유니온을해준다는것. 싸이클이 생기지 않게. 싸이클이 생긴다 ? 부모가 같다. 
			for (int i = 0; i < E; i++) {
				if (find(arr[i].a) != find(arr[i].b)) {
					union(arr[i].a, arr[i].b);
					result += arr[i].c;
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

}