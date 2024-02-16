package ejercicio2;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import _datos.DatosEjercicioCursos;
import us.lsi.common.List2;

public class CursoHeuristic {

	public static Double heuristic(CursoVertex v1, Predicate<CursoVertex> goal,
			CursoVertex v2) {

		return v1.remaining().isEmpty()? 0.: 
			IntStream.range(v1.index(), DatosEjercicioCursos.getNumeroCursos())
			.filter(i -> !List2.intersection(v1.remaining(), 
					DatosEjercicioCursos.getTematicasCursos(i)).isEmpty())
			.mapToDouble(i -> DatosEjercicioCursos.getPrecioCurso(i)).min().orElse(100.);
		
	}
	
	
	
	
}
