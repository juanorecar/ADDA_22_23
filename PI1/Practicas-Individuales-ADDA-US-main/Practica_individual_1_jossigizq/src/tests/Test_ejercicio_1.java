package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ejercicios.Ejercicio1;
import records.Linea1;
import us.lsi.iterables.IteratorFile;

public class Test_ejercicio_1 {

	public static void main(String[] args) {
		
		// Ruta ==> fichero ==> boton derecho ==> properties ==> location
		String ruta = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej1DatosEntrada.txt";
		Test_1(ruta);
		Test_2(ruta);
		

	}
	
	/*
	 * Funcion de lectura + test
	 */
	
	public static List<Linea1> Lectura1(String file){
		Iterator<String> it = new IteratorFile(file); //us.lsi.iterables.IteratorFile;
		List<Linea1> res = new ArrayList<>();
		
		while(it.hasNext()) {
			Linea1 t = Linea1.parse(it.next());
			res.add(t);
		}
		return res;
	}
	
	public static void Test_1(String file) {
		
		List<Linea1> datosEntrada = Lectura1(file);
		
		System.out.println("Ejercicio1.solucion_funcional");
		for(int i = 0; i < datosEntrada.size(); i++) { // ==> for extendido ###
			Linea1 linea = datosEntrada.get(i);
			System.out.println(" # Test " + (i+1) + ": " + Ejercicio1.solucion_funcional(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE()));
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio1.solucion_iterativa");
		for(int i = 0; i < datosEntrada.size(); i++) {
			Linea1 linea = datosEntrada.get(i);
			System.out.println(" # Test " + (i+1) + ": " + Ejercicio1.solucion_iterativa(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE()));
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio1.solucion_recursiva_final");
		for(int i = 0; i < datosEntrada.size(); i++) {
			Linea1 linea = datosEntrada.get(i);
			System.out.println(" # Test " + (i+1) + ": " + Ejercicio1.solucion_recursiva_final(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE()));
		}
		System.out.println("---------------------------------");
	}
	
	public static void Test_2(String file) {
		
		List<Linea1> datosEntrada = Lectura1(file);
		
		for(int i = 0; i < datosEntrada.size(); i++) {
			Linea1 linea = datosEntrada.get(i);
			Map<Integer, List<String>> map1 = Ejercicio1.solucion_funcional(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
			Map<Integer, List<String>> map2 = Ejercicio1.solucion_iterativa(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
			Map<Integer, List<String>> map3 = Ejercicio1.solucion_recursiva_final(linea.varA(), linea.varB(), linea.varC(), linea.varD(), linea.varE());
			if(map1.equals(map2) && map1.equals(map3)) {
				System.out.println(" # Test " + (i+1) + ": Los 3 algoritmos obtienen la misma solucion");
			} else {
				System.out.println(" # Test " + (i+1) + ": Los 3 algoritmos # NO # obtienen la misma solucion");
			}
		}
		System.out.println("---------------------------------\n");
	}

}
