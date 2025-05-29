package timeline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class GraphBuilder {
    private Map<Integer, nodes> graph;
    private Map<Integer, Set<Integer>> edges;
    private Map<Pairs<Integer, Integer>, Double> weights;

    public GraphBuilder() {
        this.graph = new HashMap<>();
        this.edges = new HashMap<>();
        this.weights = new HashMap<>();
    }

    // Method to load nodes from a .tsv file
    public void loadGraphFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    int position = Integer.parseInt(parts[0]);
                    String content = parts[1];

                    nodes node = new nodes(position, content);
                    graph.put(position, node);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in the file: " + e.getMessage());
        }
    }

    public void addEdges() {
        Random random = new Random();
        int nodeCount = graph.size();

        for (int i = 0; i < nodeCount; i++) {
            if (!graph.containsKey(i)) {
                continue;
            }

            Set<Integer> neighbors = edges.computeIfAbsent(i, k -> new HashSet<>());

            while (neighbors.size() < 2) {
                int j = random.nextInt(nodeCount);
                if (j != i && graph.containsKey(j) && !neighbors.contains(j)) {
                    neighbors.add(j);
                    edges.computeIfAbsent(j, k -> new HashSet<>()).add(i);

                    double weight = calculateEdgeWeight(graph.get(i), graph.get(j));
                    weights.put(new Pairs<>(i, j), weight);
                    weights.put(new Pairs<>(j, i), weight);
                }
            }
        }
    }

    private double calculateEdgeWeight(nodes node1, nodes node2) {
        double similarity = calculateStringSimilarity(node1.content, node2.content);
        return 2*(1 - similarity) + (1-(0.1 + ((node2.likes - 1) * (1 - 0.1) / (1000 - 1)))); 
    }

    private double calculateStringSimilarity(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) set1.add(c);
        for (char c : s2.toCharArray()) set2.add(c);

        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }

    // Kruskal's Algorithm
    public void findMST() {
        // Sort edges by weight
        List<Map.Entry<Pairs<Integer, Integer>, Double>> sortedEdges = new ArrayList<>(weights.entrySet());
        sortedEdges.sort(Comparator.comparingDouble(Map.Entry::getValue));

        // Union-Find initialization
        UnionFind uf = new UnionFind(graph.size());

        List<Pairs<Integer, Integer>> mst = new ArrayList<>();

        // Set to track nodes that have been printed already
        Set<Integer> printedNodes = new HashSet<>();

        // Build MST
        for (Map.Entry<Pairs<Integer, Integer>, Double> edge : sortedEdges) {
            int u = edge.getKey().first;
            int v = edge.getKey().second;

            if (uf.union(u, v)) { // If u and v are not already connected
                mst.add(edge.getKey());
            }

            // Stop when MST is complete
            if (mst.size() == graph.size() - 1) break;
        }

        // Print MST (avoid duplicate node printing)
        for (Pairs<Integer, Integer> edge : mst) {
            nodes node1 = graph.get(edge.first);
            nodes node2 = graph.get(edge.second);

            // Print node1 if not printed yet
            if (!printedNodes.contains(edge.first)) {
                System.out.println(node1.content);
                printedNodes.add(edge.first);
            }

            // Print node2 if not printed yet
            if (!printedNodes.contains(edge.second)) {
                System.out.println(node2.content);
                printedNodes.add(edge.second);
            }
        }
    }
}


