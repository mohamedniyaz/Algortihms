import java.util.List;

public class Compute_LCS_Linear_Memory {


	 public static List lcs_recursive(String x, String y, List LCS) {
		 if (x.length() == 1) {
	        	
	        	for(int i =0;i<y.length();i++){ // Compare X[0] to each symbol in Y[0, ..., Y.size()-1]
	        		 if (x.charAt(0) == y.charAt(i)) { //If there is a symbol match push_back X[0] on to LCS
	        			 LCS.add(x.charAt(0));
	                     break;
	                 }
	        		
	        	}
	        } else if (y.length() == 1) {
	       
	        	for(int j =0;j<x.length();j++){ //Compare Y[0] to each symbol in X[0, ..., X.size()-1]
	       		 if (y.charAt(0) == x.charAt(j)) { //If there is a symbol match push_back Y[0] on to LCS
	       			LCS.add(y.charAt(0));
	                    break;
	                }
	       		
	       	}
	        } else {
	        
	            int [][] middleRows =Computing_Edit_Distance_MiddleRow(x,y); 
	            int verticalSplit = 0;
	            int limit=middleRows[0].length;
	            
	            int upper =Integer.MAX_VALUE;
	            int lower;
	            for (int k = 0; k < limit; k++) {
	            	lower = middleRows[0][k] + middleRows[1][k];
	                if (upper >= lower) {
	                    upper = lower;
	                    verticalSplit = k;
	                }
	            }
	            int horizontalSplit=x.length()/2;

	            String x_front = (String) x.subSequence(0, horizontalSplit);

	            String y_front = (String) y.subSequence(0, verticalSplit);

	            String x_back = (String) x.subSequence(horizontalSplit, x.length());

	            String y_back = (String) y.subSequence(verticalSplit, y.length());
	            lcs_recursive(x_front, y_front, LCS);
	            lcs_recursive(x_back, y_back, LCS);
	        }
	        return LCS;
	    }
	
	 private static int[][] Computing_Edit_Distance_MiddleRow(String s1, String s2) { //calculates edit distance for the middle rows
	       
		 
		 int mid = s1.length() / 2;
	        
	            String s1substring = (String) s1.subSequence(0, mid);
	    
	            int[][] forwardArray = getNormalizedEditDistance(s1substring, s2); //row is computed left to right
	            
	            String s1substringreverse = (String) s1.subSequence(mid, s1.length());
	     
	            s1substringreverse = new StringBuffer(s1substringreverse).reverse().toString();
	     
	            String s2reverse = new StringBuffer(s2).reverse().toString();
	            
	            int[][] reverseArray = getNormalizedEditDistance(s1substringreverse, s2reverse);  // to compute from right to left we will reverse the string
	            
	            int arrSize = reverseArray[1].length;
	            
	            for (int l = 0; l <= arrSize - 1; l++) {
	            	forwardArray[1][l] = reverseArray[0][(arrSize - 1) - l];
	            }

	            return forwardArray;
	    }
	    
    
    
    public static int[][] getNormalizedEditDistance(String s1, String s2) { // used same code in Compute_Edit_Distance.java, not reused to separate each algorithm in separate file. 
		
		 int row = s1.length()+1;
		 int column = s2.length()+1; 
		 int[][] T = new int[2][column]; //we can compute the Normalized edit distance by storing only two rows of the table.
		
		 T[1][0] = 1; //T[i][j] = i if j = 0 , since we have only two rows, T[1][0] will be equal to i
		 for (int j = 0; j < column; j++) {
	            T[0][j] = j; //T[i][j] = j if i = 0 
	        }
		
			for(int i=1;i<row;i++){  // below code - i is taken as 1 since we have only two rows 
				
					 for (int j = 1; j < column; j++) {   // T[i][j]  if i; j > 0
				
							if (s1.charAt(T[1][0] - 1) == s2.charAt(j - 1)){
								T[1][j] = T[0][j - 1];   //T[i - 1][j - 1] when X[i] = Y [j]
							}else{
								T[1][j] = Math.min(T[0][j],T[1][j-1] )+1; // min(T[i - 1][j]; T[i][j - 1]) + 1 when X[i] != Y [j]
							}
			 
					 }
					 for (int k = 0; k < column; k++) {
			             T[0][k] = T[1][k]; //copy the values of row 1 to row 0
			         }
			T[1][0]++; // increment T[1][0] when rows are copied , as a part of linear modeling algorithm.
			}
		 return T;
		 
	}
  
}
