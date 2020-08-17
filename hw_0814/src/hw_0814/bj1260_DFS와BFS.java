package hw_0814;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj1260_DFS와BFS {

	static ArrayList<Integer>[] list; //인접리스트
    static int N;
    static boolean[] visit; 
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //정점의 수
        int M = sc.nextInt(); //간선수
        int start = sc.nextInt(); //시작점
        
 
        list = new ArrayList[N + 1]; //1부터 시작하므로 1크게
 
        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<Integer>();
        }
 
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
 
            list[from].add(to);
            list[to].add(from);
        }
        
        //정렬
        for (int i = 1; i < N + 1; i++) {
            Collections.sort(list[i]);
        }
             
        visit = new boolean[N + 1];
        dfs(start);
        System.out.println();
        
        visit = new boolean[N + 1];
        bfs(start);
        System.out.println();
 
        sc.close();
    }
 
    private static void dfs(int x) {
        if (visit[x]) { //기저조건 방문한 곳은 리턴
            return;
        }
 
        visit[x] = true;
        System.out.print(x + " ");
        for (int y : list[x]) {
            if (!visit[y])
                dfs(y);
        }
 
    }
 
    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start); //큐에 넣고
        visit[start] = true; //방문체크
 
        while (!queue.isEmpty()) {
            int x = queue.poll();
            System.out.print(x + " ");
            for (int y : list[x]) {
                if (!visit[y]) {
                    visit[y] = true; //큐에 넣기 전에 방문체크
                    queue.add(y); //그다음 큐에 넣어
                }
            }
        }
    }

}
