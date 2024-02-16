package ejercicio4.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.Graphs;
import _datos.DatosEjercicioClientes;
import _utils.Cliente;
import us.lsi.common.List2;


public record ClientesProblem(Integer cliente, Set<Integer> pendientes,List<Integer>visitados,Integer kms){

	public static ClientesProblem of(Integer i, Set<Integer>pend,List<Integer>visitados,Integer kms) {
		return new ClientesProblem(i, pend,visitados,kms);
	}
	
	public static ClientesProblem initial() {
		Set<Integer> remaining = DatosEjercicioClientes.gf.vertexSet().stream().map(c->c.id()).collect(Collectors.toSet());
		
		return of(0,remaining,List2.of(0),0);
	}
	
	
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
	
		Cliente clienteActual = DatosEjercicioClientes.getCliente(this.visitados().get(this.visitados.size()-1));
		
		if(this.cliente() <= DatosEjercicioClientes.getNVertices()) {
			List<Integer> conectados = Graphs.neighborListOf(DatosEjercicioClientes.gf, clienteActual).stream().map(c->c.id()).toList();
			alternativas.addAll(List2.intersection(this.pendientes(), conectados));
		}
		
		return alternativas;
	
	}
	
	public ClientesProblem neighbor(Integer a) {
		Integer distancia = this.kms() + DatosEjercicioClientes.getPeso(this.visitados().get(this.visitados().size()-1), a).intValue();
		Set<Integer> pendientes = new HashSet<>(this.pendientes());
		pendientes.remove(a);
		List<Integer> visitados = new ArrayList<>(this.visitados());
		visitados.add(a);
		return of(this.cliente()+1, pendientes, visitados, distancia);
	}
	
	public Double heuristic() {
		
		Double res = 0.;
		
		
		for(int i = 0; i < DatosEjercicioClientes.getNVertices(); i++) {
			Double beneficio = 0.;
	
			for(Integer a: actions()) {
				if(DatosEjercicioClientes.getBeneficio(a)>beneficio) {
					beneficio = DatosEjercicioClientes.getBeneficio(a);
					
				}
			}
			res += beneficio;
			
		}
		return res;
		}
	
	
	
}
