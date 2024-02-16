package soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import datos.DatosEjercicioCursos;
import datos.DatosEjercicioCursos.Curso;

public class SolucionCursos {

	public static SolucionCursos of_Range(List<Integer> value) {
		return new SolucionCursos(value);
	}

	private Double precio;
	private List<Curso> cursos;

	public SolucionCursos() {
		precio = 0.;
		cursos = new ArrayList<>();
	}

	public SolucionCursos(List<Integer> ls) {
		precio = 0.;
		cursos = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				precio += DatosEjercicioCursos.getPrecioCurso(i);
				cursos.add(DatosEjercicioCursos.cursos.get(i));
			}
		}
	}

	public static SolucionCursos empty() {
		return new SolucionCursos();
	}

	public String toString() {
		String str = cursos.stream().map(c -> "S" + c.id())
				.collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		return String.format("%sCoste total: %.1f", str, precio);
	}

}
