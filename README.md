# Automated Feeds
## Shortly introduce the problem and how Kruskal algorithm is implemented
In the evolving landscape of social media, user engagement is crucial to platform success. This proposal explores the application of graph-based algorithms to optimize social media timelines, with the goal of enhancing user satisfaction by presenting relevant, tailored content. By displaying posts that share thematic or content-based similarities, users will encounter a more curated experience, likely improving their comfort and engagement with the platform. We're implementing the graph usage for this project. In this project, we're implementing Kruskal's method.


![image](https://github.com/user-attachments/assets/cf74caf8-3a81-40b8-8790-3de8011dc8b9)


1. Start
The process begins here.
2. User Input
First, we need to fill in the input. The input datatype would be string.
3. Add User Input into Database
The input provided by the user will be stored in a database for future processing.
4. Initialize Node and Graph Structure
After we determine the input, A graph structure will be initialized, creating nodes based on the user's input.
5. Create Randomized Edges on Graph
Next, it will creates edges between the graph nodes to establish connections between the nodes.
6. Calculate Costs
Then it will determines the cost of the edges.
7. Check if All Edges Have Cost
After that, the system verifies if all edges in the graph have assigned costs:
If NO: The flow returns to the calculateCost() function to ensure all edges are calculated.
If YES: The process continues to the next step.
8. Apply Kruskal's Algorithm
A kruskal() function is used to compute the Minimum Spanning Tree (MST), optimizing the graph structure by selecting the minimum cost edges while maintaining connectivity.
9. Display Path
The optimized path obtained from Kruskal’s algorithm is displayed, showing the specific connections chosen in the MST.
10. Display Timeline
Finally, the system displays the user’s optimized timeline, which represents the sequence of events or feeds based on the selected path.
## Instruction how to run, and sample input and output. 

Open the project (this repo) in a Java-compatible IDE.

Open graphExec Class, and run it.

When you run the program, it will ask you to input some several options, whether to terminate the process, view the feeds, or tweet something.

![image](https://github.com/user-attachments/assets/fcee81a7-33dd-4ff9-bcba-d0c531c99042)

If the user inputs the number 1, the system will output "Operation aborted," indicating that the process has been terminated.

![image](https://github.com/user-attachments/assets/df239406-a3cc-49c4-a923-3c108f5d7f4b)

If 3 is chosen, you will be prompted to enter a String, which will then be added to the database. It will then be shown as the first feed in the timeline.

![image](https://github.com/user-attachments/assets/5ae7cc45-2afc-46e5-bd5b-adac8d90bdd1)

If 2 is chosen, it will simply output the timeline, much like option 3 but without entering a new feed.

## Shortly summarize result and its analysis

### Summary Result :

![WhatsApp Image 2024-12-09 at 17 21 50_1ec9cdc1](https://github.com/user-attachments/assets/f9ef2b11-4834-4171-b9a0-f8eb2b202d16)

### Analysis:

First run

![image](https://github.com/user-attachments/assets/600cd3b5-ca34-4773-a777-d726722b9441)

Second run

![image](https://github.com/user-attachments/assets/e58a5e24-b46b-4215-ab54-4405e2860eb7)

Third run 

![image](https://github.com/user-attachments/assets/35b0f3ad-3e1c-45c5-8ef6-e9da8f0819ff)

### Explanantion :

This algorithm computes the MST for all the Nodes in the graph and ensuring there are no cycle. each connection betweeen nodes (shown as Node X <-> Node Y) represents an edge in the graph, with a weight associated with it. For example :

`Node 4 (Ken says hi) <-> Node 3 (Java says hi) [Weight: 1.02]`

Node 4 ("Ken says hi") is connected to Node 3 ("Java says hi") with a weight of 1.02.

The weight is calculated based on the string similarity between the contents of the two nodes as well as some other factors node "likes" 

`weight = 2 * (1 - similarity) + (1 - (0.1 + ((node2.likes - 1) * (1 - 0.1) / (1000 - 1))))`

After all the edges are displayed, the total MST cost is shown at the end of each run. This is the sum of all the weights of the edges in the Minimum Spanning Tree. 

The total MST cost in the first run is 17.02, in the second run it's 16.97, and in the third run it's 18.59. The Minimum Spanning Tree (MST) changes between runs despite not adding any feeds is caused by the edges are added in  `addEdges()` functions

```
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
```
