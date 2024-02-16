package ejercicio2;

import _datos.DatosEjercicioCursos;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CursoEdge(CursoVertex source, CursoVertex target, Integer action,
		Double weight) implements SimpleEdgeAction<CursoVertex, Integer> {
	
	public static CursoEdge of(CursoVertex s, CursoVertex t, Integer a) {
		return new CursoEdge(s,t,a, a* DatosEjercicioCursos.getPrecioCurso(s.index()));
	}
	
}
