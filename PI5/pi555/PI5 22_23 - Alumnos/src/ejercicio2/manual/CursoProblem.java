package ejercicio2.manual;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import _datos.DatosEjercicioCafes;
import _datos.DatosEjercicioCursos;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public record CursoProblem(Integer index, Set<Integer> remaining, Set<Integer> centros) {

	public static CursoProblem of(Integer i, Set<Integer> rest, Set<Integer> cen) {
		return new CursoProblem(i, rest, cen);
	}
	
	public static CursoProblem initial() {
		return of(0, Set2.copy(DatosEjercicioCursos.getTematicas()), new HashSet<>());
	}
	
	public List<Integer> actions() {
		
		 List<Integer> alternativas = List2.empty();
		
		if(index<DatosEjercicioCursos.getNumeroCursos()) {
			if(remaining.isEmpty()) {
				alternativas= List2.of(0);
			}else {
				Set<Integer> restantesActualizados= Set2.difference(remaining,
				DatosEjercicioCursos.getTematicasCursos(index));
		
				if(index==DatosEjercicioCursos.getNumeroCursos()-1) {
		
					if(centros.contains(DatosEjercicioCursos.getCentroCurso(index)) ||
							(centros.size()<DatosEjercicioCursos.maxCentros)) {
						alternativas= restantesActualizados.isEmpty()? List2.of(1): List2.of(0);
					}
		
		}else if(restantesActualizados.equals(remaining))
		{
		alternativas = List2.of(0);
		
		}else {
		
			if(centros.contains(DatosEjercicioCursos.getCentroCurso(index)) ||
					(centros.size()<DatosEjercicioCursos.maxCentros)) {
				alternativas= List2.of(0);
				alternativas.add(1);
			}else {
				alternativas= List2.of(0);
			}
		   }
		  }
		 }
		return alternativas;
		}
		
		
		public CursoProblem neighbor(Integer a) {
		Set<Integer> rest1 = a ==0? Set2.copy(remaining):
		Set2.difference(remaining,
				DatosEjercicioCursos.getTematicasCursos(index));
		Set<Integer> centro = Set2.copy(centros);
		if(a==1) {
		centro.add(DatosEjercicioCursos.getCentroCurso(index));
		}
		
		return of(index+1, rest1, centro);
		}
	
	
	public Double heuristic() {
		return remaining.isEmpty()? 0.: 
			IntStream.range(index, DatosEjercicioCafes.getNumeroVariedades())
			.filter(i -> !List2.intersection(remaining, 
					DatosEjercicioCursos.getTematicasCursos(i)).isEmpty())
			.mapToDouble(i -> DatosEjercicioCursos.getPrecioCurso(i)).min().orElse(100.);
		
		
	}
	
	
	
}
