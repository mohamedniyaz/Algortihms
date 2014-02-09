import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class EdgeDisjoint {
	
	public static int N ;
	public double mCst = 0.0,cCst = 0.0;
	public static String fileName ="input.txt";
	
	public static void printGraph(ArrayList<ArrayList<node>> adjVer)
	{
		int len = adjVer.size();
		ArrayList<node> aN = adjVer.get(0);
		System.out.println("Total Number of Vertices : "+aN.get(0).svertex);
		
		for(int i = 1; i <len; i++)
		{
			System.out.println("\n");
			if(adjVer.get(i) != null)
			{
				ArrayList<node> aL = adjVer.get(i);
				if(!aL.isEmpty())
				{
					int size = aL.size();				
					System.out.println("From  Node "+aL.get(0).svertex);
					
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
	
	
	public static void createGraph(ArrayList<ArrayList<node>> adjVer, int firstNo, int secondNo, double thirdNo) throws FileNotFoundException
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
	    				node ns = new node(-1,-1,0.0);
	    				ArrayList<node> aL = new ArrayList();
	    				aL.add(ns);
	    				adjVer.add(i,aL);
	    				
	    			}
	    			
	    			node ns  = new node(firstNo,firstNo,(double)firstNo);
	    			ArrayList<node> aL = adjVer.get(lineNum);
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
	    			ArrayList<node> aT1 = adjVer.get(firstNo);
	    			check1 = (int)aT1.get(0).getSVertex();
	    			ArrayList<node> aT2 = adjVer.get(secondNo);
	    			check2 = (int)aT2.get(0).getSVertex();
	    			
	    			if(check1 == -1) 
	    			{
	    				node ns  = new node(firstNo,secondNo,thirdNo);
		    			ArrayList<node> aL = adjVer.get(firstNo);
		    			aL.clear();
		    			aL.add(ns);
		    			adjVer.set(firstNo,aL);				    			
		    				
	    			}
	    			
	    			if(check2 == -1)
	    			{
	    				
	    					node ns  = new node(secondNo,firstNo,thirdNo);
	    					ArrayList<node> aL = adjVer.get(secondNo);
	    					aL.clear();
		    				aL.add(ns);
		    				adjVer.set(secondNo,aL);			    				
	    			}
	    				
    				if(check1 == -1 && check2 != -1)
    				{
    					node nd  = new node(secondNo,firstNo,thirdNo);
	    				ArrayList<node> aD = adjVer.get(secondNo);
	    				aD.add(nd);
	    				adjVer.set(secondNo,aD);	
    				}
	    			
    				if(check1 != -1 && check2 == -1)
    				{
    					node ns  = new node(firstNo,secondNo,thirdNo);
	    				ArrayList<node> aL = adjVer.get(firstNo);
	    				aL.add(ns);
	    				adjVer.set(firstNo,aL);	
	    					
    				}
    				
    				if(check1 != -1 && check2 != -1)
    				{
    					node ns  = new node(firstNo,secondNo,thirdNo);
	    				ArrayList<node> aL = adjVer.get(firstNo);
	    				aL.add(ns);
	    				adjVer.set(firstNo,aL);	
	    				
	    				node nd  = new node(secondNo,firstNo,thirdNo);
	    				ArrayList<node> aD = adjVer.get(secondNo);
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
		//System.out.println("Path ");
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
						//System.out.print(aL.get(i));
					}				
					if(i != len - 2 )
					{
						//System.out.print("--");
					}
					if(i == len - 1 )
					{
						//System.out.println(" Minimum Cost : "+aL.get(i));
					}				
					
				}	
			}
			
		}
	}
	
	public  void printAllPath(ArrayList<ArrayList<Double>> tPath, ArrayList<ArrayList<Double>> fPath)
	{
		Iterator it = tPath.listIterator();
		System.out.println();
		while(it.hasNext())
		{
			ArrayList<Double> aL =  (ArrayList<Double>) it.next();
			int len = aL.size();		
			
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
	
	public  void findSP(double sN, double dN, ArrayList<ArrayList<node>> adjVer, boolean vstVer[], ArrayList<Double> cPath, ArrayList<ArrayList<Double>> tPath)
	{
		
		
		ArrayList<node> aL = adjVer.get((int)sN);
		Iterator i = aL.listIterator();
		double weight = 0.0, dVertex;
		
				
		while(i.hasNext())
		{
			
			node ns =  (node) i.next();
			if(ns.getSVertex() != -1.0)
			{
				//node ns =  (node) i.next();
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
	
	public void reCreateGraph(ArrayList<ArrayList<Double>> tPath, ArrayList<ArrayList<node>> adjVer)
	{
		
		Iterator it = tPath.listIterator();
		while(it.hasNext())
		{
			ArrayList<Double> aL =  (ArrayList<Double>) it.next();
			
			int len = aL.size();
			for(int i = 0; i < len-2; i++)
			{
				
				if(mCst == aL.get(len-1))
				{
					double Snode = aL.get(len - (i+2));
					double Dnode = aL.get(len - (i+3));
					ArrayList<node> aVS= adjVer.get((int) Snode);  
					int lnS = aVS.size();
					for(int n = 0; n < lnS; n++)
					{
						if(aVS.get(n).dvertex == Dnode)
						{
							double weight = aVS.get(n).getWeight();
							aVS.get(n).setWeight(-weight);
						}
					}
					
					ArrayList<node> aVD= adjVer.get((int) Dnode);  
					int lnD = aVD.size();
					for(int n = 0; n < lnD; n++)
					{
						if(aVD.get(n).dvertex == Snode)
						{
							aVD.remove(n);
							break;
						}
					}
					
				}	
				
			}
			
		}
		
	}
	
	public void edgeDisjointPath(ArrayList<ArrayList<Double>> fPath, ArrayList<node> cVer, ArrayList<ArrayList<node>> adjVer, ArrayList<ArrayList<node>> fadjVer,ArrayList<ArrayList<node>> tadjVer)
	{
		
		int len = fPath.size();
		
		for(int i = 0; i < len-1; i++)
		{
			ArrayList<Double> a1 = fPath.get(i);
			ArrayList<Double> a2 = fPath.get(i+1);
			double b1,b2,c1,c2;
			
			for(int b = 0; b < a1.size()-1; b++)
			{
				b1 = a1.get(b);
				b2 = a1.get(b+1);
				for(int c = 0; c < a2.size()-2; c++)
				{
					c1 = a2.get(c);
					c2 = a2.get(c+1);
					
					if(b1 == c1 && b2 == c2)
					{
						node ns = new node(b1,b2,b2);
						cVer.add(ns);
					}
					if(b1 == c2 && b2 == c1)
					{
						node ns = new node(b1,b2,b2);
						cVer.add(ns);
					}
					
				}
			}
			
		}
		
		//------------------------------------------------------------Re-Create Graph------------------------------------------------------------//
		
		for(int i = 0; i < N+1; i++)
		{
			if(i == 0)
			{
				node ns = new node(N,N,N);
				ArrayList<node> aL = new ArrayList();
				aL.add(ns);
				tadjVer.add(i,aL);
			}
			else 
			{
				node ns = new node(-1,-1,0.0);
				ArrayList<node> aL = new ArrayList();
				aL.add(ns);
				tadjVer.add(i,aL);
			}			
			
		}
		
		for(int i = 0; i < len; i++)
		{
			ArrayList<Double> aL = fPath.get(i);
			
			for(int j = 0; j < aL.size()-2; j++)
			{
				
				double a1 = aL.get(j);
				double a2 = aL.get(j+1);
				ArrayList<node> aV =  fadjVer.get((int)a1);
				//Iterator it = aV.listIterator();
				
				for(int a = 0; a < aV.size(); a++)
				{
					node ns = (node) aV.get(a);
					
					if(ns.getdVertex() == a2)
					{
						if(tadjVer.get((int)a1).get(0).getSVertex() == -1)
						{
							tadjVer.get((int)a1).get(0).clear();
							node n = new node(a1,a2,ns.getWeight());
							ArrayList<node> aN = new ArrayList();
							aN.add(n);
							tadjVer.set((int) a1,aN);
						}
						else
						{
							node n = new node(a1,a2,ns.getWeight());
							ArrayList<node> aN = tadjVer.get((int)a1);
							aN.add(n);
							tadjVer.set((int)a1,aN);
							
						}
					}
					
					
				}
								
			}
			
		}
		
		
		
		//System.out.println("Common Vertices");
		
		/*for(int i = 0; i < cVer.size(); i++)
		{
			System.out.print(cVer.get(i).getSVertex()+" "+cVer.get(i).getdVertex());
			System.out.print(" ");
		}*/
		//----------------------------------------------------------Removing Common Vertices--------------------------------------------------------//
		int ln = cVer.size();
		for(int i = 0; i < ln; i++)
		{
			
			
			double Snode = cVer.get(i).getSVertex();
			double Dnode = cVer.get(i).getdVertex();
			
			ArrayList<node> aVS= tadjVer.get((int) Snode);  
			int lnS = aVS.size();
			for(int n = 0; n < lnS; n++)
			{
				if(aVS.get(n).dvertex == Dnode)
				{
					aVS.remove(n);
					break;
				}
			}
			
			ArrayList<node> aVD= tadjVer.get((int) Dnode);  
			int lnD = aVD.size();
			for(int n = 0; n < lnD; n++)
			{
				if(aVD.get(n).dvertex == Snode)
				{
					aVD.remove(n);
					break;
				}
			}		
						
		}	
		//----------------------------------------------Removing the Veritces n both the path---------------------------------------------------------//
		
		/*boolean find[] = new boolean[adjVer.size()];
		find[0] = true;
		
		for(int i = 0; i < len; i++)
		{
			ArrayList<Double> a1 = fPath.get(i);
							
			double b1;
			for(int b = 0; b < a1.size()-1; b++)
			{
				b1 = a1.get(b);
				find[(int)b1] = true;				
			}
			
		}
		
		
		for(int i = 1; i < find.length; i++)
		{
			if(find[i] == false)
			{
				node ns = new node(-1,-1,-1);
				ArrayList<node> aN = new ArrayList();
				aN.add(ns);
				adjVer.set(i, aN);
			}
		}*/
		
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		
		int firstNo = 0,secondNo = 0;
        double thirdNo = 0;
        ArrayList<ArrayList<node>> adjVer = new ArrayList();
        ArrayList<ArrayList<node>> tadjVer = new ArrayList();
        ArrayList<ArrayList<node>> fadjVer = new ArrayList();
        ArrayList<ArrayList<Double>> tPath = new ArrayList();
        ArrayList<ArrayList<Double>> fPath = new ArrayList();
        ArrayList<Double> cPath = new ArrayList();
        ArrayList<node> cVer = new ArrayList();
                
        createGraph(adjVer, firstNo, secondNo, thirdNo);
        createGraph(fadjVer, firstNo, secondNo, thirdNo);
        //printGraph(adjVer);
        
        boolean vstVer[] = new boolean[N+1];
        double startN,desN;
		
        System.out.println("The Start Node and Destination Node should be within "+N);
        System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the start Node :");
		startN  = scan.nextInt();
				
		System.out.println("Enter the destination Node :");
		desN  = scan.nextInt();
		
		cPath.add(startN);
		vstVer[(int)startN] = true;
		EdgeDisjoint a = new EdgeDisjoint();
		
		//--------------------------------------------------------------------------------------------------------//
		a.findSP(startN,desN,adjVer,vstVer,cPath,tPath);
		//System.out.println();
		//System.out.println("The Shortest path from "+startN+" to "+desN+" using Dijkstra's");
		//System.out.println();
		a.printSP(tPath,fPath);
		//System.out.println();
		
		//--------------------------------------------------------------------------------------------------------//
		a.reCreateGraph(tPath,adjVer);
		
		
		cPath.clear();
		tPath.clear();
		boolean vstVr[] = new boolean [N+1];
		cPath.add(startN);
		vstVr[(int)startN] = true;
		
		//--------------------------------------------------------------------------------------------------------//
		a.findSP(startN,desN,adjVer,vstVr,cPath,tPath);
		//System.out.println();
		//System.out.println("The Shortest path from "+startN+" to "+desN);
		//System.out.println();
		a.printSP(tPath,fPath);
		
		//--------------------------------------------------------------------------------------------------------//
		//System.out.println();
		a.edgeDisjointPath(fPath,cVer,adjVer,fadjVer,tadjVer);
		//System.out.println();
		//printGraph(tadjVer);
		System.out.println();
		
		cPath.clear();
		tPath.clear();
		boolean vstV[] = new boolean [N+1];
		cPath.add(startN);
		vstV[(int)startN] = true;
		
		//--------------------------------------------------------------------------------------------------------//
		a.findSP(startN,desN,tadjVer,vstV,cPath,tPath);
		System.out.println("The paths from "+startN+" to "+desN+ " Edge Disjoint Algorithm");
		a.printAllPath(tPath,fPath);
		
		
	}

}


class node {
	
	public double svertex;
	public double dvertex;
	public double weight;
	
	public node(double sVertex,double dVertex, double Weight)
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

