package tests;

import java.util.*;

import ejercicios.Ejercicio3;


public class Test_ejercicio_3 {

	public static void main(String[] args) {
		
		String ruta1 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada1A.txt";
		String ruta2 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada1B.txt";
		List<String> lista12 = List.of(ruta1,ruta2);
		
		String ruta3 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada2A.txt";
		String ruta4 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada2B.txt";
		List<String> lista34 = List.of(ruta3,ruta4);
		
		String ruta5 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada3A.txt";
		String ruta6 = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej3DatosEntrada3B.txt";
		List<String> lista56 = List.of(ruta5,ruta6);
		
		Map<Integer, List<String>> data = Map.of(0, lista12, 1, lista34, 2, lista56);
		
		Test_1(data);
		
	}
	
	public static void Test_1(Map<Integer, List<String>> data) {
		
		Integer i2 = 1, j2 = 1, k2 = 1;
		
		System.out.println("Ejercicio3.solucion_iterativa");
		for(int i = 0; i < 3; i++) {
			String s = String.format(" # Test %d : %s", i2, Ejercicio3.solucion_iterativa(data.get(i).get(0), data.get(i).get(1)));
			System.out.println(s);
			i2++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio3.solucion_recursiva_final");
		for(int j = 0; j < 3; j++) {
			String s = String.format(" # Test %d : %s", j2, Ejercicio3.solucion_recursiva_final(data.get(j).get(0), data.get(j).get(1)));
			System.out.println(s);
			j2++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio3.solucion_funcional");
		for(int k = 0; k < 3; k++) {
			String s = String.format(" # Test %d : %s", k2, Ejercicio3.solucion_funcional(data.get(k).get(0), data.get(k).get(1)));
			System.out.println(s);
			k2++;
		}
		System.out.println("---------------------------------\n");
		
	}


}
