package ejercicios;

import java.math.BigInteger;

public class Ejercicio1 {
	
	/*
	 * PI2 EJERCICIO 1
	 * 
	 * Analizar los tiempos de ejecucion de las versiones recursivas
	 * e iterativa para el cálculo del factorial. Comparar segun los resultados sean
	 * de tipo Double o BigInteger
	 * 
	 */
	
	public static Double factorialDouble(Integer n) {
		Double r;
		if (n == 0) {
			r = 1.;
		} else {
			r = factorialDouble(n - 1) * n;
		}
		return r;
	}
	
	
	public static Double factorialDoubleIt(Integer n) {
		
		Double r = 1.;
		Integer i = 1;
		while (i<=n) {
			r = r * i; 
			i = i+1;
		}
		return r;
	}
	
	public static BigInteger factorialBigInteger(Integer n) {
		BigInteger r;
		if (n == 0) {
			r = BigInteger.valueOf(1);
		} else {
			r = BigInteger.valueOf(n).multiply(factorialBigInteger(n-1));
		}
		return r;
	}
	
public static BigInteger factorialBigIntegerIt(Integer n) {
		
		BigInteger r = BigInteger.valueOf(1);
		Integer i = 1;
		while (i<=n) {
			r = r.multiply(BigInteger.valueOf(i)); 
			i = i+1;
		}
		return r;
	}
	
}
