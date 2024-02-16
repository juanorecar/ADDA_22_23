package tests;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import datos.Familia;
import datos.Persona;
import ejercicios.Ejercicio1;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {

	public static void main(String[] args) {
		testApartadoA("1A");
		testApartadoA("1B");
		testApartadoB("1A", "Maria");
		testApartadoB("1B", "Raquel");
		testApartadoC("1A", "Rafael", "Sara");
		testApartadoC("1A", "Maria", "Patricia");
		testApartadoC("1A", "Carmen", "Rafael");
		testApartadoC("1B", "Julia", "Angela");
		testApartadoC("1B", "Alvaro", "Raquel");
		testApartadoC("1B", "Laura", "Raquel");
		testApartadoD("1A");
		testApartadoD("1B");
		testApartadoE("1A");
		testApartadoE("1B");
	}
	
	
	public static SimpleDirectedGraph<Persona, Familia> grafoDirigido(String file) {
		SimpleDirectedGraph<Persona, Familia> g = GraphsReader.newGraph("ficheros/PI3E" + file + "_DatosEntrada.txt", 
				Persona::ofFormat,
				Familia::ofFormat,
				Graphs2::simpleDirectedGraph);
		return g;
	}
	
	public static Graph<Persona, Familia> grafoSimple(String file) {
		Graph<Persona, Familia> g = GraphsReader.newGraph("ficheros/PI3E" + file + "_DatosEntrada.txt", 
				Persona::ofFormat,
				Familia::ofFormat,
				Graphs2::simpleGraph);
		return g;
	}
	

	
	public static void testApartadoA(String file) {
		SimpleDirectedGraph<Persona, Familia> g = grafoDirigido(file);
		Predicate<Persona> pv = v -> mismoAnyoYCiudad(g, g.incomingEdgesOf(v));
		Ejercicio1.apartadoA(file, g, pv, "Apartado A");
	}

	public static Boolean mismoAnyoYCiudad(Graph<Persona, Familia> g, Set<Familia> familia) {
		Set<String> ciudad = new HashSet<>();
		Set<Integer> anyo = new HashSet<>();
		if(familia.size()==2) { //si tiene padres
			for(Familia f: familia) {
				ciudad.add(g.getEdgeSource(f).ciudad_nacimiento()); //Ciudad de nacimiento de los padres
				anyo.add(g.getEdgeSource(f).anyo_nacimiento()); //Años de nacimiento de los padres
			}
			if(anyo.size() == 1 && ciudad.size() == 1) { //Al ser un set y no poder haber elementos repetidos, comprobamos si hay un solo elemento en cada set
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static void testApartadoB(String file, String nombre) {
		SimpleDirectedGraph<Persona, Familia> g= grafoDirigido(file);
		Ejercicio1.apartadoB(file, g, nombre, "Apartado B");
	}
	
	public static void testApartadoC(String file, String p1, String p2) {
		Graph<Persona,Familia> g = grafoSimple(file);
		Ejercicio1.apartadoC(file, g, p1, p2);
	}
	
	
	public static void testApartadoD(String file) {
		SimpleDirectedGraph<Persona, Familia> g = grafoDirigido(file);
		Predicate<Persona> pv = v->HijoConPadresDiferentes(g, v);
		Ejercicio1.apartadoD(file, g, pv, "Apartado D");
	}
	
	public static Boolean HijoConPadresDiferentes(SimpleDirectedGraph<Persona, Familia> gf, Persona p) {
		Set<Persona> conj = new HashSet<>();
		if(gf.outDegreeOf(p)>0) {
			for(Familia target: gf.outgoingEdgesOf(p)) {
				Persona hijo = gf.getEdgeTarget(target);
				for(Familia padre:gf.incomingEdgesOf(hijo)) {
					conj.add(gf.getEdgeSource(padre));
				}
			}
		}
		return conj.size()>2;
	}
	
	public static void testApartadoE(String file) {
		Graph<Persona, Familia> g = grafoSimple(file);
		Ejercicio1.apartadoE(file, g, "Apartado E");
	}
	
}
