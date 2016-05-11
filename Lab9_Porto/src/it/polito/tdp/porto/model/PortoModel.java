package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.porto.db.PortoDAO;

public class PortoModel {
	
	private Graph<Creator, DefaultEdge> grafo;
	private List<Creator> autori;
	private PortoDAO dao;
	private final int N;
	private List<Creator>[] cluster;
	private int numCluster;
	
	
	
	public PortoModel() {
		grafo = new SimpleGraph<Creator, DefaultEdge>(DefaultEdge.class);
		dao = new PortoDAO();
		autori = new ArrayList<Creator>(dao.getAutori());
		N=autori.size();
		cluster= new List[N];
		numCluster=1;
		
			
	}
	
	
	
	
	public void createGraph() {
		
		Graphs.addAllVertices(grafo, autori);
		
		for(Creator c1: autori) {
			for(Creator c2: this.getCoautori(c1)) {
				if(!c1.equals(c2) && !grafo.containsEdge(c1, c2)) {
					grafo.addEdge(c1, c2);
					
				}
			}
		}
		
	
		
		
	}
	
	public List<Creator> getCoautori(Creator c) {
		
		PortoDAO dao = new PortoDAO();
		return dao.getCoautori(c);
	
		
		
	}

	public Graph<Creator, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<Creator, DefaultEdge> grafo) {
		this.grafo = grafo;
	}

	

	public List<Creator> getAutori() {
		return autori;
	}




	public void setAutori(List<Creator> autori) {
		this.autori = autori;
	}

	public List<Creator> getVicini(Creator c) {
		return Graphs.neighborListOf(grafo, c);
	}
	
	public List<Creator>[] getCluster() {
		return cluster;
	}
	
	public void popolaCluster() {
		
		List<Creator> connessiOld = new ArrayList<Creator>();
		List<Creator> connessiNew = new ArrayList<Creator>();
		numCluster=1;

		
		
		for(Creator c: grafo.vertexSet()) {
			
			connessiOld.clear();
			connessiOld.addAll(connessiNew);
			connessiNew.clear();
			
			GraphIterator <Creator, DefaultEdge> dfv = 
					new DepthFirstIterator<Creator, DefaultEdge>(grafo, c);
			
			while(dfv.hasNext()) {
				connessiNew.add(dfv.next());
			}
			
			if(!compareList(connessiNew, connessiOld)) {
				cluster[numCluster] = new ArrayList<Creator>(connessiNew);
				numCluster++;
				
				
				
			}
				
			
		}
		

		
	}
	
	
	
	public boolean compareList(List<Creator> l1, List<Creator> l2) {
		int flag=0;
		for(Creator c1: l1) {
			for(Creator c2: l2) {
				if(c1.equals(c2)) {
					flag++;
					
				}
			}
		}
		
		if(flag==l1.size())
			return true;
		
		return false;
	}




	public int getNumCluster() {
		return numCluster;
	}
	
	
	public List<Article> getArticle(Creator c1, Creator c2) {
		return dao.getArticle(c1, c2);
	}
	
	
	

}
