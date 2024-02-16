package ejercicios.tests.manual;

import java.util.List;

import _datos.DatosEjercicioCafes;
import _utils.TestsPI5;
import ejercicio1.manual.CafePDR;
import us.lsi.common.String2;

public class TestEjercicioCafesPDR {

	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
		
		DatosEjercicioCafes.iniDatos("ficheros/Ejercicio1DatosEntrada"+num_test+".txt");
		String2.toConsole("Solucion obtenida: %s\n",
		CafePDR.search());
		TestsPI5.line("*");
		});
		} 
}
