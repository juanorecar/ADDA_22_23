package ejercicios.tests;

import java.util.List;
import java.util.function.Predicate;
import _datos.DatosEjercicioCursos;
import _soluciones.SolucionCursos;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicio2.CursoVertex;


public class TestCursos {
	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			
			TestsPI5.iniTest("Ejercicio2DatosEntrada", num_test, DatosEjercicioCursos::iniDatos);
			
			// TODO Defina un m. factoria para el vertice inicial
			CursoVertex v_inicial = CursoVertex.initial();
			// TODO Defina un m. static para los vertices finales 
			Predicate<CursoVertex> es_terminal = CursoVertex.goal();

//			var gp = TestsPI5.testGreedy(GraphsPI5.greedyMultisetGraph(v_inicial, es_terminal));
//			TestsPI5.toConsole("Voraz", gp, SolucionEjercicio1::of);
			
			var path = TestsPI5.testAStar(GraphsPI5.ejercicio2Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("A*", path, SolucionCursos::of);
			
			path = TestsPI5.testPDR(GraphsPI5.ejercicio2Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("PDR", path, SolucionCursos::of);
			
			path = TestsPI5.testBT(GraphsPI5.ejercicio2Grafo(v_inicial, es_terminal), null);
			TestsPI5.toConsole("BT", path, SolucionCursos::of);
			
			TestsPI5.line("*");
		});
	}
}
