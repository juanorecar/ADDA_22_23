package soluciones;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicioClientes;
import utiles.Cliente;

public class SolucionClientes {
	
	public static SolucionClientes of_Rnage(List<Integer> value) {
		return new SolucionClientes(value);
	}
	
	private Double kms;
	private Double benef;
	private List<Cliente> clientes;

	private SolucionClientes() {
		kms = 0.;
		benef = 0.;
		clientes = new ArrayList<>();
		Cliente client0 = DatosEjercicioClientes.getCliente(0);
		clientes.add(client0);
	}
	
	private SolucionClientes(List<Integer> value) {
		kms = 0.;
		benef = 0.;
		clientes = new ArrayList<>();
		Cliente client0 = DatosEjercicioClientes.getCliente(0);
		clientes.add(client0);
		
		for (int i=0; i<value.size(); i++) {
			Cliente client = DatosEjercicioClientes.getCliente(value.get(i));
			clientes.add(client);
			
			if (i == 0) {
				if(DatosEjercicioClientes.existeArista(0, value.get(i))) {
					kms += DatosEjercicioClientes.getPeso(0, value.get(i));
					benef += DatosEjercicioClientes.getBeneficio(value.get(i)) - kms;
				}
			} else {
				if(DatosEjercicioClientes.existeArista(value.get(i - 1), value.get(i))) {
					kms += DatosEjercicioClientes.getPeso(value.get(i - 1), value.get(i));
					benef += DatosEjercicioClientes.getBeneficio(value.get(i)) - kms;
				}
			}
		}
	}
	
	public static SolucionClientes empty() {
		return new SolucionClientes();
	}
	
	public String toString() {
		List<Integer> ids = clientes.stream().map(client -> client.id()).toList();
		return "Camino desde 0 hasta 0:\n" + ids + "\nKms: " + kms + "\nBeneficio: " + benef;
	}
	
}
