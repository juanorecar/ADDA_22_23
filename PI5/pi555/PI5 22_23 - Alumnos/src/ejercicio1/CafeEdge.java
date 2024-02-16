package ejercicio1;

import _datos.DatosEjercicioCafes;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CafeEdge(CafeVertex source, CafeVertex target, Integer action,
		Double weight) implements SimpleEdgeAction<CafeVertex, Integer> {
	public static CafeEdge of(CafeVertex s, CafeVertex t, Integer a) {
		// TODO La arista debe tener peso 
		return new CafeEdge(s, t, a, Double.valueOf(a * DatosEjercicioCafes.getBeneficio(s.index()))); 
	}

}
