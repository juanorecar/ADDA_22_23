package ejercicio2;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import _datos.DatosEjercicioCursos;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CursoVertex(Integer index, Set<Integer> remaining, Set<Integer> centros)implements VirtualVertex<CursoVertex, CursoEdge, Integer> {

		public static CursoVertex of(Integer i, Set<Integer> set, Set<Integer> centros) {
		return new CursoVertex(i, set, centros);
		}
		
		public static CursoVertex initial() {
			return of(0, Set2.copy(DatosEjercicioCursos.getTematicas()),Set2.empty());
		}
		
		public static Predicate<CursoVertex> goal() {
		return v-> v.index() == DatosEjercicioCursos.getNumeroCursos();
		}
		
		public static Predicate<CursoVertex> goalHasSolution() {
		return v-> v.remaining().isEmpty();
		}
		
		@Override
		public List<Integer> actions() {
		
		 List<Integer> alternativas = List2.empty();
		
		if(index<DatosEjercicioCursos.getNumeroCursos()) {
			if(remaining.isEmpty()) {
				alternativas= List2.of(0);
			}else {
				Set<Integer> restantes= Set2.difference(remaining,
				DatosEjercicioCursos.getTematicasCursos(index));
		
				if(index==DatosEjercicioCursos.getNumeroCursos()-1) {
		
					if(centros.contains(DatosEjercicioCursos.getCentroCurso(index)) ||
							(centros.size()<DatosEjercicioCursos.maxCentros)) {
						alternativas= restantes.isEmpty()? List2.of(1): List2.of(0);
					}
		
				}else if(restantes.equals(remaining)){
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
		
		
		public CursoVertex neighbor(Integer a) {
		Set<Integer> rest = a ==0? Set2.copy(remaining):
		Set2.difference(remaining, DatosEjercicioCursos.getTematicasCursos(index));
		Set<Integer> centro = Set2.copy(centros);
		if(a==1) {
			centro.add(DatosEjercicioCursos.getCentroCurso(index));
		}
		return of(index+1, rest, centro);
	}
		
		@Override
		public CursoEdge edge(Integer a) {
			return CursoEdge.of(this, neighbor(a), a);
		}
		
		 //el greedy del voraz:
		public CursoEdge greedyEdge() {
		CursoEdge res= null;
		Set<Integer> restantes=
		Set2.difference(remaining, DatosEjercicioCursos.getTematicasCursos(index));
		
		if(centros.contains(DatosEjercicioCursos.getCentroCurso(index)) || (centros.size() < DatosEjercicioCursos.maxCentros)) {
			res= restantes.equals(remaining)? edge(0): edge(1);
		}else {
			res= edge(0);
		}
		return res;
	}
		
		public String toString() {
			return String.format("%d; %d", index, remaining.size());
		 }
		}