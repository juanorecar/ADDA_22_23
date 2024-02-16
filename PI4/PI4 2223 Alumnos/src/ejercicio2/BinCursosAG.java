package ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.DatosEjercicioCursos;
import soluciones.SolucionCursos;
import us.lsi.ag.BinaryData;

public class BinCursosAG implements BinaryData<SolucionCursos> {

	public BinCursosAG(String fich) {
		DatosEjercicioCursos.iniDatos(fich);
	}

	@Override
	public Integer size() {
		return DatosEjercicioCursos.getNumeroCursos();
	}

	@Override
	public Double fitnessFunction(List<Integer> ls_chrm) {
		double goal = 0, error = 0, k = 0, acum = 0;

		for (int i = 0; i < ls_chrm.size(); i++) {
			if (ls_chrm.get(i) > 0) {
				goal += DatosEjercicioCursos.getPrecioCurso(i);
			}
		}

		Set<Integer> setTem = new HashSet<>();
		Set<Integer> setCent = new HashSet<>();

		for (int i = 0; i < ls_chrm.size(); i++) { 
			if (ls_chrm.get(i) > 0) {
				setTem.addAll(DatosEjercicioCursos.getTematicasCursos(i));
				setCent.add(DatosEjercicioCursos.getCentroCurso(i));
			}
		}

		Integer m = DatosEjercicioCursos.getNumeroTematicas();
		Integer c = DatosEjercicioCursos.maxCentros;
		if (setTem.size() < m) { // RESTRICCION: SELECCION TEMATICAS
			error += m - setTem.size();
		}
		if (setCent.size() > c) { // RESTRICCION: SELECCION CENTROS
			error += setCent.size() - c;
		}

		for (int i = 0; i < ls_chrm.size(); i++) { // FORMA DE CALCULAR K
			acum += DatosEjercicioCursos.getPrecioCurso(i);
		}

		k += Math.pow(acum, 2);
		return -goal -k * error;
	}

	@Override
	public SolucionCursos solucion(List<Integer> ls_chrm) {
		return SolucionCursos.of_Range(ls_chrm);
	}

}
