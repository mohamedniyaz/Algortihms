import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Dijkstra {
	
	public static int N ;
	public double mCst = 0.0,cCst = 0.0;
	public static String fileName ="input.txt";
	
	public static void printGraph(ArrayList<ArrayList<nodes>> adjVer)
	{
		int len = adjVer.size();
		ArrayList<nodes> aN = adjVer.get(0);
		System.out.println("Total Number of Vertices : "+aN.get(0).svertex);
		
		for(int i = 1; i <len; i++)
		{
			System.out.println("\n");
			if(adjVer.get(i) != null)
			{
				ArrayList<nodes> aL = adjVer.get(i);
				if(!aL.isEmpty())
				{
					int size = aL.size();				
					System.out.println("From  nodes "+aL.get(0).svertex);
					
					for(int j = 0; j < size; j++)
					{
						System.out.print("("+aL.get(j).dvertex+", "+aL.get(j).weight+")");
						if(j != size-1)
						{
							System.out.print("--");
						}
						
					}
				}
				
			}
			
		}
	}
	
	
	public static void createGraph(ArrayList<ArrayList<nodes>> adjVer, int firstNo, int secondNo, double thirdNo) throws FileNotFoundException
	{
		
		Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
		int check1 = 0, check2 = 0;
		
		try
        {

	    	for (int lineNum = 0; s.hasNextLine(); lineNum++) 
	    	{
	    	
	    		if(lineNum == 0)
	    		{
	    			String a[] = s.nextLine().split(" ");
	    			firstNo = Integer.parseInt(a[0]);
	    			N = Integer.parseInt(a[0]);
	    			
	    			for(int i = 0; i < firstNo+1; i++)
	    			{
	    				nodes ns = new nodes(-1,-1,0.0);
	    				ArrayList<nodes> aL = new ArrayList();
	    				aL.add(ns);
	    				adjVer.add(i,aL);
	    				
	    			}
	    			
	    			nodes ns  = new nodes(firstNo,firstNo,(double)firstNo);
	    			ArrayList<nodes> aL = adjVer.get(lineNum);
	    			check1 = (int)aL.get(0).getSVertex();
	    			if(check1 == -1)
	    			{
	    				aL.clear();
		    			aL.add(ns);
		    			adjVer.set(lineNum,aL);
		    			check1 = 0; 
	    			}
	    			
	    		}
	    		else
	    		{
	    			String a[] = s.nextLine().split(" ");
	    			for(int i = 0; i < a.length; i++)
	    			{
	    			
	    				if (i == 0)
	    				{
	    					firstNo = Integer.parseInt(a[i]);	    				
	    				}
	    				else if(i == 1)
	    				{
	    					secondNo = Integer.parseInt(a[i]);
	    				}
	    				else if(i == 2)
	    				{
	    					thirdNo = Double.parseDouble(a[i]); 
	    				}
	    					    					    				
	    			}
	    			
	    			check1 = 0;
	    			check2 = 0;
	    			ArrayList<nodes> aT1 = adjVer.get(firstNo);
	    			check1 = (int)aT1.get(0).getSVertex();
	    			ArrayList<nodes> aT2 = adjVer.get(secondNo);
	    			check2 = (int)aT2.get(0).getSVertex();
	    			
	    			if(check1 == -1) 
	    			{
	    				nodes ns  = new nodes(firstNo,secondNo,thirdNo);
		    			ArrayList<nodes> aL = adjVer.get(firstNo);
		    			aL.clear();
		    			aL.add(ns);
		    			adjVer.set(firstNo,aL);				    			
		    				
	    			}
	    			
	    			if(check2 == -1)
	    			{
	    				
	    					nodes ns  = new nodes(secondNo,firstNo,thirdNo);
	    					ArrayList<nodes> aL = adjVer.get(secondNo);
	    					aL.clear();
		    				aL.add(ns);
		    				adjVer.set(secondNo,aL);			    				
	    			}
	    				
    				if(check1 == -1 && check2 != -1)
    				{
    					nodes nd  = new nodes(secondNo,firstNo,thirdNo);
	    				ArrayList<nodes> aD = adjVer.get(secondNo);
	    				aD.add(nd);
	    				adjVer.set(secondNo,aD);	
    				}
	    			
    				if(check1 != -1 && check2 == -1)
    				{
    					nodes ns  = new nodes(firstNo,secondNo,thirdNo);
	    				ArrayList<nodes> aL = adjVer.get(firstNo);
	    				aL.add(ns);
	    				adjVer.set(firstNo,aL);	
	    					
    				}
    				
    				if(check1 != -1 && check2 != -1)
    				{
    					nodes ns  = new nodes(firstNo,secondNo,thirdNo);
	    				ArrayList<nodes> aL = adjVer.get(firstNo);
	    				aL.add(ns);
	    				adjVer.set(firstNo,aL);	
	    				
	    				nodes nd  = new nodes(secondNo,firstNo,thirdNo);
	    				ArrayList<nodes> aD = adjVer.get(secondNo);
	    				aD.add(nd);
	    				adjVer.set(secondNo,aD);	
    				}
	    		}
	    		
	    	}
	    	
        }
        
        catch (Exception e)
        {
            System.out.println(e.toString());
            
        }
	}
	
	public  void printSP(ArrayList<ArrayList<Double>> tPath, ArrayList<ArrayList<Double>> fPath)
	{
		Iterator it = tPath.listIterator();
		System.out.println("Path ");
		while(it.hasNext())
		{
			ArrayList<Double> aL =  (ArrayList<Double>) it.next();
			int len = aL.size();		
			
			if(mCst == aL.get(len-1))
			{
				fPath.add(aL);
				for(int i = 0; i < len; i++)
				{
					if(i != len - 1)
					{
						System.out.print(aL.get(i));
					}				
					if(i != len - 2 )
					{
						System.out.print("--");
					}
					if(i == len - 1 )
					{
						System.out.println(" Minimum Cost : "+aL.get(i));
					}				
					
				}	
			}
			
		}
	}
	
	public  void findSP(double sN, double dN, ArrayList<ArrayList<nodes>> adjVer, boolean vstVer[], ArrayList<Double> cPath, ArrayList<ArrayList<Double>> tPath)
	{
		
		
		ArrayList<nodes> aL = adjVer.get((int)sN);
		Iterator i = aL.listIterator();
		double weight = 0.0, dVertex;
		
				
		while(i.hasNext())
		{
			
			nodes ns =  (nodes) i.next();
			if(ns.getSVertex() != -1.0)
			{
				//nodes ns =  (nodes) i.next();
				dVertex = ns.getdVertex();
				
				if(vstVer[(int) dVertex] == false)
				{
					weight =  ns.getWeight();
					
					cCst += weight;
					cPath.add(dVertex);
					vstVer[(int) dVertex] = true;
									
					if(dVertex == dN)
					{
						ArrayList<Double> aP = new ArrayList();
						cPath.add(cCst);
						aP = (ArrayList<Double>) cPath.clone();
						
						if(tPath.isEmpty())
						{
							mCst = cCst;
						}
						if(cCst < mCst)
						{
							mCst = cCst;
						}
						tPath.add(aP);
						cCst -= weight;
						vstVer[(int) dVertex] = false;
						cPath.remove(cPath.size()-1);	
						cPath.remove(cPath.size()-1);
					}
					else
					{
						sN = dVertex;
						findSP(sN,dN,adjVer,vstVer,cPath,tPath);
						cCst -= weight;	
						vstVer[(int) dVertex] = false;
						cPath.remove(cPath.size()-1);
						
					}
				}
			}
								
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int firstNo = 0,secondNo = 0;
        double thirdNo = 0;
        ArrayList<ArrayList<nodes>> adjVer = new ArrayList();
        ArrayList<ArrayList<nodes>> tadjVer = new ArrayList();
        ArrayList<ArrayList<nodes>> fadjVer = new ArrayList();
        ArrayList<ArrayList<Double>> tPath = new ArrayList();
        ArrayList<ArrayList<Double>> fPath = new ArrayList();
        ArrayList<Double> cPath = new ArrayList();
        ArrayList<nodes> cVer = new ArrayList();
                
        createGraph(adjVer, firstNo, secondNo, thirdNo);
        createGraph(fadjVer, firstNo, secondNo, thirdNo);
        //printGraph(adjVer);
        
        boolean vstVer[] = new boolean[N+1];
        double startN,desN;
		
        System.out.println("The Start nodes and Destination nodes should be within "+N);
        System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the start nodes :");
		startN  = scan.nextInt();
				
		System.out.println("Enter the destination nodes :");
		desN  = scan.nextInt();
		
		cPath.add(startN);
		vstVer[(int)startN] = true;
		Dijkstra a = new Dijkstra();
		
		//--------------------------------------------------------------------------------------------------------//
		a.findSP(startN,desN,adjVer,vstVer,cPath,tPath);
		System.out.println();
		System.out.println("The Shortest path from "+startN+" to "+desN+" using Dijkstra's");
		System.out.println();
		a.printSP(tPath,fPath);
		System.out.println();
		
	}

}


class nodes {
	
	public double svertex;
	public double dvertex;
	public double weight;
	
	public nodes(double sVertex,double dVertex, double Weight)
	{
		this.svertex = sVertex;
		this.dvertex = dVertex;
		this.weight = Weight;
	}
	
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public double getSVertex()
	{
		return svertex;
	}
	
	public double getdVertex()
	{
		return dvertex;
	}
	
	
	public double getWeight()
	{
		return weight;
	}
	
	public void setSVertex(double Vertex)
	{
		svertex = Vertex;
	}
	
	public void setdVertex(double Vertex)
	{
		dvertex = Vertex;
	}
	
	public void setWeight(double Weight)
	{
		weight = Weight;
	}
}

