package _soluciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicioInvestigadores;

public class SolucionInvestigadores {

	public static SolucionInvestigadores of(List<Integer> value) {
		return new SolucionInvestigadores(value);
	}
	
	private Map<String, List<Integer>> solucion;
	
	private SolucionInvestigadores(List<Integer> value) {
		
		System.out.println(value);
		
		solucion = new HashMap<>();
		
		Integer m = DatosEjercicioInvestigadores.getNumeroTrabajos();
		Integer n = DatosEjercicioInvestigadores.getNumeroInvestigadores();
		
		for(int i = 0; i < n; i++) {
			String key = String.format("INV%s", i);
			List<Integer> diasTrabajador = new ArrayList<>();
			for(int j = 0; j < m; j++) {
				diasTrabajador.add(value.get(m*i + j));
			}
			solucion.put(key, diasTrabajador);
		}
		
	}
	
	public String toString() {
		System.out.println("Reparto obtenido (dÃ­as trabajados por cada investigador en cada trabajo):");
		for(int i = 0; i < solucion.keySet().size(); i++) {
			String key = String.format("INV%s", i);
			List<Integer> inv = solucion.get(key);
			System.out.println(String.format("%s: %s", key, inv));
		}
		return "------------------------------";
	}
	
	
}
