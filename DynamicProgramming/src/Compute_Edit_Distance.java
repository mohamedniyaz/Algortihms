
public class Compute_Edit_Distance {
	
	
	public void calculateNormalizedEditDistance(String s1, String s2) {
		
		 int row = s1.length()+1;
		 int column = s2.length()+1; 
		 int[][] T = new int[2][column]; //we can compute the Normalized edit distance by storing only two rows of the table.
		
		 T[1][0] = 1; //T[i][j] = i if j = 0 , since we have only two rows T[1][0] will be equal to i
		 for (int j = 0; j < column; j++) {
	            T[0][j] = j; //T[i][j] = j if i = 0 
	        }
		
			for(int i=1;i<row;i++){  // below code - i is taken as 1 since we have only two rows 
				
					 for (int j = 1; j < column; j++) {   // T[i][j]  if i; j > 0
				
							if (s1.charAt(T[1][0] - 1) == s2.charAt(j - 1)){
								T[1][j] = T[0][j - 1];  //T[i - 1][j - 1] when X[i] = Y [j]
							}else{
								T[1][j] = Math.min(T[0][j],T[1][j-1] )+1; // min(T[i - 1][j]; T[i][j - 1]) + 1 when X[i] != Y [j]
							}
			 
					 }
					 for (int k = 0; k < column; k++) {
			             T[0][k] = T[1][k]; //copy the values of row 1 to row 0
			         } 
			T[1][0]++; // increment T[1][0] when rows are copied , as a part of linear modeling algorithm.
			}
		  
		  int s1_length=s1.length();
		  int s2_length=s2.length();
		  int d = T[1][column-1];
			 double Dn = (double) (s1_length + s2_length - d) / (s1_length + s2_length);
			 System.out.println("Normalized Edit Distance:  "+Dn);
		 
	}

}
