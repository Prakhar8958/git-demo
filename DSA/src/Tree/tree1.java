package Tree;
import java.util.*;
public class tree1 {

	static class node{
		int data;
		node left;
		node right;
		node(int data){
			this.data=data;
			this.left=null;
			this.right=null;
		}
	}
	static class  BinaryTree
	{
		static int index=-1;
		public static node buildTree(int nodes[]) {
			index++;
			if(nodes[index]==-1) {
				return null;
			}
			node newnode=new node(nodes[index]);
			newnode.left=buildTree(nodes);
			newnode.right=buildTree(nodes);
			return newnode;
		}
	}
	/*
	public static void inorder(node root) {
		if(root==null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.data+" ");
		inorder(root.right);
	}
	*/

	public static void levelorder(node root) {
		if(root==null) 
		{
			return;
		}
		Queue<node> q=new LinkedList<>();
		q.add(root);
		q.add(null);
		while(!q.isEmpty()) 
		{
		node currnode=q.remove();
		if(currnode==null) 
		{
			System.out.println("");
			if(q.isEmpty()) {
				break;
			}
			else {
				q.add(null);
			}
		}
		else {
			System.out.print(currnode.data+" ");
			if(currnode.left!=null) {
				q.add(currnode.left);
			}
			if(currnode.right!=null) {
				q.add(currnode.right);
			}
		}
		}
	}

	public static int countNodes(node root) {
	if(root==null) {
		return 0;
	}
	int leftnode=countNodes(root.left);
	int rightnode=countNodes(root.right);
	return leftnode+rightnode+1;
	}

	public static int sumNodes(node root) {
		if(root==null) {
			return 0;
		}
		int leftsum=sumNodes(root.left);
		int rightsum=sumNodes(root.right);
		return leftsum+rightsum+root.data;
	}

	public static int height(node root) 
	{
		if(root==null) 
		{
			return 0;
		}
		int leftheight=height(root.left);
		int rightheight=height(root.right);
		int myheight=Math.max(leftheight, rightheight)+1;
		return myheight;
		
	}

	public static int diameter(node root) 
	{
		if(root==null) 
		{
			return 0;
		}
		int dia1=diameter(root.left);
		int dia2=diameter(root.right);
		int dia3=height(root.left)+height(root.right)+1;
		return Math.max(dia3, Math.max(dia2, dia1));
	}


	static class TreeInfo
	{
		int ht;
		int diam;
		TreeInfo(int ht,int diam)
		{
			this.ht=ht;
			this.diam=diam;
		}
	}

	public static TreeInfo diameter2(node root) {
		if(root==null) {
			return new TreeInfo(0,0);
		}
		TreeInfo left=diameter2(root.left);
		TreeInfo right=diameter2(root.right);
		 
		int myHeight=Math.max(left.ht, right.ht)+1;
		int dia1=left.diam;
		int dia2=right.diam;
		int dia3=left.ht+right.ht+1;
		int mydiam=Math.max(dia1, Math.max(dia2, dia3));
		TreeInfo myinfo=new TreeInfo(myHeight,mydiam);
		return myinfo;
		
	}

	public static void main(String args[]) 
	{
		int nodes[]= {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
		BinaryTree tree=new BinaryTree();
		node root=tree.buildTree(nodes);
		levelorder(root);
		System.out.println("Total Number of Nodes: "+countNodes(root));
	    System.out.println("Total Sum of Nodes: "+sumNodes(root));
	    System.out.println("Total Height of Nodes: "+height(root));
	    System.out.println("Total Diameter of Nodes: "+diameter(root));
	    System.out.println("Total Diameter of Nodes: "+diameter2(root));
	}
		
	}


