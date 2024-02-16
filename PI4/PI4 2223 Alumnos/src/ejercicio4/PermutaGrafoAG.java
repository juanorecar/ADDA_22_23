package ejercicio4;

import java.util.List;

import datos.DatosEjercicioClientes;
import soluciones.SolucionClientes;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutaGrafoAG implements SeqNormalData<SolucionClientes> {

	public PermutaGrafoAG(String fich) {
		DatosEjercicioClientes.iniDatos(fich);
	}

	@Override
	public Integer itemsNumber() {
		return DatosEjercicioClientes.getNVertices();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls_chrm) {
		double goal = 0, error = 0, k = 0, suma = 0;

		for (int i = 0; i < ls_chrm.size(); i++) {
			if (i == 0) {
				if (DatosEjercicioClientes.existeArista(0, ls_chrm.get(i))) {
					suma += DatosEjercicioClientes.getPeso(0, ls_chrm.get(i));
					goal += DatosEjercicioClientes.getBeneficio(ls_chrm.get(i)) - suma;
				} else {
					error++; // SI NO EXISTE ARISTA PENALIZAMOS
				}
			} else {
				if (DatosEjercicioClientes.existeArista(ls_chrm.get(i - 1), ls_chrm.get(i))) {
					suma += DatosEjercicioClientes.getPeso(ls_chrm.get(i - 1), ls_chrm.get(i));
					goal += DatosEjercicioClientes.getBeneficio(ls_chrm.get(i)) - suma;
				} else {
					error++;
				}
			}
		}
		if (ls_chrm.get(ls_chrm.size() - 1) != 0) { // SI ULTIMO VERTICE != 0
			if (error == 0) { // SI PENALIZACION = 0 LA HACEMOS = 2
				error += 2;
			} else { // SI PENALIZACION != 0 LA MULTIPLICMAOS POR 2
				error = error * 2;
			}
		}
		suma = 0.;
		for (int i = 0; i < ls_chrm.size(); i++) {
			suma += DatosEjercicioClientes.getBeneficio(ls_chrm.get(i));
		}

		k = Math.pow(suma, 2);

		return goal - k * error;
	}

	@Override
	public SolucionClientes solucion(List<Integer> ls_chrm) {
		return SolucionClientes.of_Rnage(ls_chrm);
	}

}
