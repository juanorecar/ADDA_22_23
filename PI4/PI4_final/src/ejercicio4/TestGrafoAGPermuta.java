package ejercicio4;

import java.util.List;
import java.util.Locale;
import soluciones.SolucionClientes;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestGrafoAGPermuta {

	public static void main(String[] args) {
		
		
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;

		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

		for (int i = 1; i < 3; i++) {
			
			System.out.println("\n\n#####################################################################################");
			System.out.println("SOLUCION EJERCICIO 4 CON DATOS DE ENTRADA " + i);
			System.out.println("#####################################################################################\n");

			PermutaGrafoAG p = new PermutaGrafoAG("ficheros/Ejercicio4DatosEntrada" + i + ".txt");

			AlgoritmoAG<List<Integer>, SolucionClientes> ap = AlgoritmoAG.of(p);
			ap.ejecuta();

			System.out.println("================================");
			System.out.println(ap.bestSolution());
			System.out.println("================================");

		}
	}
}
