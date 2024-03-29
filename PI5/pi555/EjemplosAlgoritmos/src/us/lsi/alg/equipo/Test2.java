package us.lsi.alg.equipo;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
public class Test2 {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		
		DatosEquipo.iniDatos("ficheros/DatosEquipo3.txt");
		
		EquipoVertex v = EquipoVertex.first();
		
		EGraph<EquipoVertex, EquipoEdge> g = EGraph.virtual(EquipoVertex.first(),EquipoVertex::goal)
				.build();
		
		while (v.index()<DatosEquipo.M) {
			GreedyOnGraph<EquipoVertex, EquipoEdge> vr2 = GreedyOnGraph.random(g);	
			GraphPath<EquipoVertex, EquipoEdge> gp = vr2.path();
//			Double h = EquipoHeuristic.heuristica2(v, EquipoVertex::goal, null);
//			SolucionEquipo s = SolucionEquipo.create(gp.getVertexList());
//			System.out.printf("%d,%6.2f,%6.2f\n",v.index(),gp.getWeight(),h);
//			System.out.printf("%s\n",s);
			v = v.neighbor(v.mejorEnPosicion(v.index()).orElse(v.mejorEnPosicionSin(v.index()).get()));
		}
	}

}
