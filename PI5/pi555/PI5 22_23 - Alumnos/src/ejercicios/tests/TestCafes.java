package ejercicios.tests;

import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioCafes;
import _soluciones.SolucionCafe;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicio1.CafeVertex;

public class TestCafes {
	
	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			
			TestsPI5.iniTest("Ejercicio1DatosEntrada", num_test, DatosEjercicioCafes::iniDatos);
			
			// TODO Defina un m. factoria para el vertice inicial
			CafeVertex v_inicial = CafeVertex.initial();
			// TODO Defina un m. static para los vertices finales 
			Predicate<CafeVertex> es_terminal = CafeVertex.goal();
	
			var path = TestsPI5.testAStar(GraphsPI5.ejercicio1Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("A*", path, SolucionCafe::of);
			
			path = TestsPI5.testPDR(GraphsPI5.ejercicio1Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("PDR", path, SolucionCafe::of);
			
			path = TestsPI5.testBT(GraphsPI5.ejercicio1Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("BT", path, SolucionCafe::of);
			
			TestsPI5.line("*");
		});
	}
}
