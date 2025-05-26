package timeline;
import java.util.concurrent.ThreadLocalRandom; 

public class nodes {
	int position;
	String content;
    int likes;

    nodes(int position, String content) {
    	this.position = position;
        this.content = content;
        this.likes = getRandomValue(1, 1000);
    }

    @Override
    public String toString() {
        return content + " (" + likes + " likes)";
    }
    
    public static int getRandomValue(int Min, int Max) 
    { 
  
        // Get and return the random integer 
        // within Min and Max 
        return ThreadLocalRandom 
            .current() 
            .nextInt(Min, Max + 1); 
    } 
}
