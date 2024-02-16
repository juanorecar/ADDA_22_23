package _utils;

import java.util.function.Predicate;

import ejemplos.ejemplo1.MulticonjuntoEdge;
import ejemplos.ejemplo1.MulticonjuntoHeuristic;
import ejemplos.ejemplo1.MulticonjuntoVertex;
import ejemplos.ejemplo2.SubconjuntosEdge;
import ejemplos.ejemplo2.SubconjuntosHeuristic;
import ejemplos.ejemplo2.SubconjuntosVertex;
import ejemplos.ejemplo3.AlumnosEdge;
import ejemplos.ejemplo3.AlumnosVertex;
import ejercicio1.CafeEdge;
import ejercicio1.CafeHeuristic;
import ejercicio1.CafeVertex;
import ejercicio2.CursoEdge;
import ejercicio2.CursoHeuristic;
import ejercicio2.CursoVertex;
import ejercicio4.ClienteEdge;
import ejercicio4.ClienteHeuristic;
import ejercicio4.ClienteVertex;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

// Clase Factoria para crear los grafos de los ejemplos y ejercicios
public class GraphsPI5 {
	
	// EJERCICIO 1
	public static EGraph<CafeVertex, CafeEdge>
	ejercicio1Grafo(CafeVertex v_inicial, Predicate<CafeVertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
//				.goalHasSolution(Ejercicio1Vertex.goalHasSolution())
				.heuristic(CafeHeuristic::heuristic).build();
	}
	
	// EJERCICIO 2
	public static EGraph<CursoVertex, CursoEdge>
	ejercicio2Grafo(CursoVertex v_inicial, Predicate<CursoVertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Min)
				.goalHasSolution(CursoVertex.goalHasSolution())
				.heuristic(CursoHeuristic::heuristic).build();
	}
	
	//	EJERCICIO 4
	public static EGraph<ClienteVertex, ClienteEdge>
	ejercicio4Grafo(ClienteVertex v_inicial, Predicate<ClienteVertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
				.goalHasSolution(ClienteVertex.goalHasSolution())
				.heuristic(ClienteHeuristic::heuristic).build();
	}
	
	// Ejemplo1: Grafo NO Greedy
	public static EGraph<MulticonjuntoVertex, MulticonjuntoEdge>
	multisetGraph(MulticonjuntoVertex v_inicial, Predicate<MulticonjuntoVertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Min)
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
	}
	
	// Ejemplo1: Grafo Greedy
	public static EGraph<MulticonjuntoVertex, MulticonjuntoEdge> 
	greedyMultisetGraph(MulticonjuntoVertex v_inicial, Predicate<MulticonjuntoVertex> es_terminal) { 
		return EGraph.virtual(v_inicial,es_terminal,PathType.Sum, Type.Min)
				.greedyEdge(MulticonjuntoVertex::greedyEdge)
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
	}

	// Ejemplo2: Grafo NO Greedy
	public static EGraph<SubconjuntosVertex, SubconjuntosEdge>
	subsetGraph(SubconjuntosVertex v_inicial, Predicate<SubconjuntosVertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Min)
				.goalHasSolution(SubconjuntosVertex.goalHasSolution())
				.heuristic(SubconjuntosHeuristic::heuristic).build();
	}
	
	// Ejemplo2: Grafo Greedy
	public static EGraph<SubconjuntosVertex, SubconjuntosEdge> 
	greedySubsetGraph(SubconjuntosVertex v_inicial, Predicate<SubconjuntosVertex> es_terminal) { 
		return EGraph.virtual(v_inicial,es_terminal,PathType.Sum, Type.Min)
				.greedyEdge(SubconjuntosVertex::greedyEdge)
				.goalHasSolution(SubconjuntosVertex.goalHasSolution())
				.heuristic(SubconjuntosHeuristic::heuristic).build();
	}

	// Ejemplo3: Grafo NO Greedy
	public static EGraph<AlumnosVertex, AlumnosEdge>
	academyGraph(AlumnosVertex v_inicial, Predicate<AlumnosVertex> es_terminal) {
		// TODO Implementarlo de forma similar a los de los ejemplos 1 y 2
		return null;
	}
	
	// Ejemplo3: Grafo Greedy
	public static EGraph<AlumnosVertex, AlumnosEdge> 
	greedyAcademyGraph(AlumnosVertex v_inicial, Predicate<AlumnosVertex> es_terminal) {
		// TODO Implementarlo de forma similar a los greedy de los ejemplos 1 y 2
		return null;
	}
	
}
