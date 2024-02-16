package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import datos.Familia;
import datos.Persona;
import datos.Relacion;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {
	
	public static void apartadoA(String file, Graph<Persona, Familia> g, 
			Predicate<Persona> pv, String nombreVista) {
		
		Graph<Persona, Familia> vista = SubGraphView.of(g, pv, null);
		Set<Persona> set = vista.vertexSet();
		List<Persona> personas = set.stream().toList();
		List<String> ls = new ArrayList<>();
		for(int i=0; i<personas.size(); i++) {
			ls.add(personas.get(i).nombre());
		}
		System.out.println("Personas cuyos padres aparecen en el grafo y cumplen los requisitos: " + ls);
		String fileRes = "resultados/ejercicio1/" + file + nombreVista + ".gv";
		GraphColors.toDot(vista, fileRes,
				v->v.nombre(), 
				e->"",
				v->GraphColors.colorIf(Color.red, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.black, vista.containsEdge(e)));
		System.out.println("Se ha generado" + fileRes);
	
	}
	
	public static void apartadoB(String file, SimpleDirectedGraph<Persona, Familia> g, String p, String nombreVista) {
		Graph<Persona, Familia> grafo = Graphs2.inversedDirectedGraph(g);
		Persona persona = g.vertexSet().stream().filter(v->v.nombre().equals(p)).findFirst().get(); //Encontrar a dicha persona en el grafo
		List<Persona> ancestros = new ArrayList<>();
		DepthFirstIterator<Persona, Familia> dfi = new DepthFirstIterator<>(grafo, persona);
		dfi.forEachRemaining(v->ancestros.add(v));
		Map<Persona, Color> m = new HashMap<>();
		for(Persona person: g.vertexSet()) {
			if(person.nombre().equals(p)) {
				m.put(person, Color.red);
			}else if(ancestros.contains(person)) {
				m.put(person, Color.blue);
			}else {
				m.put(person, Color.black);
			}
		}
		List<String> ls = new ArrayList<>();
		for(int i=0; i<ancestros.size(); i++) {
			ls.add(ancestros.get(i).nombre());
		}
		System.out.println("Ancestros de " + p + ": "+ ls);
		String fileRes = "resultados/ejercicio1/" + file + nombreVista + ".gv";
		GraphColors.toDot(g, fileRes,
				v->v.nombre(), 
				e->"",
				v->GraphColors.color(m.get(v)),
				e->GraphColors.color(Color.black));
		System.out.println("Se ha generado" + fileRes);
	}
	
	
	public static void apartadoC(String file, Graph<Persona, Familia> g, String p1, String p2) {
		Persona persona1 = g.vertexSet().stream().filter(v->v.nombre().equals(p1)).findFirst().get();
		Persona persona2 = g.vertexSet().stream().filter(v->v.nombre().equals(p2)).findFirst().get();
		DijkstraShortestPath<Persona, Familia> circuito = new DijkstraShortestPath<>(g);
		Integer longitud = circuito.getPath(persona1, persona2).getLength();
		if(longitud==2) {
			System.out.println(persona1.nombre() + " y " + persona2.nombre() + " son " + Relacion.HERMANOS);
		}else if(longitud == 4) {
			System.out.println(persona1.nombre() + " y " + persona2.nombre() + " son " + Relacion.PRIMOS);
		}else {
			System.out.println(persona1.nombre() + " y " + persona2.nombre() + " son " + Relacion.OTROS);
		}
	}
	
	
	public static void apartadoD(String file, Graph<Persona, Familia> g, 
			Predicate<Persona> pv, String nombreVista) {
		Graph<Persona, Familia> vista = SubGraphView.of(g, pv, null);
		Set<Persona> set = vista.vertexSet();
		List<Persona> personas = set.stream().toList();
		List<String> ls = new ArrayList<>();
		for(int i=0; i<personas.size(); i++) {
			ls.add(personas.get(i).nombre());
		}
		System.out.println("Personas que tienen hijos/as con distintas personas: " + ls);
		String fileRes = "resultados/ejercicio1/" + file + nombreVista + ".gv";
		GraphColors.toDot(vista, fileRes,
				v->v.nombre(), 
				e->"",
				v->GraphColors.colorIf(Color.red, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.black, vista.containsEdge(e)));
		System.out.println("Se ha generado" + fileRes);
	}
	
	
	public static void apartadoE(String file, Graph<Persona, Familia> g, String nombreVista) {
		GreedyVCImpl<Persona, Familia> vCover= new GreedyVCImpl<>(g);
		Set<Persona> personas = vCover.getVertexCover();
		String fileRes = "resultados/ejercicio1/" + file + nombreVista + ".gv";
		GraphColors.toDot(g, fileRes,
				v->v.nombre(), 
				e->"",
				v->GraphColors.colorIf(Color.red, personas.contains(v)),
				e->GraphColors.style(Style.solid));
		System.out.println("Se ha generado" + fileRes);
	}
}
