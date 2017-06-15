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
	<V> V getValue(){
		return (V) value;
	}
	
	//Genera un documento .dot diferenciando por niveles el jugador imprimiento el valor de los nodos del arbol
	public void generarDOT() throws FileNotFoundException, InterruptedException{
		Tree<T> arbol = this;
		//Reemplaza el archivo si ya existe.
		PrintWriter writer = new PrintWriter("tree.dot");
		writer.println("digraph tree{");
		//Recorro por nivel, visito una vez cada nodo
		Queue<Tree> cola = new Queue<Tree>();
		Queue<Tree> cola2 = new Queue<Tree>();
		cola.enqueue(arbol);
		int level = 0, i;
		boolean flag = true;
		while(!(cola.isEmpty() && cola2.isEmpty())){
			Tree aux;
			if(flag){
				if(!cola.isEmpty()){
					aux = cola.dequeue();
					for (i=0;i<aux.children.size();i++) {
						writer.println(aux.value +" -> "+ ((Tree)aux.children.get(i)).value + ";");
						cola2.enqueue(((Tree)aux.children.get(i)));
					}
				}else{
					flag=!flag;
				}
			}else{
				if(!cola2.isEmpty()){
					aux = cola2.dequeue();
					writer.println(aux.value + " [shape=box]");
					for (i=0;i<aux.children.size();i++) {
						writer.println(aux.value +" -> "+ ((Tree)aux.children.get(i)).value + ";");
						cola.enqueue(((Tree)aux.children.get(i)));
					}
				}else{
					flag=!flag;
				}	
			}
		}
		writer.println("}");
		writer.close();
	}
	
	public ArrayList<Tree<T>> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Tree<T>> children) {
		this.children = children;
	}
	//INDEXES MAX Y MIN DEL CHILDREN
		public int getMaxHeuristica(){
			int index=0;
			int h=0;
			int i;
			int heuristica = 0;
			
			for(i=0;i<children.size();i++){
				heuristica = ((ArrayList<Move>)children.get(i).getValue()).get(((ArrayList<Move>)children.get(i).getValue()).size() - 1).getHeuristica((ArrayList<Move>) value);
				if(heuristica > h){
					index = i;
					h = heuristica;
				}
			}
			return index;
		}
		public int getMinHeuristica(){
			int index=0;
			int h=0;
			int i;
			int heuristica = 0;
			
			for(i=0;i<children.size();i++){
				heuristica = ((ArrayList<Move>)children.get(i).getValue()).get(((ArrayList<Move>)children.get(i).getValue()).size() - 1).getHeuristica((ArrayList<Move>) value);
				if(heuristica < h){
					index = i;
					h = heuristica;
				}
			}
			return index;
		}
}
