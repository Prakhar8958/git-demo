package Graph;
import java.util.*;
public class graph1 {

	
	

		
		static class Edge
		{
			int src;
			int des;
			public Edge(int s,int d) 
			{
			this.src=s;
			this.des=d;
			}
		}
		
		public static void createGraph(ArrayList<Edge> graph[]) 
		{
			for(int i=0; i<graph.length; i++) 
			{
			 graph[i]=new ArrayList<Edge>();
			}
			graph[0].add(new Edge(0,1));
			graph[0].add(new Edge(0,4));
			
			graph[1].add(new Edge(1,0));
			graph[1].add(new Edge(1,4));
			graph[1].add(new Edge(1,2));
			
			graph[2].add(new Edge(2,3));
			graph[2].add(new Edge(2,1));
			
			
			graph[3].add(new Edge(3,2));
			//graph[3].add(new Edge(3,5));
			//graph[3].add(new Edge(3,4));
			//graph[4].add(new Edge(4,5));
			
			
			graph[4].add(new Edge(4,0));
			graph[4].add(new Edge(4,1));
			graph[4].add(new Edge(4,5));
			
			graph[5].add(new Edge(5,4));
			/*
			graph[5].add(new Edge(5,4));
			graph[5].add(new Edge(5,6));
			
			graph[6].add(new Edge(6,5));
			*/
			
			
		}
		
		public static void bfs(ArrayList<Edge> graph[],int start,int v)
		{
			boolean check[]=new boolean[v];
			Arrays.fill(check, false);
			Queue<Integer> q=new LinkedList<>();
			q.add(start);
			while(!q.isEmpty())
			{
				int curr=q.remove();
				if(check[curr]==false) 
				{
				 System.out.print(curr+" ");
				 check[curr]=true;
				 for(int i=0; i<graph[curr].size(); i++) 
				 {
				  Edge e=graph[curr].get(i);
				  q.add(e.des);
				 }
				}
				
			}
		}
		
		public static void dfs(ArrayList<Edge> graph[],int curr,boolean check[]) 
		{
	     System.out.print(curr+" ");
	     check[curr]=true;
	     for(int i=0; i<graph[curr].size(); i++) 
	     {
	    	Edge e=graph[curr].get(i);
	    	if(check[e.des]==false) 
	    	{
	    	dfs(graph,e.des,check);
	    	}
	     }
			
		}
		public static void sourceToTarget(ArrayList<Edge> graph[],Boolean check[],String print,int curr,int target) 
		{
			if(curr==target) 
			{
				System.out.println(print);
				return;
			}
			for(int i=0; i<graph[curr].size(); i++) 
			{
			Edge e=graph[curr].get(i);
			if(check[e.des]==false) 
			{
			check[e.des]=true;
			sourceToTarget(graph,check,print+e.des,e.des,target);
			check[e.des]=false;
			}
				
			}
			
			
		}
		//cycle detection in directed graph
		public static boolean cycle(ArrayList<Edge> graph[],Boolean[] check,int curr,Boolean[] rec)
		{
			check[curr]=true;
			rec[curr]=true;
			for(int i=0; i<graph[curr].size(); i++)
			{
				Edge e=graph[curr].get(i);
				if(rec[e.des]==true)
				{
					return true;
				}
				else if(!check[e.des]) 
				{
					if(cycle(graph,check,e.des,rec)) {
						return true;
					}
				}
			}
			rec[curr]=false;
			
			return false;
		}
		
		//Topological order
		public static void topSort(ArrayList<Edge> graph[],Boolean check[],int curr,Stack<Integer> s,int v)
		{
			check[curr]=true;
			for(int i=0; i<graph[curr].size(); i++) 
			{
				Edge e=graph[curr].get(i);
				if(!check[e.des]) {
					topSort(graph,check,e.des,s,v);
				}
			}
			s.push(curr);
			
		}
		
		//Cycle detection in unDirceted graph
		public static boolean isCycle(ArrayList<Edge> graph[],Boolean check[],int curr,int part) 
		{
		check[curr]=true;
		for(int i=0; i<graph[curr].size(); i++)	
		{
			Edge e=graph[curr].get(i);
			if(check[e.des]==true && part!=e.des) 
			{
				return true;
			}
			if(!check[e.des]) 
			{
				if(isCycle(graph,check,e.des,curr)) 
				{
					return true;
				}
			}
		}
			
			return false;
		}
		
		/* helper function for Topological order
		public static void topLogicalSort(ArrayList<Edge> graph[],int v)
		{
			Boolean check[]=new Boolean[v];
			Stack<Integer> s=new Stack<Integer>();
			for(int i=0; i<v; i++)
			{
				if(!check[i]) 
				{
				topSort(graph,check,i,s);	
				}
			}
			while(!s.isEmpty()) {
				System.out.print(s.pop()+" ");
			}
		}
		*/
		//**Dijkstra Algo
		
		
		
		
		public static void main(String args[])
		{
			int v=6;
		    ArrayList<Edge> graph[]=new ArrayList[v];
			createGraph(graph);
			boolean check[]=new boolean[v];
			Arrays.fill(check, false);
			bfs(graph,0,v);
			
			System.out.println("***Cycle Detection in Undirected Graph***");
			Boolean check2[]=new Boolean[v];
			for(int i=0; i<check2.length; i++) 
			{
			  check2[i]=false;
			}
			for(int i=0; i<v; i++)
			{
				if(!check2[i]) {
					boolean ans=isCycle(graph,check2,i,-1);
				if(ans) {
					System.out.println("True");
			        return;
				}
				}
			}
			System.out.println("False");
			
			/*
			System.out.println("*****Cycle Detection*****");
			Boolean check[]=new Boolean[v];
			for(int i=0; i<check.length; i++) 
			{
			  check[i]=false;
			}
			Boolean rec[]=new Boolean[v];
			for(int i=0; i<rec.length; i++) 
			{
			  rec[i]=false;
			}
			for(int i=0; i<check.length; i++) 
			{
			boolean isCycle=cycle(graph,check,i,rec);
			if(isCycle)
			{
				System.out.println("True");
			    return;
			}
			}
			*/
			
			//***Topological Sorting***
			/*
			Boolean check[]=new Boolean[v];
			for(int i=0; i<check.length; i++) 
			{
			  check[i]=false;
			}
			Stack<Integer> s=new Stack<>();
			topSort(graph,check,0,s,v);
				while(!s.isEmpty()) {
					System.out.print(s.pop()+" ");
				}
			*/
			/*
			System.out.println("****Graph Traversal****");
			boolean check3[]=new boolean[v];
			Arrays.fill(check3, false);
			dfs(graph,0,check3);
			*/
			/*
			String print="";
			sourceToTarget(graph,check,"0",0,5);
			*/
			//print all the neighours of 2
			/*
			for(int i=0; i<graph[2].size(); i++) 
			{
				Edge e=graph[2].get(i);
				System.out.println(e.des+","+e.wt);
			}
			
			
			System.out.println("****Graph Traversal****");
			bfs(graph,v);
	       */
		
			
		}
		
	}


