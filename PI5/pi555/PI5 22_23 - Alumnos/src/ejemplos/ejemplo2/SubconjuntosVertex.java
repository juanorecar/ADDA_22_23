package ejemplos.ejemplo2;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import _datos.DatosSubconjuntos;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record SubconjuntosVertex(Integer index, Set<Integer> remaining) 
    implements VirtualVertex<SubconjuntosVertex, SubconjuntosEdge, Integer>{
	
	public static SubconjuntosVertex of(Integer i, Set<Integer> rest) {
		return new SubconjuntosVertex(i, rest);
	}
	
	public static SubconjuntosVertex initial() {
		return of(0, Set2.copy(DatosSubconjuntos.getUniverso())); //El copy es para convertir a set
	}
	
	/*
	 * El goal será si el indice es igual al numero de subconjuntos (n)
	 */
	public static Predicate<SubconjuntosVertex> goal(){
		return v -> v.index() == DatosSubconjuntos.getNumSubconjuntos();
	}
	/*
	 * Tiene solucion si el set es vacio
	 */
	public static Predicate<SubconjuntosVertex> goalHasSolution(){
		return v -> v.remaining().isEmpty();
	}
	
	// TODO Consulte las clases GraphsPI5 y TestPI5 
	
	@Override
	public List<Integer> actions() {
		// TODO Alternativas de un vertice
		List<Integer> alternativas = new ArrayList<Integer>();
		if(index < DatosSubconjuntos.getNumSubconjuntos()) {
			if(remaining.isEmpty()) {
				alternativas = List.of(0);
			}else {
				Set<Integer> rest = Set2.difference(remaining, DatosSubconjuntos.getElementos(index));
				if(rest.equals(remaining)) {
					alternativas = List2.of(0);
				}else if(index == DatosSubconjuntos.getNumSubconjuntos()-1) {
					alternativas = rest.isEmpty() ? List2.of(1) : List2.of(0);
				}else {
					alternativas = List2.of(0,1);
				}
			}
		}
		return alternativas;
	}
	
	@Override
	public SubconjuntosVertex neighbor(Integer a) {
		// TODO Vertice siguiente al actual segun la alternativa a 
		Set<Integer> rest = a == 0 ? Set.copyOf(remaining) : Set2.difference(remaining, DatosSubconjuntos.getElementos(index));
		return of(index + 1, rest);
	}
	
	/*
	 * Construye la arista y peso con respecto vertices
	 */
	@Override
	public SubconjuntosEdge edge(Integer a) {
		return SubconjuntosEdge.of(this, neighbor(a), a);
	}
	
	// Se explica en practicas.
	/*
	 * Arista voraz -> Estoy en un subconjunto y miro el resto de subconjuntos por si 
	 * me interesa coger alguno
	 */
	public SubconjuntosEdge greedyEdge() {
		Set<Integer> rest = Set2.difference(remaining, DatosSubconjuntos.getElementos(index));
		return rest.equals(remaining)? edge(0): edge(1);
	}
}
