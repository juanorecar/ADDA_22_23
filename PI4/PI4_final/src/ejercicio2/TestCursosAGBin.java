package ejercicio2;

import java.util.List;
import java.util.Locale;
import soluciones.SolucionCursos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestCursosAGBin {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
				
		for (int i = 1; i < 4; i++) {
			
			System.out.println("\n\n#####################################################################################");
			System.out.println("SOLUCION EJERCICIO 2 CON DATOS DE ENTRADA " + i);
			System.out.println("#####################################################################################\n");


			BinCursosAG p = new BinCursosAG("ficheros/Ejercicio2DatosEntrada" + i + ".txt");

			AlgoritmoAG<List<Integer>, SolucionCursos> ap = AlgoritmoAG.of(p);
			ap.ejecuta();

			System.out.println("================================");
			System.out.println(ap.bestSolution());
			System.out.println("================================");

		}
	}
}
