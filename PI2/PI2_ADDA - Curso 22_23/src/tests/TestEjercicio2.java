package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;
import ejercicios.Ejercicio2;
import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Pair;
import us.lsi.common.Trio;
import us.lsi.curvefitting.DataCurveFitting;
import utils.FicherosListas;
import utils.GraficosAjuste;
import utils.Resultados;
import utils.TipoAjuste;
import utils.FicherosListas.PropiedadesListas;

public class TestEjercicio2 {
	// Parámetros de generación de las listas
		private static Integer sizeMin = 100; // tamaño mínimo de lista
		private static Integer sizeMax = 10000; // tamaño máximo de lista
		private static Integer numSizes = 50; // número de tamaños de listas
		private static Integer minValue = 0; 
		private static Integer maxValue = 1000000;
		private static Integer numListPerSize = 1; // número de listas por cada tamaño  (UTIL???) 
		private static Integer numCasesPerList = 30; // número de casos (elementos a buscar) por cada lista 
		private static Random rr = new Random(System.nanoTime()); // para inicializarlo una sola vez y compartirlo con los métodos que lo requieran

		// Parámetros de medición
		private static Integer numMediciones = 5; // número de mediciones de tiempo de cada caso (número de experimentos)
		private static Integer numIter = 50; // número de iteraciones para cada medición de tiempo
		private static Integer numIterWarmup = 100000; // número de iteraciones para warmup
		
		public static void main(String[] args) {
			// Generación de Listas
			PropiedadesListas props = PropiedadesListas.of(sizeMin, sizeMax, numSizes, minValue, maxValue,numListPerSize, numCasesPerList, rr);
			generaFicherosDatos(props);
			
			generaFicherosTiempoEjecucion(metodos);
			muestraGraficas();
		}
		
		private static List<Trio<BiConsumer<List<Integer>, Integer>, TipoAjuste, String>> metodos = 
				List.of(
						Trio.of(Ejercicio2::sort, TipoAjuste.NLOGN, "Sort")
					
				);
	
		
		public static void muestraGraficas() {
			System.out.println("a*n^b*(ln n)^c + d");
			List<Integer> umbrales = List.of(4, 20, 100, 500);
			List<String> ficherosSalida = new ArrayList<>();
			List<String> labels = new ArrayList<>();
			for (int i=0; i<umbrales.size(); i++) { 
				
				String ficheroSalida = String.format("ficheros/Tiempos%s_%d.csv",
						metodos.get(0).third(),umbrales.get(i));
				ficherosSalida.add(ficheroSalida);
				String label = metodos.get(0).third() + umbrales.get(i);
				System.out.println(label);
				
				TipoAjuste tipoAjuste = metodos.get(0).second();
				GraficosAjuste.show(ficheroSalida, tipoAjuste, label);

				// Obtener ajusteString para mostrarlo en gráfica combinada
				Pair<Function<Double, Double>, String> parCurve = GraficosAjuste.fitCurve(
						DataCurveFitting.points(ficheroSalida), tipoAjuste);
				String ajusteString = parCurve.second();
				labels.add(String.format("%s     %s", label, ajusteString));

						
			}

			GraficosAjuste.showCombined("QuickSort", ficherosSalida, labels);
		}

		
		public static void generaFicherosDatos(PropiedadesListas p) {
			Resultados.cleanFiles(List.of("ficheros/Listas.txt",
					"ficheros/ListasOrdenadas.txt",
					"ficheros/ElementosSI.txt",
					"ficheros/ElementosNO.txt",
					"ficheros/ElementosProb_0.txt"));
			
			FicherosListas.generaFicherosDatos(p,"ficheros/Listas.txt",
					"ficheros/ListasOrdenadas.txt",
					"ficheros/ElementosSI.txt",
					"ficheros/ElementosNO.txt",
					"ficheros/ElementosProb.txt",
					0.5
			);
		}
		
		
		private static <E> void generaFicherosTiempoEjecucion(List<Trio<BiConsumer<List<Integer>, Integer>, TipoAjuste, String>> metodos) {
			List<Integer> umbrales = List.of(4, 20, 100, 500);
			for (int i=0; i<umbrales.size(); i++) { 
				String ficheroSalida = String.format("ficheros/Tiempos%s_%d.csv",
						metodos.get(0).third(),umbrales.get(i));
				
				testTiemposEjecucionBusqueda("ficheros/Listas.txt",
							metodos.get(0).first(),
							ficheroSalida,
							umbrales.get(i));
				}
			
		}

		private static void testTiemposEjecucionBusqueda(String ficheroListas, 
				 
				BiConsumer<List<Integer>, Integer> busca,
				String ficheroTiempos,
				Integer umbral) {
			Map<Problema, Double> tiempos = new HashMap<Problema,Double>();
			List<String> lineasListas = Files2.linesFromFile(ficheroListas);
			
			for (int iter=0; iter<numMediciones; iter++) {
				System.out.println(iter);
				for (int i=0; i<lineasListas.size(); i++) { // numSizes*numListPerSize
					String lineaLista = lineasListas.get(i);
					List<String> ls = List2.parse(lineaLista, "#", Function.identity());
					Integer tam = Integer.parseInt(ls.get(0)); 
					List<Integer> le = List2.parse(ls.get(1), ",", Integer::parseInt);
						Problema p = Problema.of(tam);
						warmup(busca, lineasListas.get(0));
						Integer[] res = new Integer[numIter];
						Long t0 = System.nanoTime();
						for (int z=0; z<numIter; z++) {
							busca.accept(le, umbral);
						}
						Long t1 = System.nanoTime();
						actualizaTiempos(tiempos, p, Double.valueOf(t1-t0)/numIter);
					}
				}
			 
			
			Resultados.toFile(tiempos.entrySet().stream()
					.map(x->TResultD.of(x.getKey().tam(), 
										x.getValue()))
					.map(TResultD::toString),
				ficheroTiempos, true);
			}
		
		private static void actualizaTiempos(Map<Problema, Double> tiempos, Problema p, double d) {
			if (!tiempos.containsKey(p)) {
				tiempos.put(p, d);
			} else if (tiempos.get(p) > d) {
					tiempos.put(p, d);
			}
		}


		private static int warmup(BiConsumer<List<Integer>, Integer> busca, String lineaLista) {
			int res=0;
			List<String> ls = List2.parse(lineaLista, "#", Function.identity());
			Integer tam = Integer.parseInt(ls.get(0)); 
			List<Integer> le = List2.parse(ls.get(1), ",", Integer::parseInt);
			
			Integer umbral = 4;
			Integer z = 0; 
			for (int i=0; i<numIterWarmup; i++) {
				busca.accept(le, umbral);
			}
			res = z>0?z+tam:tam;
			return res;
		}



		record TResult(Integer tam, Integer numList, Integer numCase, Long t) {
			public static TResult of(Integer tam, Integer numList, Integer numCase, Long t){
				return new TResult(tam, numList, numCase, t);
			}
			
			public String toString() {
				return String.format("%d,%d,%d,%d", tam, numList, numCase, t);
			}
		}
		
		record TResultD(Integer tam, Double t) {
			public static TResultD of(Integer tam, Double t){
				return new TResultD(tam, t);
			}
			
			public String toString() {
				return String.format("%d,%.0f", tam, t);
			}
		}
		
		record TResultMedD(Integer tam, Double t) {
			public static TResultMedD of(Integer tam, Double t){
				return new TResultMedD(tam, t);
			}
			
			public String toString() {
				return String.format("%d,%.0f", tam, t);
			}
		}
		
		
		record Problema(Integer tam) {
			public static Problema of(Integer tam){
				return new Problema(tam);
			}
		}
}
