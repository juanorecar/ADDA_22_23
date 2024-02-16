package ejercicio4.manual;

import java.util.ArrayList;
import java.util.List;
import _datos.DatosEjercicioClientes;
import _soluciones.SolucionClientes;
import us.lsi.common.List2;

public class ClientesState {

	ClientesProblem actual;
	Double acumulado;
	List<Integer> acciones;
	List<ClientesProblem> anteriores;
	
	private ClientesState(ClientesProblem p, Double a, List<Integer> actions, List<ClientesProblem> vertices) {
		actual = p;
		acumulado = a;
		acciones = actions;
		anteriores= vertices;
	}
	
	public static ClientesState initial() {
		ClientesProblem p = ClientesProblem.initial();
		Double a = 0.;
		List<Integer> ls1= List2.empty();
		List<ClientesProblem> ls2 = List2.empty();
		return new ClientesState(p,a,ls1,ls2);
	}
	
	public static ClientesState of(ClientesProblem prob){
		List<ClientesProblem> ls = new ArrayList<>();
		ls.add(prob);
		return new ClientesState(prob, 0., new ArrayList<>(), ls);
	}
	
	public void forward(Integer a) {
		acumulado += a * DatosEjercicioClientes.getBeneficio(actual.cliente());
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}
	
	public void back() {
		int last = acciones.size() - 1;
		ClientesProblem prob_ant = anteriores.get(last);
		acumulado = acciones.get(last) *
		DatosEjercicioClientes.getBeneficio(prob_ant.cliente());
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	
	}
	
	
	public List<Integer> alternativas() {
		return actual.actions();
	}
	
	public Double cota(Integer a) {
		ClientesProblem siguiente = this.actual.neighbor(a);
		Double wei = DatosEjercicioClientes.getBeneficio(siguiente.visitados().get(siguiente.visitados().size()-1)) - siguiente.kms();
		return acumulado + wei + actual.neighbor(a).heuristic();
	}
	
	public Boolean esSolucion() {
	// TODO Cuando todos los elementos del universo se han
	
		return actual.cliente() == 0 &&
		actual.pendientes().isEmpty();
	}
		
	public Boolean esTerminal() {
		return actual.cliente() == 0 &&
		actual.pendientes().isEmpty();
	}
		
	public SolucionClientes getSolucion() {
		return SolucionClientes.of_format(acciones);
	}
	
	
	
	 
}
