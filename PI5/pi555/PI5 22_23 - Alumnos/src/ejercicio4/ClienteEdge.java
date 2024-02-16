package ejercicio4;

import _datos.DatosEjercicioClientes;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ClienteEdge(ClienteVertex source, ClienteVertex target, Integer action,
		Double weight) implements SimpleEdgeAction<ClienteVertex, Integer> {
	
	public static ClienteEdge of(ClienteVertex s, ClienteVertex t, Integer a) {
		Double w = DatosEjercicioClientes.getBeneficio(a) - t.kms();
		return new ClienteEdge(s, t, a, w);
	}

}
