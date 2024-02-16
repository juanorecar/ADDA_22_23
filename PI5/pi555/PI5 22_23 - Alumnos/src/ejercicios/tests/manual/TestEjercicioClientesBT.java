package ejercicios.tests.manual;

import java.util.List;
import _datos.DatosEjercicioClientes;
import _utils.TestsPI5;
import ejercicio4.manual.ClientesBT;
import us.lsi.common.String2;

public class TestEjercicioClientesBT {
	public static void main(String[] args) {
		List.of(1,2).forEach(num_test -> {
		
		DatosEjercicioClientes.iniDatos("ficheros/Ejercicio4DatosEntrada"+num_test+".txt");
		ClientesBT.search();
		ClientesBT.getSoluciones().forEach(s ->
		String2.toConsole("Solucion obtenida: %s\n", s));
		TestsPI5.line("*");
		});
		 
} }
