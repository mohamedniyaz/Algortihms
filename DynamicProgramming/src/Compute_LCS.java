import java.util.Stack;


public class Compute_LCS {
	
	
	public void calculateLCS(String s1, String s2) {
	       
	       
		   int row = s1.length()+1;
		   int column = s2.length()+1;
		   Stack LCS = new Stack();


		       int[][] T = new int[row][column];
		   	
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						
						if(j == 0){
							T[i][j] = i ; //T[i][j] = i if j = 0 
						}
						if(i == 0){
							T[i][j] = j ;  //T[i][j] = j if i = 0 
						}
						if (i > 0 && j > 0) {
							if (s1.charAt(i - 1) == s2.charAt(j - 1))
								T[i][j] = T[i - 1][j - 1]; //T[i - 1][j - 1] when X[i] = Y [j]
							else
								T[i][j] = Math.min(T[i-1][j],T[i][j-1] )+1; // min(T[i - 1][j]; T[i][j - 1]) + 1 when X[i] != Y [j]
						}
					}
				}
				
			        for (int m = (row - 1),n = (column - 1);m >= 1 && n >= 1;) {
			            if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
			                LCS.push(s1.charAt(m - 1));
			                m--;
			                n--;
			            } else {
			                if (T[m - 1][n] <= T[m][n - 1]) {
			                    m--;
			                } else {
			                    n--;
			                }
			            }
			        }   
		       
		       System.out.print("LCS:                       ");
		       while (!LCS.isEmpty()) {
		           System.out.print(LCS.pop());
		           System.out.print(" ");
		       }
		   }
}
