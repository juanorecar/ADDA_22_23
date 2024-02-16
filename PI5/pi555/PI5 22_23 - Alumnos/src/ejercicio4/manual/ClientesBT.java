package ejercicio4.manual;

import java.util.HashSet;
import java.util.Set;
import _soluciones.SolucionClientes;

public class ClientesBT {

	private static Double mejorValor;
	private static ClientesState estado;
	private static Set<SolucionClientes> soluciones;
	
	public static void search() {
		soluciones = new HashSet<SolucionClientes>();
		mejorValor = Double.MIN_VALUE; // Estamos minimizando
		estado = ClientesState.initial();
		bt_search();
	}
	
	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido > mejorValor) { // Estamos minimizando
				mejorValor = valorObtenido;
				soluciones.add(estado.getSolucion());
			}
		} else if(!estado.esTerminal()){
			for (Integer a: estado.alternativas()) {
				
				if (estado.cota(a) >= mejorValor) { // Estamos minimizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}
	
	public static Set<SolucionClientes> getSoluciones() {
		return soluciones;
	}
	
	
	

	
}
