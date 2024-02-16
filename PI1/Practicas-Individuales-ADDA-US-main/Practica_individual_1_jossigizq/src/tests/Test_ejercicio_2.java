package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ejercicios.Ejercicio2;
import records.Linea2;
import us.lsi.iterables.IteratorFile;

public class Test_ejercicio_2 {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\aaa\\ADDA-workspace\\Practica_individual_1_jossigizq\\Ficheros\\PI1Ej2DatosEntrada.txt";
		Test_1(ruta);
		Test_2(ruta);
		
	}
	
	public static List<Linea2> Lectura2(String file){
		Iterator<String> it = new IteratorFile(file);
		List<Linea2> res = new ArrayList<>();
		while(it.hasNext()) {
			Linea2 linea = Linea2.parse(it.next());
			res.add(linea);
		}
		return res;
	}
	
	public static void Test_1(String file) {
		
		List<Linea2> lectura = Lectura2(file);
		Integer i = 1;
		Integer j = 1;
		Integer k = 1;
		Integer w = 1;
		
		System.out.println("Ejercicio2.solucion_recursiva_no_final");
		for(Linea2 linea: lectura) {
			String s = String.format(" # Test %d : %d", i, Ejercicio2.solucion_recursiva_no_final(linea.a(),linea.b(),linea.s()));
			System.out.println(s);
			i++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio2.solucion_recursiva_final");
		for(Linea2 linea: lectura) {
			String s = String.format(" # Test %d : %d", j, Ejercicio2.solucion_recursiva_final(linea.a(),linea.b(),linea.s()));
			System.out.println(s);
			j++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio2.solucion_iterativa");
		for(Linea2 linea: lectura) {
			String s = String.format(" # Test %d : %d", k, Ejercicio2.solucion_iterativa(linea.a(),linea.b(),linea.s()));
			System.out.println(s);
			k++;
		}
		System.out.println("---------------------------------\n");
		
		System.out.println("Ejercicio2.solucion_funcional");
		for(Linea2 linea: lectura) {
			String s = String.format(" # Test %d : %d", w, Ejercicio2.solucion_funcional(linea.a(),linea.b(),linea.s()));
			System.out.println(s);
			w++;
		}
		System.out.println("---------------------------------\n");
		
	}
	
	public static void Test_2(String file) {
		
		List<Linea2> lectura = Lectura2(file);
		Integer i = 1;
		
		for(Linea2 linea: lectura) {
			Integer e0 = Ejercicio2.solucion_recursiva_no_final(linea.a(), linea.b(), linea.s());
			Integer e1 = Ejercicio2.solucion_recursiva_final(linea.a(), linea.b(), linea.s());
			Integer e2 = Ejercicio2.solucion_iterativa(linea.a(), linea.b(), linea.s());
			Integer e3 = Ejercicio2.solucion_funcional(linea.a(), linea.b(), linea.s());
			if(e0.equals(e1) && e0.equals(e2) && e0.equals(e3)) {
				String s = String.format(" # Test %d : Los 3 algoritmos obtienen la misma solucion", i);
				System.out.println(s);
			} else {
				String s = String.format(" # Test %d : Los 3 algoritmos # NO # obtienen la misma solucion", i);
				System.out.println(s);
			}
		}
		
	}

}
