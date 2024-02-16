package tests;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;
import datos.Ciudad;
import datos.Trayecto;
import ejercicios.Ejercicio2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio2 {

	public static void main(String[] args) {
		testApartadoA("2");
		testApartadoB("2");
		testApartadoC("2");
		testApartadoD("2");
	}
	public static Graph<Ciudad, Trayecto> grafoSimple(String file) {
		Graph<Ciudad, Trayecto> g = GraphsReader.newGraph("ficheros/PI3E" + file + "_DatosEntrada.txt", 
				Ciudad::ofFormat,
				Trayecto::ofFormat,
				Graphs2::simpleGraph);
		return g;
	}
	public static SimpleWeightedGraph<Ciudad, Trayecto> grafoSimplePeso(String file) {
		SimpleWeightedGraph<Ciudad, Trayecto> g = GraphsReader.newGraph("ficheros/PI3E" + file + "_DatosEntrada.txt", 
				Ciudad::ofFormat,
				Trayecto::ofFormat,
				Graphs2::simpleWeightedGraph);
		return g;
	}
	public static void testApartadoA(String file) {
		Graph<Ciudad,Trayecto> g = grafoSimple(file);
		Ejercicio2.apartadoA(g, file, "Apartado A");
	}
	
	public static void testApartadoB(String file) {
		Graph<Ciudad, Trayecto> g = grafoSimple(file);
		Ejercicio2.apartadoB(g, file, "Apartado B");
	}
	
	public static void testApartadoC(String file) {
		SimpleWeightedGraph<Ciudad, Trayecto> g = grafoSimplePeso(file);
		Ejercicio2.apartadoC(g, file, "Apartado C");
	}

	public static void testApartadoD(String file) {
		SimpleWeightedGraph<Ciudad, Trayecto> g = grafoSimplePeso(file);
		Ejercicio2.apartadoD(g, file, "Apartado D");
	}
}
