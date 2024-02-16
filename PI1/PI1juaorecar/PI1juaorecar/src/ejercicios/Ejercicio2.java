package ejercicios;

import java.util.stream.Stream;

public class Ejercicio2 {
	
	public static Integer ejercicio2RecursivoNoFinal(Integer a, Integer b, String s) {
		return ejercicio2RecursivoNoFinalAux(a, b, s);
	}
	
	public static Integer ejercicio2RecursivoNoFinalAux(Integer a, Integer b, String s) {
		Integer r = null;
		if(s.length()==0) {
			r = (a*a) + (b*b);
		}else if(a<2||b<2) {
			r = s.length()+a+b;
		}else if(a%s.length() < b%s.length()) {
			r = a + b + ejercicio2RecursivoNoFinalAux(a-1, b/2, s.substring(a%s.length(), b%s.length())); 
		}else {
			r = a * b + ejercicio2RecursivoNoFinalAux(a/2, b-1 , s.substring(b%s.length(), a%s.length()));
		}
		return r;
	}
	
	
	
	public static Integer ejercicio2RecursivoFinal(Integer a, Integer b, String s) {
		return recFinal(a, b, s, 0);
	}
	
	private static Integer recFinal(Integer a, Integer b, String s, Integer ac) {
			Integer r = null;
		if(s.length()==0) {
			r = (a*a) + (b*b) + ac;
		}else if(a<2||b<2) {
			r = s.length()+a+b+ac;
		}else if(a%s.length() < b%s.length()) {
			r = recFinal(a-1, b/2, s.substring(a%s.length(), b%s.length()), ac + a + b); 
		}else {
			r = recFinal(a/2, b-1 , s.substring(b%s.length(), a%s.length()), ac + (a*b));
		}
		return r;
	}
	
	
	
	public static Integer ejercicio2Iterativo(Integer a, Integer b, String s) {
		Integer r = 0;
		while(!(s.length()==0) ||(!(a<2||b<2)) ) {	
		if(a%s.length() < b%s.length()) {
			//r = recFinal(a-1, b/2, s.substring(a%s.length(), b%s.length()), a+b);
			r = r + (a+b);
			a = a-1;
			b = b/2;
			s = s + s.substring(a%s.length(), b%s.length());
			
		}else {
			//r = recFinal(a/2, b-1 , s.substring(b%s.length(), a%s.length()), a*b);
			r = r + a*b;
			a /= 2;
			b -=1 ;
			s = s.substring(b%s.length(), a%s.length());
	
			}
		}
		if(s.length()==0) {
			r = r + (a*a) + (b*b);
		}else {
			r = r+s.length()+a+b;
		}
		return r;
	}
	
	public static record Tupla(Integer ac, Integer a, Integer b, String s) {
		public static Tupla of(Integer ac, Integer a, Integer b, String s) {
			return new Tupla(ac, a, b, s);
		}
	
	public static Tupla first(Integer a, Integer b, String s) {
		return of(0, a, b, s);
	}
	
	public Tupla next() {
		Tupla r = null;
		if(a%s.length() < b%s.length()) {
			r =  of(ac+a+b,a-1,b/2,s.substring(a%s.length(), b%s.length()));
		}else {
			r =  of(ac+(a*b),a/2,b-1,s.substring(b%s.length(), a%s.length()));
		}
		return r;
	}
}
	
	public static Integer ejercicio2Funcional(Integer a, Integer b, String s) {
		Integer r = 0;
		
		Tupla t = Stream.iterate(Tupla.first(a,b,s), e->e.next())
				.filter(e -> e.s().length()==0 || (e.a()<2||e.b()<2))
				.findFirst()
				.get();
		
		if(t.s().length()==0) {
			r = t.ac() + (t.a()*t.a()) + (t.b()*t.b());
		}else {
			r = t.ac() + (t.s().length()+t.a()+t.b());
		}
		return r;
	}
	
	
}

	
