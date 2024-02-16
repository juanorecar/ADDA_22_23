package ejercicios;

import java.util.HashMap;
import java.util.Map;

import records.Tupla4;

public class Ejercicio4 {
	
	public static String solucion_recursiva_multiple_sin_memoria(Integer a, Integer b, Integer c) {
        String s = null;
        if( a < 2 && b <= 2 || c < 2) {
            s = String.format("(%d + %d + %d)", a, b, c);
        } else if( a < 3 || b < 3 && c <= 3) {
            s = String.format("(%d - %d - %d)", c, b, a);
        } else if( b % a == 0 && (a % 2 == 0 || b % 3 == 0)) {
            s = String.format("(%s * %s)", solucion_recursiva_multiple_sin_memoria(a - 1, b / a, c - 1),
                    solucion_recursiva_multiple_sin_memoria(a - 2, b / 2, c / 2));
        } else { // En otro caso
            s = String.format("(%s / %s)", solucion_recursiva_multiple_sin_memoria(a / 2, b - 2, c / 2),
                    solucion_recursiva_multiple_sin_memoria(a / 3, b - 1, c / 3));
        }
        return s;
    }
	
	public static String solucion_recursiva_multiple_con_memoria(Integer a, Integer b, Integer c) {
        Map<Tupla4, String> map = new HashMap<>();
        String s = null;
        Tupla4 t = Tupla4.of(a,b,c);
        if(map.containsKey(t)) {
            s = map.get(t);
        } else if( a < 2 && b <= 2 || c < 2) {
            s = String.format("(%d + %d + %d)", a, b, c);
            map.put(t, s);
        } else if( a < 3 || b < 3 && c <= 3) {
            s = String.format("(%d - %d - %d)", c, b, a);
            map.put(t, s);
        } else if( b % a == 0 && (a % 2 == 0 || b % 3 == 0)) {
            s = String.format("(%s * %s)", solucion_recursiva_multiple_con_memoria(a - 1, b / a, c - 1),
                    solucion_recursiva_multiple_con_memoria(a - 2, b / 2, c / 2));
            map.put(t, s);
        } else { // En otro caso
            s = String.format("(%s / %s)", solucion_recursiva_multiple_con_memoria(a / 2, b - 2, c / 2),
                    solucion_recursiva_multiple_con_memoria(a / 3, b - 1, c / 3));
            map.put(t, s);
        }
        // System.out.println(map);
        return s;
    }
	
	public static String solucion_iterativa(Integer a, Integer b, Integer c) {
        // Secuencia ==> valores (a,b,c)
        // Bucle for para cada variable de la key del map
        String s = null; // Acumulador
        Map<Tupla4, String> map = new HashMap<>();
        for(int i = 0; i <= a; i++) {
            for(int j = 0; j <= b; j++) {
                for(int k = 0; k <= c; k++) {
                    Tupla4 t = Tupla4.of(i,j,k);
                    if( i < 2 && j <= 2 || k < 2) {
                        
                        s = String.format("(%d + %d + %d)", i, j, k);
                        map.put(t, s);
                        
                    } else if( i < 3 || j < 3 && k <= 3) {
                        
                        s = String.format("(%d - %d - %d)", k, j, i);
                        map.put(t, s);
                        
                    } else if( j % i == 0 && (i % 2 == 0 || j % 3 == 0)) {
                        
                        Tupla4 t1 = Tupla4.of(i - 1, j / i, k - 1);
                        Tupla4 t2 = Tupla4.of(i - 2, j / 2, k / 2);
                        s = String.format("(%s * %s)", map.get(t1), map.get(t2));
                        map.put(t, s);
                        
                    } else { // En otro caso
                        
                        Tupla4 t3 = Tupla4.of(i / 2, j - 2, k / 2);
                        Tupla4 t4 = Tupla4.of(i / 3, j - 1 , k / 3);
                        
                        s = String.format("(%s / %s)", map.get(t3), map.get(t4));
                        map.put(t, s);
                    }
                }
            }
        }
        Tupla4 res = Tupla4.of(a, b, c);
        return map.get(res);
    }

}
