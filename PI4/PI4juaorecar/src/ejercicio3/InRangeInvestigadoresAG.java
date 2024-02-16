package ejercicio3;

import java.util.List;

import datos.DatosEjercicioInvestigadores;
import soluciones.SolucionInvestigadores;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeInvestigadoresAG implements ValuesInRangeData<Integer, SolucionInvestigadores> {

	public InRangeInvestigadoresAG(String fich) {
		DatosEjercicioInvestigadores.iniDatos(fich);
	}

	@Override
	public Integer max(Integer i) {
		Integer num = i % DatosEjercicioInvestigadores.getNumeroInvestigadores(); // Imod N
		return DatosEjercicioInvestigadores.diasDisponibles(num) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	@Override
	public Integer size() {
		return DatosEjercicioInvestigadores.getNumeroInvestigadores() * DatosEjercicioInvestigadores.getNumeroTrabajos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls_chrm) {

		double goal = 0, error = 0, k_k = 0, capacidad = 0;

		Integer nInvest = DatosEjercicioInvestigadores.getNumeroInvestigadores();
		Integer nTrabaj = DatosEjercicioInvestigadores.getNumeroTrabajos();
		Integer nEspec = DatosEjercicioInvestigadores.getNumeroEspecialidades();

		for (int j = 0; j < nTrabaj; j++) {

			Integer jnInvest = j * nInvest;
			List<Integer> trabajs = ls_chrm.subList(jnInvest, jnInvest + nInvest);
			Boolean realiza = true;

			for (int k = 0; k < nEspec; k++) {

				Integer suma = 0;

				for (int i = 0; i < nInvest; i++) {
					suma += trabajs.get(i) * DatosEjercicioInvestigadores.trabajadorEspecialidad(i, k);
				}

				if (suma != DatosEjercicioInvestigadores.diasNecesarios(j, k)) {
					realiza = false;
					error += Math.abs(suma - DatosEjercicioInvestigadores.diasNecesarios(j, k));
				}
			}
			if (realiza) {
				goal += DatosEjercicioInvestigadores.getCalidad(j);
			}
		}
		for (int i = 0; i < nInvest; i++) {
			capacidad = 0;

			for (int i_i = i; i_i < ls_chrm.size(); i_i += nInvest) {
				capacidad += ls_chrm.get(i_i);
			}

			if (capacidad > DatosEjercicioInvestigadores.diasDisponibles(i)) {
				error += capacidad - DatosEjercicioInvestigadores.diasDisponibles(i);
			}
		}

		Integer suma = 0;
		for (int j = 0; j < nTrabaj; j++) {
			suma += DatosEjercicioInvestigadores.getCalidad(j);
		}
		k_k = Math.pow(suma, 2);

		return goal - k_k * error;
	}

	@Override
	public SolucionInvestigadores solucion(List<Integer> ls_chrm) {
		System.out.println(ls_chrm);
		return SolucionInvestigadores.of_Range(ls_chrm);
	}

}
