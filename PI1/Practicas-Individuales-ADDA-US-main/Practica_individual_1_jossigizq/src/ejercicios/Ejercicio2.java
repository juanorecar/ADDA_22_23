package ejercicios;

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
		Boolean cp = true; // condicion de parada
		Integer ac = 0;
		while( cp ) {
			if(s.length() == 0) { // # PRIMER caso base
				
				ac += a*a + b*b;
				cp = false;
				
			} else if (a < 2 || b < 2 ) { // # SEGUNDO caso base
				
				ac += s.length() + a + b;
				cp = false;
				
			} else if(a % s.length() < b % s.length()) {
				
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
		return ac;
	}
	
	public static Integer solucion_funcional(Integer a, Integer b, String s) {
		return null;
	}
}
