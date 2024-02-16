package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ejemplos.Ejemplo4;
import ejemplos.Ejemplo5;
import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class TestEjemplosArboles {

	public static void main(String[] args) {
		//testEjemplo4();
		testEjemplo5();

	}
	
	private static List<Character> stringToCharList(String s){
		String aux = s.replace("[", "").replace(",", "").replace("]", "");
		List<Character> res = new ArrayList<>();
		for(int i=0; i< aux.length(); i++) {
			res.add(aux.charAt(i));
		}
		return res;
	}
	
	public static void testEjemplo4() {
		String file = "ficheros/Ejemplo4DatosEntrada.txt";
		
		List<Pair<BinaryTree<Character>,List<Character>>> inputs =
				Files2.streamFromFile(file)
				.map(linea ->{
						String[] aux = linea.split("#");
						BinaryTree<Character> t =
								BinaryTree.parse(aux[0], s->s.charAt(0));
						List<Character> charList = stringToCharList(aux[1]);
						return Pair.of(t, charList);
				}
		).toList();
		
		for(Pair<BinaryTree<Character>, List<Character>> par:inputs) {
			BinaryTree<Character> t = par.first();
			List<Character> ls = par.second();
			System.out.println("Arbol:" + t +
					"\tSecuencia: "+ls+
					"\tResultado: " + Ejemplo4.solucion_recursiva(t, ls));
		}
	}
	
	
	public static void testEjemplo5() {
		String file = "ficheros/Ejemplo5DatosEntrada.txt";
		List<Tree<Integer>> inputs = Files2.streamFromFile(file)
			.map(linea -> Tree.parse(linea, s-> Integer.parseInt(s)))
			.toList();
		Predicate<Integer> pred = x->x%2==0;
		for(Tree<Integer> tree:inputs) {
			System.out.println("Arbol:" + tree +
					"\tResultado: " + Ejemplo5.solucion_recursiva(tree, pred));
		}
	}

}
