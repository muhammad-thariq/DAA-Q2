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
