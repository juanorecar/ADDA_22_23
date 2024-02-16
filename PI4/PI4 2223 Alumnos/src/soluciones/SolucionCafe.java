package soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import datos.DatosEjercicioCafes;
import datos.DatosEjercicioCafes.Variedad;

public class SolucionCafe {

	public static SolucionCafe of_Range(List<Integer> value) {
		return new SolucionCafe(value);
	}

	private Double beneficio;
	private List<Variedad> lsVariedades;
	private List<Integer> solucion;

	private SolucionCafe() {
		beneficio = 0.;
		lsVariedades = new ArrayList<>();
		solucion = new ArrayList<>();
	}

	private SolucionCafe(List<Integer> ls) {
		beneficio = 0.;
		lsVariedades = new ArrayList<>();
		solucion = new ArrayList<>();

		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer kilos = ls.get(i);
				Integer benef = DatosEjercicioCafes.getBeneficio(i) * kilos; // Benefvar = benef de cada variedad por la
																			// cantidad que hay
				lsVariedades.add(DatosEjercicioCafes.getVariedades().get(i));
				solucion.add(ls.get(i));
				beneficio += benef; // Guardamos en beneficio los benefvar que se consiguen
			}
		}
	}

	public static SolucionCafe empty() {
		return new SolucionCafe();
	}

	// LE DAMOS FORMA AL LA SALIDA POR CONSOLA:

	public String toString() {
		String str = lsVariedades.stream().map(v -> "P" + (v.id() + 1) + ": " + solucion.get(lsVariedades.indexOf(v)))
				.collect(Collectors.joining(" Kgs\n", "Variedades de cafe seleccionadas:\n", " Kg\n"));
		return String.format("%sBeneficio: %.1f", str, beneficio);
	}
}
