import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class DP {
	
	public static void main(String[] args) {
		
		
		
		FileInputStream fstream;
		
		try {
			
			String inputFile = "data3.txt";
			fstream = new FileInputStream(inputFile);
	
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

		
			String s1 = br.readLine(); // read first line as row or X
			
			String s2 = br.readLine(); //  read second line as column or Y
			
			Compute_Edit_Distance ned = new Compute_Edit_Distance();
			Compute_LCS clcs = new Compute_LCS();
	    	Compute_LCS_Linear_Memory clcsLM = new Compute_LCS_Linear_Memory();
	    	
	    	//Compute Normalized Edit Distance
	    	ned.calculateNormalizedEditDistance(s1,s2);
	    	
	    	// Compute LCS 
	    	clcs.calculateLCS(s1,s2);
	    	
	    	System.out.print("\n");
	    	List LCSop = new LinkedList();
	    	//Compute LCS using Linear memory
	    	LCSop = clcsLM.lcs_recursive(s1, s2, LCSop);
	    	System.out.print("Compute_LCS_Linear_Memory: ");
	    	
	    	while (!LCSop.isEmpty()) {
                System.out.print(LCSop.remove(0));
                System.out.print(" ");
            }
	    	
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

}
