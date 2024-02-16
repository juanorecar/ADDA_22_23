package ejercicio3;

import _datos.DatosEjercicioInvestigadores;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record InvestigadorEdge(InvestigadorVertex source, InvestigadorVertex target, Integer action,
				Double weight) implements SimpleEdgeAction<InvestigadorVertex, Integer> {
	
	public static InvestigadorEdge of(InvestigadorVertex s, InvestigadorVertex t, Integer a) {
		return new InvestigadorEdge(s, t, a, (double) a * DatosEjercicioInvestigadores.getCalidad(s.z()));
	}

}
