package datos;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utiles.Cliente;
import utiles.Trayecto;

public class DatosEjercicioClientes {
	
	@SuppressWarnings("exports") // PARA SUPRIMIR EL AVISO Y PERMITIR EXPORTACION SIN TENER QUE PONERLO EN EL MODULE INFO
	public static Graph<Cliente, Trayecto> gf;
	
	public static void iniDatos(String fichero) {
		gf = GraphsReader.newGraph(fichero, Cliente::ofFormat, Trayecto::ofFormat, Graphs2::simpleWeightedGraph);
		toConsole();
	}
	
	public static Integer getNVertices() {
		return gf.vertexSet().size();
	}
	
	@SuppressWarnings("exports")
	public static Cliente getCliente(Integer i) {
		Cliente client = null;
		List<Cliente> vs = new ArrayList<>(gf.vertexSet());
		for (int k = 0; k < vs.size(); k++) {
			if (vs.get(k).id() == i) {
				client = vs.get(k);
			}
		}
		return client;
	}
	
	public static Double getBeneficio(Integer i) {
		Cliente client = getCliente(i);
		return client.beneficio();
	}
	
	public static Double getPeso(Integer i, Integer j) {
		Cliente cliente1 = getCliente(i);
		Cliente cliente2 =  getCliente(j);
		return gf.getEdge(cliente1, cliente2).distancia();
	}
	
	public static Boolean existeArista(Integer i, Integer j) {
		Cliente client1 = getCliente(i);
		Cliente client2 =  getCliente(j);
		return gf.containsEdge(client1, client2);
	}
	
	public static void toConsole() {
		System.out.println("Numero de vertices: " + gf.vertexSet().size() + "\n\tVertices: " + gf.vertexSet()
		+ "\nNumero de aristas: " + gf.edgeSet().size() + "\n\tAristas: " + gf.edgeSet());
	}

	public static void main(String[] args) {
		for (int i = 1; i < 3; i++) {
			System.out.println("\n######################## DATOS FICHERO " + i + " ########################\n");
			String fich = "ficheros/Ejercicio4DatosEntrada" + i + ".txt";
			iniDatos(fich);
			System.out.println("\n");
		}
	}

}
