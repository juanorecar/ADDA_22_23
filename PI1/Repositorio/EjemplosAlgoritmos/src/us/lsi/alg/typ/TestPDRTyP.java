package us.lsi.alg.typ;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
import us.lsi.graphs.virtual.EGraph;

public class TestPDRTyP {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		TyPVertex.datos("ficheros/tareas.txt",5);
		TyPVertex e1 = TyPVertex.first();
		
		EGraph<TyPVertex,SimpleEdgeAction<TyPVertex,Integer>> graph = 
				EGraph.virtual(e1,v->v.goal(), PathType.Last, Type.Min)
				.vertexWeight(v->v.maxCarga())
				.greedyEdge(TyPVertex::greadyEdge)
				.heuristic(Heuristica::heuristic)
				.build();	
		
		GreedyOnGraph<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> rr = GreedyOnGraph.of(graph);
		
		GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> path = rr.path();
		Double bv = path.getWeight();
		
		DPR<TyPVertex,SimpleEdgeAction<TyPVertex,Integer>,?> ms = 
				DPR.of(graph,null,					
						bv,path,true);
		
		ms.search();
//		System.out.println(ms.search());
//		System.out.println(ms.solutionsTree);
		GraphPath<TyPVertex,SimpleEdgeAction<TyPVertex,Integer>> s1 = ms.search().get();
//		System.out.println(s1);
		SolucionTyP s = SolucionTyP.of(s1);
		System.out.println(s);
		
	}

}