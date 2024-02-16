package ejercicio4;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jgrapht.Graphs;
import _datos.DatosEjercicioClientes;
import _utils.Cliente;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record ClienteVertex(Integer cliente, Set<Integer> pendientes, List<Integer> visitados, Integer kms)implements VirtualVertex<ClienteVertex, ClienteEdge, Integer> {
	public static ClienteVertex of(Integer i, Set<Integer> pend,List<Integer>visitados,Integer kms) {
		
		return new ClienteVertex(i, pend,visitados,kms);
	}
	public static ClienteVertex initial() {
		Set<Integer> remaining = DatosEjercicioClientes.gf.vertexSet().stream().map(c->c.id()).collect(Collectors.toSet());
		
		return of(0,remaining,List2.of(0),0);
	}
	
	public static Predicate<ClienteVertex> goal(){
	
		return v -> v.pendientes().isEmpty();
	}
	
	public static Predicate<ClienteVertex> goalHasSolution(){
		return v ->v.visitados().get(v.visitados.size()-1) == 0;
	}
	
	// TODO Consulte las clases GraphsPI5 y TestPI5
	
		
	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
	
		Cliente clienteActual = DatosEjercicioClientes.getCliente(this.visitados().get(this.visitados.size()-1));
		
		if(this.cliente() <= DatosEjercicioClientes.getNVertices()) {
			List<Integer> conectados = Graphs.neighborListOf(DatosEjercicioClientes.gf, clienteActual).stream().map(c->c.id()).toList();
			alternativas.addAll(List2.intersection(this.pendientes(), conectados));
		}
		
		return alternativas;
	
	}
	
	@Override
	public ClienteVertex neighbor(Integer a) {
		Integer distancia = this.kms() + DatosEjercicioClientes.getPeso(this.visitados().get(this.visitados().size()-1), a).intValue();
		Set<Integer> pendientes = new HashSet<>(this.pendientes());
		pendientes.remove(a);
		List<Integer> visitados = new ArrayList<>(this.visitados());
		visitados.add(a);
		return of(this.cliente()+1, pendientes, visitados, distancia);
	}
	
	@Override
	public ClienteEdge edge(Integer a) {
		return ClienteEdge.of(this, neighbor(a), a);
	
	}
	
	//Se explica en practicas.
	public ClienteEdge greedyEdge() {
	return null;
}
}