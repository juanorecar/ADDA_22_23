package records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Linea1(Integer varA, String varB, Integer varC, 
		  String varD, Integer varE) {
	
	/*
	 * linea_ejercicio_1 se utiliza para parsear cada linea del fichero de entrada
	 * de datos en la funcion de lectura
	 */
		
	public static Linea1 of(Integer varA, String varB, Integer varC, 
			  String varD, Integer varE) {
		
		return new Linea1(varA, varB, varC, varD, varE);
		
	}
		
	public static Linea1 of1(String varA, String varB, String varC, 
			  String varD, String varE) {
		
		return new Linea1(Integer.parseInt(varA), 
				varB, 
				Integer.parseInt(varC), 
				varD, 
				Integer.parseInt(varE));
		
	}
		
	public static Linea1 parse(String linea) {
		List<String> aux = Arrays.stream(linea.split(",")).collect(Collectors.toList());
		return Linea1.of1(aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4));
	}

}
