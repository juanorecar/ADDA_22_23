package tests;


import java.util.Comparator;
import java.util.List;
import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.common.Files2;

public class TestEjercicios {

	public static void main(String[] args) {
		
		 
		testEjercicio1();	  
		
		
		testEjercicio2();   
		
		
		testEjercicio3(); 	
		
		
		testEjercicio4();
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static record entradaFicheroEj1(Integer varA, String varB, Integer varC, String
			varD, Integer varE) {
		public static entradaFicheroEj1 of(Integer varA, String varB, Integer varC, String
				varD, Integer varE) {
			return new entradaFicheroEj1(varA, varB, varC, varD, varE);
		}
		
		private static entradaFicheroEj1 parseLinea(String linea) {
			String [] splitted = linea.split(",");
			return of(Integer.valueOf(splitted[0]),splitted[1].toString(),
					Integer.valueOf(splitted[2]), splitted[3].toString(), Integer.valueOf(splitted[4]));
			
	}

}
	public static void testEjercicio1() {
		String file = "ficheros/PI1Ej1DatosEntrada.txt";
		
		List<String> lineas = Files2.linesFromFile(file);
		List<entradaFicheroEj1> l = lineas.stream().map(linea -> entradaFicheroEj1.parseLinea(linea)).toList();
		System.out.println("\n EJERCICIO 1");
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 1 Iterativo");
		l.forEach(d->{
			System.out.println(Ejercicio1.ejercicio1Iterativo(d.varA(), d.varB(), d.varC(), d.varD(), d.varE()));
			
		});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 1 Recursivo");
		l.forEach(d->{
			System.out.println(Ejercicio1.ejercicio1Recursivo(d.varA(), d.varB(), d.varC(), d.varD(), d.varE()));
			
		});
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static record entradaFicheroEj2(Integer a, Integer b, String c) {
		public static entradaFicheroEj2 of(Integer a, Integer b, String c) {
			return new entradaFicheroEj2(a, b, c);
		}
		
		private static entradaFicheroEj2 parseLinea(String linea) {
			String [] splitted = linea.split(",");
			return of(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]), splitted[2].toString());
			
	}

}
	
	public static void testEjercicio2() {
		String file = "ficheros/PI1Ej2DatosEntrada.txt";
		List<String> lineas = Files2.linesFromFile(file);
		List<entradaFicheroEj2> l = lineas.stream().map(linea -> entradaFicheroEj2.parseLinea(linea)).toList();
		System.out.println("\n EJERCICIO 2");
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 2 Recursivo No final");
		l.forEach(d->{
			System.out.println(Ejercicio2.ejercicio2RecursivoNoFinal(d.a(), d.b(), d.c()));
		});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 2 Recursivo Final");
		l.forEach(d->{
			System.out.println(Ejercicio2.ejercicio2RecursivoFinal(d.a(), d.b(), d.c()));
		});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 2 Iterativo");
		l.forEach(d->{
			System.out.println(Ejercicio2.ejercicio2RecursivoFinal(d.a(), d.b(), d.c()));
		});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio 2 Funcional");
		l.forEach(d->{
			System.out.println(Ejercicio2.ejercicio2Funcional(d.a(), d.b(), d.c()));
		});
	}
	
	
	public static void testEjercicio3() {
		String file1 = "ficheros/PI1Ej3DatosEntrada1A.txt";
		String file2 = "ficheros/PI1Ej3DatosEntrada1B.txt";
		String file3 = "ficheros/PI1Ej3DatosEntrada2A.txt";
		String file4 = "ficheros/PI1Ej3DatosEntrada2B.txt";
		String file5 = "ficheros/PI1Ej3DatosEntrada3A.txt";
		String file6 = "ficheros/PI1Ej3DatosEntrada3B.txt";
		System.out.println("\n EJERCICIO 3");
		System.out.println("\n################################################");
		System.out.println("\n Solucion iterativa");
		System.out.println("1) Iterativo Ficheros 1 y 2: \n" + Ejercicio3.ejercicio3Iterativo(file1, file2, Comparator.naturalOrder()));
		System.out.println("2) Iterativo Ficheros 3 y 4: \n" + Ejercicio3.ejercicio3Iterativo(file3, file4, Comparator.naturalOrder()));
		System.out.println("3) Iterativo Ficheros 5 y 6: \n" + Ejercicio3.ejercicio3Iterativo(file5, file6, Comparator.naturalOrder()));
		System.out.println("\n################################################");
		System.out.println("\n Solucion recursiva final");
		System.out.println("1) Recursiva Final Ficheros 1 y 2: \n" + Ejercicio3.ejercicio3RecursivoFinal(file1, file2, Comparator.naturalOrder()));
		System.out.println("2) Recursiva Final Ficheros 3 y 4: \n" + Ejercicio3.ejercicio3RecursivoFinal(file3, file4, Comparator.naturalOrder()));
		System.out.println("3) Recursiva Final Ficheros 5 y 6: \n" + Ejercicio3.ejercicio3RecursivoFinal(file5, file6, Comparator.naturalOrder()));
		System.out.println("\n################################################");
		System.out.println("\n Solucion funcional");
		System.out.println("1) Funcional Ficheros 1 y 2: \n" + Ejercicio3.ejercicio3RecursivoFinal(file1, file2, Comparator.naturalOrder()));
		System.out.println("2) Funcional Ficheros 3 y 4: \n" + Ejercicio3.ejercicio3RecursivoFinal(file3, file4, Comparator.naturalOrder()));
		System.out.println("3) Funcional Ficheros 5 y 6: \n" + Ejercicio3.ejercicio3RecursivoFinal(file5, file6, Comparator.naturalOrder()));
	}
	
	
	
	public static record entradaFicheroEj4(Integer a, Integer b, Integer c) {
		public static entradaFicheroEj4 of(Integer a, Integer b, Integer c) {
			return new entradaFicheroEj4(a, b, c);
		}
		
		private static entradaFicheroEj4 parseLinea(String linea) {
			String [] splitted = linea.split(",");
			return of(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]), Integer.valueOf(splitted[2]));
			
	} 
}
	public static void testEjercicio4() {
		String file = "ficheros/PI1Ej4DatosEntrada.txt";
		List<String> lineas = Files2.linesFromFile(file);
		List<entradaFicheroEj4> l = lineas.stream().map(linea -> entradaFicheroEj4.parseLinea(linea)).toList();
		System.out.println("\n EJERCICIO 4");
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio Iterativo");
		l.forEach(d->{
			System.out.println(Ejercicio4.ejercicio4Iterativo(d.a(), d.b(), d.c()));
		});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio Recursivo Sin Memoria");
		l.forEach(d->{
			System.out.println(Ejercicio4.ejercicio4RecSinMemoria(d.a(), d.b(), d.c()));
			});
		System.out.println("\n################################################");
		System.out.println("\n Ejercicio Recursivo Sin Memoria");
		l.forEach(d->{
			System.out.println(Ejercicio4.ejercicio4ConMemoria(d.a(), d.b(), d.c()));
			});
	}
}

	
	

