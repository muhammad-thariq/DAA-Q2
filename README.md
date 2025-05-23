# Automated Feeds
## Shortly introduce the problem and how graph theory is implemented
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
When you run the program, it will ask you to input some several options, whether to terminate the process, view the feeds, or tweet something.

![image](https://github.com/user-attachments/assets/fcee81a7-33dd-4ff9-bcba-d0c531c99042)

If the user inputs the number 1, the system will output "Operation aborted," indicating that the process has been terminated.

![image](https://github.com/user-attachments/assets/df239406-a3cc-49c4-a923-3c108f5d7f4b)


## Shortly summarize result and its analysis


