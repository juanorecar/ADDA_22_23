package tests;

import java.util.List;
import ejercicios.Ejercicio4;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;


public class TestEjercicio4 {

	public static void main(String[] args) {
		testEjercicio4Binario();
		//testEjercicio4Nario();
	}

	/*
	 * TEST EJERCICIO 4 BINARIO
	 */
	
	public static void testEjercicio4Binario() {
		String file = "ficheros/Ejercicio4DatosEntradaBinario.txt";
		List<BinaryTree<String>> inputs = Files2.streamFromFile(file)
			.map(linea -> BinaryTree.parse(linea))
			.toList();
		
		for(BinaryTree<String> tree:inputs) {
			System.out.println("Arbol:" + tree +
					"\tResultado: " + Ejercicio4.solucion_recursivaBinaria(tree));
		}
	}
	
	/*
	 * TEST EJERCICIO 4 NARIO
	 */
	
	public static void testEjercicio4Nario() {
		String file = "ficheros/Ejercicio4DatosEntradaNario.txt";
		List<Tree<String>> inputs = Files2.streamFromFile(file)
			.map(linea -> Tree.parse(linea))
			.toList();
		
		for(Tree<String> tree:inputs) {
			System.out.println("Arbol:" + tree +
					"\tResultado: " + Ejercicio4.solucion_recursivaNaria(tree));
		}
	}
	
	
}
