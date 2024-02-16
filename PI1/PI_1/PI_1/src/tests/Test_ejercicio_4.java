package tests;

import java.util.*;

import ejercicios.Ejercicio4;
import records.Tupla4;
import us.lsi.iterables.IteratorFile;

public class Test_ejercicio_4 {

	public static void main(String[] args) {

		String ruta = "C:\\Users\\aaa\\ADDA-workspace\\PI_1\\Ficheros\\PI1Ej4DatosEntrada.txt";
		List<Tupla4> data = Lectura4(ruta);
		Test_1(data);
		Test_2(data);

	}
	
	public static List<Tupla4> Lectura4(String file) {
		
		Iterator<String> it = new IteratorFile(file);
		List<Tupla4> res = new ArrayList<>();
		
		while(it.hasNext()) {
			String linea = it.next();
			Tupla4 t = Tupla4.parse(linea);
			res.add(t);
		}
		
		return res;
	}
	
	public static void Test_1(List<Tupla4> data) {
		
		Integer i = 1, j = 1, k = 1;
		
		System.out.println("Ejercicio4.solucion_recursiva_multiple_sin_memoria");
		for(Tupla4 t: data) {
			String s = String.format(" # Test %d : %s", i, Ejercicio4.solucion_recursiva_multiple_sin_memoria(t.a(), t.b(), t.c()));
			System.out.println(s);
			i++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio4.solucion_recursiva_multiple_con_memoria");
		for(Tupla4 t: data) {
			String s = String.format(" # Test %d : %s", j, Ejercicio4.solucion_recursiva_multiple_con_memoria(t.a(), t.b(), t.c()));
			System.out.println(s);
			j++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio4.solucion_iterativa");
		for(Tupla4 t: data) {
			String s = String.format(" # Test %d : %s", k, Ejercicio4.solucion_iterativa(t.a(), t.b(), t.c()));
			System.out.println(s);
			k++;
		}
		System.out.println("---------------------------------\n");
	}
	
	public static void Test_2(List<Tupla4> data) {
		
		Integer i = 1;
		//String aux = null;
		
		for(Tupla4 t: data) {
			String s1 = Ejercicio4.solucion_recursiva_multiple_sin_memoria(t.a(), t.b(), t.c());
			String s2 = Ejercicio4.solucion_recursiva_multiple_con_memoria(t.a(), t.b(), t.c());
			String s3 = Ejercicio4.solucion_iterativa(t.a(), t.b(), t.c());
			if(s1.equals(s2) && s1.equals(s3)) {
				String aux1 = String.format(" # Test %d : Los 3 algoritmos obtienen la misma solucion", i);
				System.out.println(aux1);
			} else {
				String aux2 = String.format(" # Test %d : Los 3 algoritmos # NO # obtienen la misma solucion", i);
				System.out.println(aux2);
			}
			
			i++;
		}
		
	}

}
