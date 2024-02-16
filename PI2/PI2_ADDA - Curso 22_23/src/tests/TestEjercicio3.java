package tests;

import java.util.List;
import ejercicios.Ejercicio3;
import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class TestEjercicio3 {

	public static void main(String[] args) {
		testEjericio3Binario();
		//testEjericio3Nario();

	}

	public static void testEjericio3Binario() {
		String file = "ficheros/Ejercicio3DatosEntradaBinario.txt";
		
		List<Pair<BinaryTree<Character>, Character>> inputsBinario =
				Files2.streamFromFile(file)
				.map(linea ->{
						String[] aux = linea.split("#");
						BinaryTree<Character> t =
								BinaryTree.parse(aux[0], s->s.charAt(0));
						Character c = aux[1].charAt(0);
						return Pair.of(t, c);
				}
		).toList();
		
		
		for(Pair<BinaryTree<Character>, Character> par:inputsBinario) {
			BinaryTree<Character> t = par.first();
			Character c = par.second();
			System.out.println("Arbol:" + t + "\tCaracter:" + c +
					"\tResultado: " + Ejercicio3.solucion_recursivaBinario(t, c));
		}
	}
	
	public static void testEjericio3Nario() {
		String file = "ficheros/Ejercicio3DatosEntradaNario.txt";
		
		List<Pair<Tree<Character>, Character>> inputsNario =
				Files2.streamFromFile(file)
				.map(linea ->{
						String[] aux = linea.split("#");
						Tree<Character> t =
								Tree.parse(aux[0], s->s.charAt(0));
						Character c = aux[1].charAt(0);
						return Pair.of(t, c);
				}
		).toList();
		
		
		for(Pair<Tree<Character>, Character> par:inputsNario) {
			Tree<Character> t = par.first();
			Character c = par.second();
			System.out.println("Arbol:" + t + "\tCaracter:" + c +
					"\tResultado: " + Ejercicio3.solucion_recursivaNario(t, c));
		}
	}
	
	
	
}
