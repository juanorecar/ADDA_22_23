package ejercicios.tests;

import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioClientes;
import _soluciones.SolucionClientes;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicio4.ClienteVertex;

public class TestClientes {

	public static void main(String[] args) {
		List.of(1,2).forEach(num_test -> {
			
			TestsPI5.iniTest("Ejercicio4DatosEntrada", num_test, DatosEjercicioClientes::iniDatos);
			
			// TODO Defina un m. factoria para el vertice inicial
			ClienteVertex v_inicial = ClienteVertex.initial();
			// TODO Defina un m. static para los vertices finales 
			Predicate<ClienteVertex> es_terminal = ClienteVertex.goal();
			
			var path = TestsPI5.testAStar(GraphsPI5.ejercicio4Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("A*", path, SolucionClientes::of);
			
			path = TestsPI5.testPDR(GraphsPI5.ejercicio4Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("PDR", path, SolucionClientes::of);
			
			path = TestsPI5.testBT(GraphsPI5.ejercicio4Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("BT", path, SolucionClientes::of);
			
			TestsPI5.line("*");
			
			
		});
	}
	
}
