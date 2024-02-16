package soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import datos.DatosEjercicioInvestigadores;
import datos.DatosEjercicioInvestigadores.Investigador;

public class SolucionInvestigadores {

	public static SolucionInvestigadores of_Range(List<Integer> value) {
		return new SolucionInvestigadores(value);
	}

	private Integer calidad;
	private List<Investigador> investigadores;
	private List<List<Integer>> horas;

	private SolucionInvestigadores() {
		calidad = 0;
		investigadores = new ArrayList<>();
		horas = new ArrayList<>();
	}

	private SolucionInvestigadores(List<Integer> ls) {
		Integer numeroInvestigadores = DatosEjercicioInvestigadores.getNumeroInvestigadores();
		Integer numeroTrabajos = DatosEjercicioInvestigadores.getNumeroTrabajos();
		Integer numeroEspecialidades = DatosEjercicioInvestigadores.getNumeroEspecialidades();
		calidad = 0;
		investigadores = new ArrayList<>();
		investigadores.addAll(DatosEjercicioInvestigadores.investigadores);
		horas = new ArrayList<>();

		for (int i = 0; i < numeroInvestigadores; i++) {
			horas.add(new ArrayList<>());
		}
		for (int j = 0; j < numeroTrabajos; j++) {
			Integer jj = j * numeroInvestigadores;
			List<Integer> trab = ls.subList(jj, jj + numeroInvestigadores);
			for (int i = 0; i < numeroInvestigadores; i++) {
				horas.get(i).add(trab.get(i));
			}
			Boolean realiza = true;
			for (int k = 0; k < numeroEspecialidades; k++) {
				Integer suma = 0;
				for (int i = 0; i < numeroInvestigadores; i++) {
					suma += trab.get(i) * DatosEjercicioInvestigadores.trabajadorEspecialidad(i, k);
				}
				if (suma < DatosEjercicioInvestigadores.diasNecesarios(j, k)) {
					realiza = false;
					k = numeroEspecialidades;
				}
			}
			if (realiza) {
				calidad += DatosEjercicioInvestigadores.getCalidad(j);
			}
		}
	}

	public static SolucionInvestigadores empty() {
		return new SolucionInvestigadores();
	}

	public String toString() {
		String str = investigadores.stream().map(i -> "INV" + (i.id() + 1) + ": " + horas.get(i.id()))
				.collect(Collectors.joining("\n",
						"Reparto obtenido (dias trabajados por cada investigador en cada trabajo):\n", "\n"));
		return String.format("%sSUMA DE LAS CALIDADES DE LOS TRABAJOS REALIZADOS: %d", str, calidad);
	}

}
