package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import bean.Parola;
import db.DBConnect;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private UndirectedGraph<Parola, DefaultEdge> grafo = null;

	public boolean isPres(String nome){
		return dao.isPresente(nome);
	}
	
	public boolean getOk(String p1, String p2){
		if(p1!= null && p2!=null){
			if(!p1.equals(p2)){
				if(p1.length()>=3 && p2.length()>=3){
					String ultime= p1.substring(p1.length()-2, p1.length());
					String prime = p2.substring(0,2);
					if(ultime.equals(prime)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int conta(int lung){
		int numero = dao.conta(lung);
		return numero;
	}
	
	public List<Parola> getAll(int lung){
		List<Parola> pa = dao.getAllParole(lung);
		return pa;
	}
	
	public void buidGraph(int lung){
		grafo = new SimpleGraph<Parola, DefaultEdge> (DefaultEdge.class);
		List<Parola> queste = new LinkedList<Parola>();
		queste = getAll(lung);
		Graphs.addAllVertices(grafo, queste);
		for(Parola p1 : queste){
			for(Parola p2 : queste){
				String nome1 =p1.getNome();
				String nome2 =p2.getNome();
				if(getOk(nome1, nome2)){
					grafo.addEdge(p1, p2);
				}
			}
		}
	}
	
	public UndirectedGraph<Parola, DefaultEdge> getGraph() {
		return grafo;
	}
	
	
	
}
