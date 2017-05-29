package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

import sun.misc.Queue;

public class Tree<T> {
	private T value;
	private ArrayList<Tree<T>> children;
	public Tree(T value) {
		this.value = value;
		children = new ArrayList<Tree<T>>();
	}
	public void AddChild(T value){
		children.add(new Tree<T>(value));
	}
	public Tree<T> getChild(int index){
		return children.get(index);
	}
	public void removeChild(T value){
		for (Tree<T> tree : children) {
			if(tree.value.equals(value))
				children.remove(tree);
		}	
	}
	
	public void generarDOT() throws FileNotFoundException, InterruptedException{
		Tree<T> arbol = this;
		//Reemplaza el archivo si ya existe.
		PrintWriter writer = new PrintWriter("tree.dot");
		writer.println("graph tree{");
		//Recorro por nivel, visito una vez cada nodo
		Queue<Tree> cola = new Queue<Tree>();
		Stack<Integer> stack = new Stack<Integer>();
		cola.enqueue(arbol);
		stack.push(0);
		int level = 0, i;
		while(!cola.isEmpty()){
			Tree aux = cola.dequeue();
			int current = stack.pop();
			if(current == 0){
				level++;
				
			}else{
				stack.push(current-1);
			}
			if(level%2==0)
				writer.println(aux.value + " [shape=box]");
			if(aux.children.size()!=0)
				stack.add(aux.children.size());
			for (i=0;i<aux.children.size();i++) {
				writer.println(aux.value +" -> "+ ((Tree)aux.children.get(i)).value + ";");
				cola.enqueue((Tree) aux.children.get(i));
			}
			
		}
		writer.println("}");
		writer.close();
	}
		
	
}
