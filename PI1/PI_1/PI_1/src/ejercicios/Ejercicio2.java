wwwwwwwwpackage ejercicios;

import java.util.function.Predicate;
import java.util.stream.Stream;

import records.Tupla2;

public class Ejercicio2 {
	
	/*
	 * Dada la siguiente definición recursiva de la función f (que toma como entrada 2
	 *	números enteros positivos y una cadena, y devuelve un número entero):
	 *
	 *	Ejercicio 2: Proporcione una solución iterativa usando while, una recursiva no
	 *	final, una recursiva final, y una en notación funcional.
	 */
	
	// Como la definicion que nos dan es NO FINAl, empezamos por aqui
	public static Integer solucion_recursiva_no_final(Integer a, Integer b, String s) {
		
		if(s.length() == 0) { // # PRIMER caso base
			
			return a*a+b*b;
			
		} else if(a < 2 || b < 2) { // # SEGUNDO caso base
			
			return s.length() + a + b;
			
		} else if(a % s.length() < b % s.length()) {
			
			return a + b + solucion_recursiva_no_final(a - 1, b / 2, s.substring(a % s.length(), b % s.length()));
			
		} else { // En otro caso
			
			return a * b + solucion_recursiva_no_final(a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
			
		}
	}
	
	// Para transformar de NO FINAl ==> FINAL, añadimos un parametro como acumulador
	public static Integer solucion_recursiva_final(Integer a, Integer b, String s) {
		Integer ac = 0;
		return solucion_recursiva_final_aux(a,b,s,ac);
	}
	
	private static Integer solucion_recursiva_final_aux(Integer a, Integer b, String s, Integer ac) {
		
		if(s.length() == 0) { // # PRIMER caso base
			
			return a*a + b*b + ac;
			
		} else if (a < 2 || b < 2 ) { // # SEGUNDO caso base
			
			return s.length() + a + b + ac;
			
		} else if(a % s.length() < b % s.length()) {
			
			return solucion_recursiva_final_aux(a - 1, b / 2, s.substring(a % s.length(), b % s.length()), ac + a + b);
			
		} else { // En otro caso
			
			return solucion_recursiva_final_aux(a / 2, b - 1, s.substring(b % s.length(), a % s.length()), ac + a * b);
			
		}
		
	}
	
	/*
	 * ### PREGUNTAR ### INVARIANTE y ESTADO
	 */
	public static Integer solucion_iterativa(Integer a, Integer b, String s) {
		// Secuencia
		// Acumulador
//		Boolean f1 = s.length() == 0;
//		Boolean f2 = a < 2 || b < 2;
// 		Boolean cp = !f1 || f2; // condicion de parada
		Integer ac = 0;
		
		// Mientras se cumplan los casos recursivos ...
		// Aqui va: "si no se cumplen las condiciones de los casos bases"
		while( !( (s.length() == 0) || (a < 2 || b < 2 ) ) ) {
			//System.out.println(s);
			if(a % s.length() < b % s.length()) {
				ac += a + b;
				s = s.substring(a % s.length(), b % s.length());
				a -= 1;
				b /= 2;
				
			} else { // En otro caso
				
				ac += a * b;
				s = s.substring(b % s.length(), a % s.length());
				a /= 2;
				b -= 1;
				
			}
		}
		
		if(s.length() == 0) { // # PRIMER caso base
			
			ac += a*a + b*b;
			
		} else if (a < 2 || b < 2 ) { // # SEGUNDO caso base
			
			ac += s.length() + a + b;
			
		}
		return ac;
	}
	
	// dos next() ???
	public static Integer solucion_funcional(Integer a, Integer b, String s) {
		
		Tupla2 seed = Tupla2.of(a, b, s, 0);
		
 		Predicate<Tupla2> cb = tupla -> ( (tupla.s().length() == 0) || (tupla.a() < 2 || tupla.b() < 2) );
		
		Tupla2 t = Stream.iterate(seed, x -> x.next())
				.filter(cb) // Casos base
				.findFirst()
				.get();
		

		Integer res = null;
		if(t.s().length() == 0) {
			res = t.ac() + t.a() * t.a() + t.b() * t.b();
		} else {
			res = t.ac() + s.length() + t.a() + t.b();
		}
		return res;
	}

}















