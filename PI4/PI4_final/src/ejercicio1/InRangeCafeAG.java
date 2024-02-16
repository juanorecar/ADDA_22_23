package ejercicio1;

import java.util.List;
import datos.DatosEjercicioCafes;
import soluciones.SolucionCafe;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeCafeAG implements ValuesInRangeData<Integer, SolucionCafe> {

	public InRangeCafeAG(String fichero) {
		DatosEjercicioCafes.iniDatos(fichero);
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicioCafes.getCantidadMaxima(i) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	@Override
	public Integer size() {
		return DatosEjercicioCafes.getNumeroVariedades();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, dif = 0, k = 0;
		for (int i = 0; i < size(); i++) {
			if (ls.get(i) > 0) {
				goal += ls.get(i) * DatosEjercicioCafes.getBeneficio(i); // GOAL DEL PROBLEMA, BENEF DE VARIEDAD * LA VARIEDAD
			}
		}
		for (int j = 0; j < DatosEjercicioCafes.getNumeroTipos(); j++) {
			dif = 0;
			for (int i = 0; i < size(); i++) { // RESTRICCION: DEBEMOS TENER CANTIDAD DISPONIBLE DE CADA TIPO
				dif += ls.get(i) * DatosEjercicioCafes.getCantidadTipoVariedad(j, i); 
			}
			if (dif > DatosEjercicioCafes.getCantidad(j)) {
				error += dif - DatosEjercicioCafes.getCantidad(j);
			}
		}
		for (int i = 0; i < size(); i++) { // CALCULO DE K (A CONTINUACION LO MULTIPLICAMOS POR EL ERROR PARA QUE ESTE SEA GRANDE) 
			k += Math.pow(DatosEjercicioCafes.getCantidadMaxima(i) * DatosEjercicioCafes.getBeneficio(i), 2);
		}
		return goal - k * error;
	}

	@Override
	public SolucionCafe solucion(List<Integer> ls_chrm) {
		return SolucionCafe.of_Range(ls_chrm);
	}

}
