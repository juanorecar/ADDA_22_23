
package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import _datos.DatosEjercicioCursos;
import _datos.DatosEjercicioCursos.Curso;
import ejercicio2.CursoEdge;
import ejercicio2.CursoVertex;


public class SolucionCursos implements Comparable<SolucionCursos> {

		public static SolucionCursos of(List<Integer> ls) {
			return new SolucionCursos(ls);
		}

		// Ahora en la PI5
		public static SolucionCursos of(GraphPath<CursoVertex, CursoEdge> path) {
			List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
			SolucionCursos res = of(ls);
			res.path = ls;
			return res;
		}
		
		private Double precioTotal;
		 private List<Curso> cursos;
		
		// Ahora en la PI5
		private List<Integer> path;
		
		public SolucionCursos() {
			precioTotal = 0.;
			cursos = new ArrayList<>();
		}
		
		public SolucionCursos(List<Integer> ls) {
			precioTotal = 0.;
			cursos = new ArrayList<>();
		
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				precioTotal += DatosEjercicioCursos.getPrecioCurso(i);
				cursos.add(DatosEjercicioCursos.cursos.get(i));
				}
			}
		}
		
		public static SolucionCursos empty() {
			return new SolucionCursos();
		}
		
		
		// Ahora en la PI5
		public String toString() {
		String s = cursos.stream().map(e -> "S" + e.id()) .collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		String res = String.format("%sCoste Total: %.1f", s,precioTotal);
		return path == null ? res : String.format("%s\nPath de la solucion: %s", res, path);
		}
		
		 @Override
		public int compareTo(SolucionCursos o) {
		 return precioTotal.compareTo(o.precioTotal);
		 }

	
}
