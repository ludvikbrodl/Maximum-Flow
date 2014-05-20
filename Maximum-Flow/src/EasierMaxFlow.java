import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class EasierMaxFlow {
	private static int[][] graph;
	private static int NUMBER_OF_NODES;
	private static int[] parent;
	private static Queue<Integer> queue;
	private static boolean[] visited;
	private static int[][] residualGraph;

	public static void main(String[] args) throws Exception {
		parse();
		parent = new int[NUMBER_OF_NODES];
		visited = new boolean[NUMBER_OF_NODES];
		queue = new LinkedList<Integer>();
		System.out.println(fordFulkerson(graph, 0, 54));
		System.out.println(bfsNew(0, 54));
	}

	public static int fordFulkerson(int graph[][], int source, int destination) {
		int u, v;
		int maxFlow = 0;
		int pathFlow;

		residualGraph = new int[NUMBER_OF_NODES][NUMBER_OF_NODES];
		for (int sourceVertex = 0; sourceVertex < NUMBER_OF_NODES; sourceVertex++) {
			for (int destinationVertex = 0; destinationVertex < NUMBER_OF_NODES; destinationVertex++) {
				residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];
			}
		}

		while (bfs(source, destination, residualGraph)) {
			pathFlow = Integer.MAX_VALUE;
			for (v = destination; v != source; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}
			for (v = destination; v != source; v = parent[v]) {
				u = parent[v];
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow;
			}
			maxFlow += pathFlow;
		}

		for (int i = 0; i < residualGraph[0].length; i++) {
			for (int j = 0; j < residualGraph.length; j++) {
				 if(residualGraph[i][j] == 0 && residualGraph[j][i] != 0){
//				 System.out.println(residualGraph[j][i]);
				System.out.println((i) + " " + (j) + " : "
						+ residualGraph[j][i]);
				 
				 }
			}
//			System.out.println();
		}
		return maxFlow;
	}

	public static boolean bfs(int source, int goal, int graph[][]) {
		boolean pathFound = false;
		int element;

		for (int vertex = 0; vertex < NUMBER_OF_NODES; vertex++) {
			parent[vertex] = -1;
			visited[vertex] = false;
		}

		queue.add(source);
		parent[source] = -1;
		visited[source] = true;

		while (!queue.isEmpty()) {
			element = queue.remove();
			for (int index = 0; index < NUMBER_OF_NODES; index++) {
				if (graph[element][index] > 0 && !visited[index]) {
					parent[index] = element;
					queue.add(index);
					visited[index] = true;

				}
			}
		}
		if (visited[goal]) {
			pathFound = true;
		}
		return pathFound;
	}

	
	public static boolean bfsNew(int source, int goal) {
		boolean pathFound = false;
		int element;

		for (int vertex = 0; vertex < NUMBER_OF_NODES; vertex++) {
			parent[vertex] = -1;
			visited[vertex] = false;
		}

		queue.add(source);
		parent[source] = -1;
		visited[source] = true;

		while (!queue.isEmpty()) {
			element = queue.remove();
			for (int index = 0; index < NUMBER_OF_NODES; index++) {
				if (residualGraph[element][index] > 0 && !visited[index]) {
					parent[index] = element;
					queue.add(index);
					visited[index] = true;

				}
			}
		}
		if (visited[goal]) {
			pathFound = true;
		}
		return pathFound;
	}
	public static void parse() throws Exception {
		FileReader fr = new FileReader("rail.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		NUMBER_OF_NODES = Integer.parseInt(line);
		while (br.ready() && !line.equals("DESTINATIONS")) {
			line = br.readLine();
		}
		graph = new int[NUMBER_OF_NODES][NUMBER_OF_NODES];
		line = br.readLine();
		line = br.readLine();
		while (br.ready() && !line.equals("EOF")) {
			String[] split = line.split(" ");
			int column = Integer.parseInt(split[0]);
			int row = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);
			if (weight == -1) {
				weight = 1000;
			}
			graph[column][row] = weight;
			graph[row][column] = weight;
			line = br.readLine();
		}

		// for (int i = 0; i < graph[0].length; i++) {
		// for (int j = 0; j < graph.length; j++) {
		// System.out.print(graph[i][j] + " ");
		// }
		// System.out.println();
		// }
	}

}
