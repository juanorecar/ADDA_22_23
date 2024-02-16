package ejercicios.tests.manual;

import java.util.List;

import _datos.DatosEjercicioCursos;
import _utils.TestsPI5;
import ejercicio2.manual.CursoPDR;
import us.lsi.common.String2;

public class TestEjercicioCursosPDR {

	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
		DatosEjercicioCursos.iniDatos("ficheros/Ejercicio2DatosEntrada"+num_test+".txt");
		String2.toConsole("Solucion obtenida: %s\n",
		CursoPDR.search());
		TestsPI5.line("*");
		});
	}
}
	

