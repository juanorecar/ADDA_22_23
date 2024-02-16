package tests;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import ejercicios.Ejercicio3;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;

public class TestEjercicio3 {

	public static void main(String[] args) {
		testApartadoA("3A");
		testApartadoA("3B");
		testsApartadoB("3A");
		testsApartadoB("3B");
	}
	
	public static Graph<String, DefaultEdge> grafo(String file){
		Graph<String, DefaultEdge> g = Graphs2.simpleGraph(String::new, DefaultEdge::new, false);
		
		Files2.streamFromFile("ficheros/PI3E" + file + "_DatosEntrada.txt").forEach(linea -> {
			String[] lineaSinPuntos = linea.split(":");
			String[] lineaSinComas = lineaSinPuntos[1].replace(" ", "").split(",");
			for(int i=0; i<lineaSinComas.length; i++) {
				for(int j = i +1; j<lineaSinComas.length; j++) {
					g.addVertex(lineaSinComas[i]);
					g.addVertex(lineaSinComas[j]);
					g.addEdge(lineaSinComas[i], lineaSinComas[j]);
				}
			}
			
		});
		return g;
	}
	
	public static void testApartadoA(String file) {
		Graph<String, DefaultEdge> g = grafo(file);
		Ejercicio3.apartadoA(g, file);
	}
	
	public static void testsApartadoB(String file) {
		
		Graph<String, DefaultEdge> g = grafo(file);
		Ejercicio3.apartadoB(g, file, "Apartado B");
	}

}
