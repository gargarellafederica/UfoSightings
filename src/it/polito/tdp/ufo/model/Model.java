package it.polito.tdp.ufo.model;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {
	private SightingsDAO dao;
	private List<String> stati;
	private Graph<String, DefaultEdge> grafo;
	
	
	public Model() {
		this.dao= new SightingsDAO();
	}
	public List<AnnoCount> getAnnieCount(){
		return this.dao.AnnoeCount();
	}

	public void creaGrafo(Year anno) {
		this.grafo= new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		this.stati= this.dao.getStatibyanno(anno);
		Graphs.addAllVertices(this.grafo, this.stati);
		
		//ho solo 52 stati quindi posso usare la soluzione semplice e trovare gli archi con due cicli for annidati
		
		for(String s1: this.grafo.vertexSet()) {
			for( String s2: this.grafo.vertexSet()) {
				if(!s1.equals(s2)) {
					if(this.dao.esistearco(s1,s2, anno))
						this.grafo.addEdge(s1, s2);
					}
			}
		}
		System.out.println("Grafo creato!");
		System.out.println("Numero vertici: " + this.grafo.vertexSet().size());
		System.out.println("Numero archi: " + this.grafo.edgeSet().size());

	}
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	public List<String> getStati() {
		return this.stati;
	}
	public List<String> getSuccessori(String stato) {
		return Graphs.successorListOf(this.grafo, stato);
	}
	public List<String> getPredecessori(String stato) {
		return Graphs.predecessorListOf(this.grafo, stato);
	}
	public List<String> getRaggiungibili(String stato) {
		List<String> raggiungibili= new LinkedList<>();
		
		BreadthFirstIterator<String, DefaultEdge> it= new BreadthFirstIterator<>(this.grafo, stato);
		it.next(); //tolgo il primo perchè non voglio il vertice da cui parto nell'elenco
		while(it.hasNext())
			raggiungibili.add(it.next());
		return raggiungibili;
	}
}
