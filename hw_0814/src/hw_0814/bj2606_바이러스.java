package hw_0814;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj2606_바이러스 {

	static int N, M;
	static int cnt = -1;
	static int arr[][];
	static boolean visit[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner (System.in);
		N = sc.nextInt(); //정점수
		M = sc.nextInt(); //간선수
		arr = new int[N+1][N+1];
		
		for(int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			arr[from][to] = arr[to][from] = 1;
		}
		
		visit = new boolean [N+1]; //정점 수 만큼
		bfs();
		System.out.println(cnt);
	
		sc.close();
	}
	
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int cur = 1;
		queue.add(cur);
		visit[cur] = true;
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			cnt++; //꺼낼때 카운트 증가
			for(int i = 1; i < N+1; i++) {
				if( arr[cur][i] == 1 && !visit[i]) {
					visit[i] = true;
					queue.add(i);
				}
			}
		}
		
	}

}
