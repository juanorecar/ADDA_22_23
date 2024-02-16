package tests;

import java.util.*;

import ejercicios.Ejercicio3;


public class Test_ejercicio_3 {

	public static void main(String[] args) {
		
		String ruta1 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada1A.txt";
		String ruta2 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada1B.txt";
		List<String> lista12 = List.of(ruta1,ruta2);
		
		String ruta3 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada2A.txt";
		String ruta4 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada2B.txt";
		List<String> lista34 = List.of(ruta3,ruta4);
		
		String ruta5 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada3A.txt";
		String ruta6 = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej3DatosEntrada3B.txt";
		List<String> lista56 = List.of(ruta5,ruta6);
		
		Map<Integer, List<String>> data = Map.of(0, lista12, 1, lista34, 2, lista56);
		
		for(Integer key: data.keySet()) { // ¿¿¿ Los valores rotan ???
			System.out.println(data.get(key));
		}
		
		Test_1(data);
		
	}
	
	public static void Test_1(Map<Integer, List<String>> data) {
		
		Integer i = 1, j = 1, k = 1;
		
		System.out.println("Ejercicio3.solucion_iterativa");
		for(Integer key: data.keySet()) {
			String s = String.format(" # Test %d : %s", i, Ejercicio3.solucion_iterativa(data.get(key).get(0), data.get(key).get(1)));
			System.out.println(s);
			i++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio3.solucion_recursiva_final");
		for(Integer key: data.keySet()) {
			String s = String.format(" # Test %d : %s", j, Ejercicio3.solucion_recursiva_final(data.get(key).get(0), data.get(key).get(1)));
			System.out.println(s);
			j++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio3.solucion_recursiva_final");
		for(Integer key: data.keySet()) {
			String s = String.format(" # Test %d : %s", k, Ejercicio3.solucion_funcional(data.get(key).get(0), data.get(key).get(1)));
			System.out.println(s);
			k++;
		}
		System.out.println("---------------------------------\n");
		
	}

	

}
