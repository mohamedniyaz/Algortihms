import java.io.IOException;
import java.util.*;

public class simplePathAlgorithm {
	
	public static int N ;
	static int pathCount = 0;
	
	public static ArrayList getSibling(vertex node){
		
		ArrayList aL = new ArrayList();
		
		//up
		if(node.X >= 1)
		{
			vertex vxUp =  new vertex(node.X - 1, node.Y);
			aL.add(vxUp);
		}
		
		//down
        if(node.X < N - 1) 
        {
        	vertex vxDown =  new vertex(node.X + 1, node.Y);
            aL.add(vxDown);
        }
        //left
        if(node.Y >= 1) 
        {
        	vertex vxLeft =  new vertex(node.X, node.Y - 1);
            aL.add(vxLeft);
                
        }
        //right
        if(node.Y < N - 1) 
        {
        	vertex vxRight =  new vertex(node.X, node.Y + 1);
            aL.add(vxRight);
                                
        }
        
        return aL;		
		
	}
	
	//Method to Determine all the paths from start(stX,stY) vertex to destination (dsX,dsY) 
	//pathFinderMap tells if (x,y) has been visited or not
	public static void determinePaths(int stX, int stY, int dsX, int dsY, ArrayList<vertex> path, boolean [][]pathFinderMap) throws IOException
	
	{
	        
	        //bounds check
	        if((stX < 0 || stY < 0 || dsX < 0 || dsY < 0 ) || (stX >= N || stY >= N || dsX >= N || dsY >= N )) 
	        {
	            System.out.println("Out of Array");    
	        	
	        }

	        //add current node to the path and mark it as visited
	        vertex curr = path.get(path.size()-1);
	        pathFinderMap[curr.X][curr.Y] = true;
	        ArrayList<vertex> neighbors = getSibling(curr);
	        
	        //check path from every neighbor to the destination
	        for(int i = 0; i < neighbors.size(); i ++) 
	        {
	                //if neighbor is the destination then print path
	                if (neighbors.get(i).X  == dsX && neighbors.get(i).Y == dsY) 
	                {
	                        path.add((neighbors.get(i)));
	                        pathCount++;
	                        //print(path,pathCnt);
	                        path.remove(path.size()-1);
	                        continue;
	                }
	                
	                //if unvisited neighbor
	                if(pathFinderMap[neighbors.get(i).X][neighbors.get(i).Y] == false) 
	                {
	                        //mark as visited
	                        pathFinderMap[neighbors.get(i).X][neighbors.get(i).Y] = true;
	                        
	                        //add to path
	                        path.add(neighbors.get(i));
	                        
	                        //find all paths from this neighbor to destination
	                        determinePaths(neighbors.get(i).X, neighbors.get(i).Y, dsX, dsY, path, pathFinderMap);
	                        
	                        //remove neighbor from path and mark as unvisited
	                        pathFinderMap[neighbors.get(i).X][neighbors.get(i).Y] = false;
	                        path.remove(path.size()-1);
	                }
	        	}
	}
	
	//Method to print path
	public static void print(ArrayList<vertex> path,int pathCnt) {
		System.out.println();    
		System.out.print("Path -"+pathCount); 
		for(int i = 0; i < path.size(); ++i){
			
			System.out.print(" ("+path.get(i).X +""+ path.get(i).Y+")" );
			System.out.print(" -- " );
		}
		
	}
	
	//Main Method
	public static void main(String[] args) throws IOException {
		double start ;
        ArrayList<vertex> path = new ArrayList();
        
        System.out.println("Enter the value of N :");        
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();        
        
        vertex startVertex;
        vertex vx = new vertex(0,0);        
        path.add(vx);
        boolean pathFinderMap[][] = new boolean[N][N];
        
        /*for(int i=0; i < N; i++) {
                for(int j=0; j < N; j++) {
                        pathFinderMap[i][j] = false;
                }
        }*/
        
            start = System.nanoTime();
        	determinePaths(vx.X, vx.Y, N-1, N-1, path, pathFinderMap);
			System.out.println("The total number of paths : "+pathCount);
			System.out.println("The total time : "+((System.nanoTime() - start)/1000000000)+" seconds");
            
	 }

}

// X Y vertices Class
class vertex {
	
    public int X;
    public int Y;
    
    public vertex(int x, int y){
    	
		this.X = x;
        this.Y = y;
    }

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
    
}