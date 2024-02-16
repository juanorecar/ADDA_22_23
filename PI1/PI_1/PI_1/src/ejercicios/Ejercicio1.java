package ejercicios;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import records.Tupla1;

import java.util.*;

public class Ejercicio1 {
	
	/*
	 * Ejercicio 1: Analice el código que se muestra y proporcione una solución iterativa
	 * y otra recursiva final equivalentes.
	 */
	
	public static Map<Integer, List<String>> solucion_funcional (Integer varA, String varB, Integer varC, 
			  String varD, Integer varE) {
		
			UnaryOperator<Tupla1> nx = elem -> {
				return Tupla1.of(elem.varA() + 2,
				elem.varA() % 3 == 0 ? elem.varB() + elem.varA().toString() : elem.varB().substring(elem.varA() % elem.varB().length() ));
			};

			return Stream.iterate(Tupla1.of(varA, varB), elem -> elem.varA() < varC, nx) // Mientras varA < varC ==> siguiente
				.map(elem -> elem.varB() + varD) // Apartir de aqui solo trabajamos con varB
				.filter(nom -> nom.length() < varE) // nom ???
				.collect(Collectors.groupingBy(String::length));
	}
	
	public static Map<Integer, List<String>> solucion_iterativa (Integer varA, String varB, Integer varC, 
			  String varD, Integer varE){
		
		Tupla1 t = Tupla1.of(varA, varB);
		Map<Integer, List<String>> m = new HashMap<>();
		
		while(t.varA() < varC) {
			
			String vb = t.varB() + varD;
			
			if(vb.length() < varE) { // Filter

				if(m.containsKey(vb.length())) { // Collect String::lenght
					List<String> aux = m.get(vb.length()); //
					aux.add(vb);
					m.put(vb.length(), aux);
				} else {
					List<String> aux = new ArrayList<>();
					aux.add(vb);
					m.put(vb.length(), aux);
				}
			}
			
			t = t.next(); // Siguiente tupla
		}
		
		return m;
	}
	
	public static Map<Integer, List<String>> solucion_recursiva_final (Integer varA, String varB, Integer varC, 
			  String varD, Integer varE){
		Map<Integer, List<String>> map = new HashMap<>();
		return solucion_recursiva_final_aux(varA, varB, varC, varD, varE, map);
	}
	
	public static Map<Integer, List<String>> solucion_recursiva_final_aux (Integer varA, String varB, Integer varC, 
			  String varD, Integer varE, Map<Integer, List<String>> map){
		
		Tupla1 t = Tupla1.of(varA, varB);
		
		if(t.varA() < varC) { // While
			String vb = t.varB() + varD;
			if(vb.length() < varE) {
				
				if(map.containsKey(vb.length())) {
					List<String> aux = map.get(vb.length()); //
					aux.add(vb);
					map.put(vb.length(), aux);
					t = t.next();
					return solucion_recursiva_final_aux(t.varA(), t.varB(), varC, varD, varE, map);
				} else {
					List<String> aux = new ArrayList<>();
					aux.add(vb);
					map.put(vb.length(), aux);
					t = t.next();
					return solucion_recursiva_final_aux(t.varA(), t.varB(), varC, varD, varE, map);
				}
				
			}
			//t = t.next();
			//return solucion_recursiva_final_aux(t.varA(), t.varB(), varC, varD, varE, map);
		}
		
		return map;
	}

}
