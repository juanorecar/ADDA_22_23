package ejercicios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Ciudad;
import datos.Trayecto;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Trio;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio2 {
	
	public static List<Set<Ciudad>> componentesConexas(Graph<Ciudad, Trayecto> gf){
		var cc = new ConnectivityInspector<>(gf);
		List<Set<Ciudad>> componentes = cc.connectedSets();
		return componentes;
	}
	
	public static Boolean esConexo(Graph<Ciudad, Trayecto> gf) {
		var ec = new ConnectivityInspector<>(gf);
		Boolean conexo = ec.isConnected();
		return conexo;
	}
	
	private static Color asignaColor(Ciudad v, List<Set<Ciudad>> ls, ConnectivityInspector<Ciudad, Trayecto> alg) {
		Color[] vc = Color.values();
		Set<Ciudad> s = alg.connectedSetOf(v);
		return vc[ls.indexOf(s)];
	}
	
	public static void apartadoA(Graph<Ciudad, Trayecto> gf, String file, String nombreVista) {
		var esConexo = esConexo(gf);
		var cc = new ConnectivityInspector<>(gf);
		if(esConexo) {
			System.out.println("Solo hay un grupo de ciudades");
		}else {
			System.out.println("Numero de grupos de ciudades: " + componentesConexas(gf).size());
			for(int i=0; i<componentesConexas(gf).size(); i++) {
				System.out.println("\n Grupo numero " + (i+1) + ":" + componentesConexas(gf).get(i));
			}
		}
		String fileRes = "resultados/ejercicio2/" + file + nombreVista + ".gv";
		GraphColors.toDot(gf, fileRes,c->"", v->"", 
				v -> GraphColors.color(asignaColor(v, componentesConexas(gf), cc)),
				e -> GraphColors.color(asignaColor(gf.getEdgeSource(e), componentesConexas(gf),cc)));
		
	}
	
	
	public static Set<Ciudad> setApartadoB(Graph<Ciudad, Trayecto> gf) {
		List<Set<Ciudad>> lsComponentes = componentesConexas(gf);
		Integer maximo = 0;
		Set<Ciudad> res = new HashSet<>();
		for(int i=0; i<lsComponentes.size(); i++) {
				Integer suma = lsComponentes.get(i).stream()
						.mapToInt(Ciudad::puntuacion).sum();
				if(suma>maximo) {
					maximo = suma;
					res = lsComponentes.get(i);
				}
		}
		
		return res;
	}

	
	public static void apartadoB(Graph<Ciudad, Trayecto> gf, String file, String nombreVista) {
		Set<Ciudad> set = setApartadoB(gf);
		Graph<Ciudad, Trayecto> g = SubGraphView.of(gf, set);
		String fileRes = "resultados/ejercicio2/" + file + nombreVista + ".gv";
		GraphColors.toDot(g, fileRes,
				v->v.nombre(), 
				e->"",
				v->GraphColors.colorIf(Color.blue, g.containsVertex(v)),
				e->GraphColors.colorIf(Color.blue, g.containsEdge(e)));
		System.out.println("Se ha generado" + fileRes);
	}
	
	
	
	public static void apartadoC(SimpleWeightedGraph<Ciudad, Trayecto> gf, String file, String nombreVista) {
		
			ConnectivityInspector<Ciudad, Trayecto> cc = new ConnectivityInspector<>(gf);
			HeldKarpTSP<Ciudad, Trayecto> camino = new HeldKarpTSP<>();
			Trio<List<Ciudad>, List<Trayecto>, Double> trio = Trio.of(null, null, Double.MAX_VALUE);
			
			for(Set<Ciudad> grupo:cc.connectedSets()) {
				Graph<Ciudad, Trayecto> vista = SubGraphView.of(gf, grupo);
				List<Trayecto> lsTrayectos = camino.getTour(vista).getEdgeList();
				List<Ciudad> lsCiudades = camino.getTour(vista).getVertexList();
				Double totalPrecio = lsTrayectos.stream().mapToDouble(x->x.precio()).sum();
				if(totalPrecio<trio.third()) {
					trio = Trio.of(lsCiudades, lsTrayectos, totalPrecio);
				}
			}
			
			List<Ciudad> mejoresCiudades = trio.first();
			List<Trayecto> mejoresTrayectos = trio.second();
			
			String fileRes = "resultados/ejercicio2/" + file + nombreVista + ".gv";
			GraphColors.toDot(gf, fileRes,
					v->v.nombre(), 
					e->"",
					v->GraphColors.colorIf(Color.blue, mejoresCiudades.contains(v)),
					e->GraphColors.colorIf(Color.blue, mejoresTrayectos.contains(e)));
			System.out.println("Se ha generado" + fileRes);
	}
	
	
	public static void apartadoD(Graph<Ciudad, Trayecto> gf, String file, String nombreVista) {
		List<Set<Ciudad>> lsComponentes = componentesConexas(gf);
		gf.edgeSet().forEach(e-> gf.setEdgeWeight(e, e.duracion()));
		for(int i=0; i<lsComponentes.size(); i++) {
			Graph<Ciudad, Trayecto> componente = SubGraphView.of(gf,lsComponentes.get(i));
			List<Ciudad> ls = componente.vertexSet().stream().toList();
			Double minimo = Double.POSITIVE_INFINITY;
			GraphPath<Ciudad, Trayecto> res = null;
			for(int j=0; j<ls.size(); j++) {
				for(int k=j+1; k<ls.size(); k++) {
					Ciudad vertice1 = ls.get(j);
					Ciudad vertice2 = ls.get(k);
					DijkstraShortestPath<Ciudad, Trayecto> dj = new DijkstraShortestPath<>(componente);
					GraphPath<Ciudad, Trayecto> camino = dj.getPath(vertice1, vertice2);
					if(camino.getLength()>= 2) {
						if(camino.getWeight()<minimo) {
							minimo = camino.getWeight();
							res = camino;
						}
				}
			}				
			}
			System.out.println("Para el grupo: " + componente.vertexSet() + ", las ciudades no conectadas directamente entre las que se puede viajar en menor tiempo son:\r\n"
					+ "Origen: " + res.getStartVertex() + " y Destino: " + res.getEndVertex());
			
			List<Ciudad> lsVertices = res.getVertexList();
			List<Trayecto> lsAristas = res.getEdgeList();
			String fileRes = "resultados/ejercicio2/" + file + nombreVista + ".gv";
			GraphColors.toDot(gf, fileRes,
					v->v.nombre(), 
					e->e.precio().toString() + " euros",
					v->GraphColors.styleIf(Style.bold, lsVertices.contains(v)),
					e->GraphColors.styleIf(Style.bold, lsAristas.contains(e)));
			System.out.println("Se ha generado" + fileRes);
		}
		
		
	}	
	
}
