import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 수업
public class 크루스칼 {

	static int V, E;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight); // 기본 오른차순의 결과
		}

	}

	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		parents = new int [V];

		int from, to, weight;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);

		}

		Arrays.sort(edgeList);
		make();
		int cnt = 0, result = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) // 싸이클이 형성되지 않았다면
			{
				result += edge.weight;
				if (++cnt == V - 1)
					break;
			}
		}

		System.out.println(result);
	}

	static void make() {
		for (int i = 0; i < V; i++)
			parents[i] = i;
	}

	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]); // 내 부모를 뽑아서 내 부모의 부모를 찾아 자신의 부모로 바로 넣어버려
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}
}
