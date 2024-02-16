package ejercicios;

import java.util.*;
import java.util.stream.Stream;

import records.Tupla3;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.iterables.IteratorFile;


public class Ejercicio3 {
	
	/*
	 *   A partir de 2 ficheros ordenados de objetos de tipo Punto2D, obtener una lista ordenada de puntos
	 *   que incluya sólo los puntos del primer y tercer cuadrante. Para realizar la fusión debe hacer uso
	 *   de iteradores directamente sobre los ficheros de entrada, no permitiéndose almacenar los puntos 
	 *   en listas y hacer fusión de listas
	 *   
	 *   Ejercicio 3: Proporcione una solución iterativa usando while, 
	 *   una recursiva final, y una en notación funcional.
	 */
	
	
	
	public static List<Punto2D> solucion_iterativa(String file1, String file2){
		
		Iterator<String> it1 = new IteratorFile(file1);
		Iterator<String> it2 = new IteratorFile(file2);
		List<Punto2D> res = new ArrayList<>();
		
//		if(it1.hasNext()) {
//			Punto2D p1 = Punto2D.parse(it1.next());
//		} else {
//			Punto2D p1 = null;
//		}
		
		Punto2D p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
		Punto2D p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
		
		while(p1 != null && p2 != null) {
			if(p1.compareTo(p2) < 0) {
				if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p1);
				}
				p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
			} else {
				if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p2);
				}
				p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
			}
		}
		
		while(p1 != null) {
			if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p1);
			}
			p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
		}
		
		while(p2 != null) {
			if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p2);
			}
			p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
		}
		
		return res;
	}
	
	// ITERATIVA ==> RECURSIVA FINAL --------> Añadimos parametros
	public static List<Punto2D> solucion_recursiva_final(String file1, String file2){
		
		Iterator<String> it1 = new IteratorFile(file1);
		Iterator<String> it2 = new IteratorFile(file2);
		
		Punto2D p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
		Punto2D p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
		
		List<Punto2D> res = new ArrayList<>();
		
		return solucion_recursiva_final_aux(it1, it2, p1, p2, res);
	}
	
	private static List<Punto2D> solucion_recursiva_final_aux(Iterator<String> it1, 
															  Iterator<String> it2, Punto2D p1, Punto2D p2, List<Punto2D> res){
		
		if(p1 != null && p2 != null) {
			if(p1.compareTo(p2) < 0) {
				if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p1);
				}
				p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
				res = solucion_recursiva_final_aux(it1, it2, p1, p2, res);
			} else {
				if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
					res.add(p2);
				}
				p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
				res = solucion_recursiva_final_aux(it1, it2, p1, p2, res);
			}
		}
		
		if(p1 != null && p2 == null) { // Modificado
			if(p1.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p1.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p1);
			}
			p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
			res = solucion_recursiva_final_aux(it1, it2, p1, p2, res);
		}
		
		if(p2 != null && p1 == null) { // Modificado
			if(p2.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p2.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				res.add(p2);
			}
			p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
			res = solucion_recursiva_final_aux(it1, it2, p1, p2, res);
		}
	
		return res;
	}
	
	public static List<Punto2D> solucion_funcional(String file1, String file2){
		
		Iterator<String> it1 = new IteratorFile(file1);
		Iterator<String> it2 = new IteratorFile(file2);
		
		Punto2D p1 = it1.hasNext()?Punto2D.parse(it1.next()):null;
		Punto2D p2 = it2.hasNext()?Punto2D.parse(it2.next()):null;
		
		List<Punto2D> aux = new ArrayList<>();
		
		List<Punto2D> ls = Stream.iterate(Tupla3.of(it1, p1, it2, p2, aux), t -> t.next())
				.filter(t3 -> (t3.p1() == null && t3.p2() == null))
				.findFirst()
				.get().res();

		return ls;
	}

}
