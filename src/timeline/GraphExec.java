package timeline;

import java.util.Scanner;

public class GraphExec {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\user\\Downloads\\automatedFeeds-main\\automatedFeeds-main\\inputAnalysis.tsv";
        
        Scanner myScanner = new Scanner(System.in);
        System.out.print("1. Abort Operation\n2. View Feed Only\n3. Add Feed\nEnter mode: ");
        int mode = myScanner.nextInt();
        myScanner.nextLine();
        
        if(mode == 1) {
        	myScanner.close();
        	System.out.println("\nOperation aborted");
        }
        else if(mode == 2) {
        	myScanner.close();
        	System.out.println();
            GraphBuilder graphBuilder = new GraphBuilder();
            graphBuilder.loadGraphFromFile(fileName);
            graphBuilder.addEdges();
            graphBuilder.findMST();
        }
        else if(mode == 3) {
            System.out.print("Input your tweet: ");
            String newText = myScanner.nextLine();
            myScanner.close();
            System.out.println();
            System.out.println(newText);
            // Proceed with graph operations
            GraphBuilder graphBuilder = new GraphBuilder();
            graphBuilder.loadGraphFromFile(fileName);
            graphBuilder.addEdges();
            graphBuilder.findMST();
            
            
            FileAppender.appendToFile(fileName, newText);
        }
        else {
            System.out.println("\nInvalid input, operation aborted");
        }
    }
}
