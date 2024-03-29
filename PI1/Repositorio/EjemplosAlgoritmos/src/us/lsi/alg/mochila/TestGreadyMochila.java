package us.lsi.alg.mochila;


import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.path.EGraphPath.PathType;


public class TestGreadyMochila {
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		DatosMochila.iniDatos("ficheros/objetosMochila.txt");
		MochilaVertex.capacidadInicial = 457;
		MochilaVertex v1 = MochilaVertex.initialVertex();
//		MochilaVertex v2 = MochilaVertex.lastVertex();
//		Predicate<MochilaVertex> goal = v->v.equals(v2);
//		System.out.println(e1);
//		System.out.println(e2);			
		Double r2 = MochilaHeuristic.heuristic(v1,MochilaVertex.goal(),null);
		System.out.println("H "+r2);
		
		EGraph<MochilaVertex, MochilaEdge> graph = 
				EGraph.virtual(v1,MochilaVertex.goal(), PathType.Sum, Type.Max)
				.greedyEdge(MochilaVertex::greedyEdge)
				.heuristic(MochilaHeuristic::heuristic)
				.build();
		
		GreedyOnGraph<MochilaVertex, MochilaEdge> gs = GreedyOnGraph.of(graph);
		
		GraphPath<MochilaVertex, MochilaEdge> gp = gs.path();
		
		System.out.println("G "+gp.getWeight());
		
//		Optional<GraphPath<MochilaVertex, MochilaEdge>> r = gs.path();
//		System.out.println("2 "+r.get().getWeight());
//		System.out.println(r.getWeight());
//		Double r3 = MochilaHeuristic.voraz(e1, e->e.equals(e2),e2);
//		System.out.println(r3);
//		GraphPath<MochilaVertex, MochilaEdge> r4 = MochilaHeuristic.greadyPath(e1,e->e.equals(e2));
//		System.out.println(r4);
//		System.out.println(r4.getWeight());
//		EGraph<MochilaVertex,MochilaEdge> graph = Graphs2.simpleVirtualGraph(e1);
//		GreedySearch<MochilaVertex, MochilaEdge> rr = GraphAlg.greedy(graph,
//				MochilaVertex::greedyEdgeHeuristic,
//				e->e.equals(e2));
//		System.out.println(rr.weightToEnd());

	}

}
