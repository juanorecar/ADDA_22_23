package ejercicio4;

import java.util.function.Predicate;
import _datos.DatosEjercicioClientes;

public class ClienteHeuristic {

	public static Double heuristic(ClienteVertex v1, Predicate<ClienteVertex> goal, ClienteVertex v2) {
		
		Double res = 0.;
		ClienteVertex clienteActual = v1;
		
		for(int i = 0; i < DatosEjercicioClientes.getNVertices(); i++) {
			Double beneficio = 0.;
			Integer op = 0;
			for(Integer a: clienteActual.actions()) {
				if(DatosEjercicioClientes.getBeneficio(a)>beneficio) {
					beneficio = DatosEjercicioClientes.getBeneficio(a);
					op = a;
				}
			}
			res += beneficio;
			if(op != 0) {
				clienteActual = clienteActual.neighbor(op);
			}else {
				break;
			}
		}
		return res;
		}
	
	}

