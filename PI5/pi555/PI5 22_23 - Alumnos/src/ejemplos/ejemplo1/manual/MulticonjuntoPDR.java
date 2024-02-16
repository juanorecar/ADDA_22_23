package ejemplos.ejemplo1.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import _datos.DatosMulticonjunto;
import _soluciones.SolucionMulticonjunto;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class MulticonjuntoPDR {

	public static record Spm(Integer a, Integer weight) implements Comparable<Spm> {
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static Map<MulticonjuntoProblem, Spm> memory;
	public static Integer mejorValor; 

	public static SolucionMulticonjunto search() {
		memory =  Map2.empty();
		mejorValor = Integer.MAX_VALUE; // Estamos minimizando
		
		pdr_search(MulticonjuntoProblem.initial(), 0, memory);
		return getSolucion();
	}

	private static Spm pdr_search(MulticonjuntoProblem prob, Integer acumulado, Map<MulticonjuntoProblem, Spm> memoria) {

		Spm res = null;
		
		//Devuelve true cuando ya no queden más nodos (fin de la rama)
		Boolean esTerminal = prob.index().equals(DatosMulticonjunto.getNumElementos());
		
		//Devuelve true si esa rama tiene solucion 
		Boolean esSolucion = prob.remaining().equals(0);

		//¿El problema ha sido ya resuelto?
		if (memory.containsKey(prob)) {
			res = memory.get(prob);
			
		} else if (esTerminal && esSolucion) { //Caso base
			res = Spm.of(null, 0); // Ya no hay mas subproblemas
			memory.put(prob, res); // Lo guardo igualmente
			if (acumulado < mejorValor) { // Estamos minimizando
				mejorValor = acumulado;
			}
		} else { //caso recursivo
			
			//Voy a analizar todas las posibles soluciones (tomando distintas alternativas) desde mi problema inicial)
			List<Spm> soluciones = List2.empty();
			for (Integer action : prob.actions()) {
				//Miro si me interesa descartar o no la rama
				Double cota = acotar(acumulado, prob, action);   		
				if (cota > mejorValor) { // La descarto
					res = null;
				}else {
					
					//Obtengo la solucion del vecino tomando un camino (acción)
					MulticonjuntoProblem vecino = prob.neighbor(action);
					
					//ACCIÓN = PESO
					
					//Vecino = nuevo vértice tomando una acción
					//Acumulado = lo que lleve acumulado más esa acción
					//Arrastro la memoria
					Spm s = pdr_search(vecino, acumulado + action, memory);
					if (s != null) {
						
						//si da una solución válida, la instanciamos
						//de forma estática para guardarla en la lista
						//de soluciones parciales
						Spm amp = Spm.of(action, s.weight() + action);
						soluciones.add(amp);
					}
				}
			}
			// Estamos minimizando
			//de entre todas las soluciones, quédate con la más pequeña
			//solucion mas pequeña = solucion con menor peso
			res = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			if (res != null)
				// guardo la solucion para ese subproblema
				memory.put(prob, res);
		}

		return res;
	}

	private static Double acotar(Integer acum, MulticonjuntoProblem p, Integer a) {
		
		// LO QUE LLEVO + LO QUE ACTUALIZO TOMANDO "a" + LO QUE ME QUEDARÍA TOMANDO "a" (heurística)
		
		return acum + a + p.neighbor(a).heuristic();
	}

	public static SolucionMulticonjunto getSolucion() {
		// La solución del problema original no es más que una serie de pasos
		//Serie de pasos = lista de acciones
		List<Integer> acciones = List2.empty();
		
		//Obtengo el problema inicial
		MulticonjuntoProblem prob = MulticonjuntoProblem.initial();
		
		//Obtengo la solución del problema inicial
		Spm spm = memory.get(prob);
		while (spm != null && spm.a != null) {
			
			//Recreo los pasos
			MulticonjuntoProblem old = prob;
			
			//Guardo la acción del problema actual (solucion)
			acciones.add(spm.a);
			
			// Actualizo el problema
			// Actualizar el problema = obtener el subproblema dado "a"
			prob = old.neighbor(spm.a);
			spm = memory.get(prob);
		}
		return SolucionMulticonjunto.of(acciones);
	}

}
