import java.awt.font.GraphicAttribute;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class MaxFlow {

	static HashMap<String, LinkedList<Edge>> graph;

	public static void main(String[] args) throws Exception {
		graph = new HashMap<>();
		parse();
		System.out.println("suck");
	}

	private static Edge[] bfs(Edge[] g, String source, String sinc) {
		
		if (source.equals(sinc)) {
			return g;
		} else {
			for (int i = 0; i < g.length; i++) {
				for(String s : graph.keySet()){
					LinkedList<Edge> list = graph.get(s);
					
				}
			}
		}

		//
		// def find_path(self, source, sink, path):
		// if source == sink:
		// return path
		// for edge in self.get_edges(source):
		// residual = edge.capacity - self.flow[edge]
		// if residual > 0 and edge not in path:
		// result = self.find_path( edge.sink, sink, path + [edge])
		// if result != None:
		// return result

		return -1;
	}

	public static void parse() throws Exception {
		FileReader fr = new FileReader("rail.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (br.ready() && !line.equals("DESTINATIONS")) {
			line = br.readLine();
		}
		line = br.readLine();
		line = br.readLine();

		while (br.ready() && !line.equals("EOF")) {
			String[] split = line.split(" ");
			System.out.println(split[0]);
			LinkedList<Edge> list = new LinkedList<>();
			Edge temp = new Edge(split[0], split[1], Integer.parseInt(split[2]));
			list.add(temp);
			if (!graph.containsKey(split[0])) {
				graph.put(split[0], list);
			} else {
				LinkedList<Edge> t = graph.get(split[0]);
				t.add(temp);
			}
			line = br.readLine();
		}

		System.out.println(graph);
	}

}

class Edge implements Comparable<Edge> {
	String nodeA;
	String nodeB;
	public int weight;

	public Edge(String nodeA, String nodeB, int weight) {
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		if (this.weight > edge.weight) {
			return 1;
		} else if (this.weight < edge.weight) {
			return -1;
		} else {
			return 0;
		}

	}

	public String toString() {
		return nodeA + " -> " + nodeB + " " + weight;
	}
}
