package hw_0805;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 단지번호붙이기 {

static int dr[] = { -1, 1, 0, 0 };
static int dc[] = { 0, 0, -1, 1 };

static class Node {
    int r, c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public static void main(String[] args) {
    // TODO Auto-generated method stub

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    // Queue<Node> queue = new LinkedList<>();

    int arr[][] = new int[N][N];
    for (int i = 0; i < N; i++) {
        String str = sc.next();
        for (int j = 0; j < N; j++)
            arr[i][j] = str.charAt(j) - '0';
    }

    int cnt = 0; // 단지의 개수
    int num = 0; // 단지 안에 개수

    ArrayList<Integer> a = new ArrayList<>();
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {

            if (arr[i][j] == 1) {
                cnt++;
                Queue<Node> queue = new LinkedList<>();
                queue.add(new Node(i, j)); // 1발견하면 큐에 넣어주고
                arr[i][j] = 0; // 0으로 바꿔줘  *** 큐에 들어가기 전에 0으로 체크해주고 들어가야해.

                while (!queue.isEmpty()) {
                    
                    Node n = queue.poll();
                    num += 1;

                    // 4방탐색
                    for (int d = 0; d < 4; d++) {
                        int nr = n.r + dr[d];
                        int nc = n.c + dc[d];
                        // 범위 밖은 패스
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                            continue;
                        // 0은 패스
                        if (arr[nr][nc] == 0)
                            continue;
                        
                        // 1을만나면
                        arr[nr][nc] = 0; // 0으로 바꿔줘
                        queue.add(new Node(nr, nc));
                    
                    }
                }
                a.add(num);
                num = 0;
            }

        }
    }

    a.sort(null);
    System.out.println(cnt);
    for(int x: a)
        System.out.println(x);
}
}