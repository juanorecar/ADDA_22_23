package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ejercicio1 {
	
	public record EnteroCadena(Integer a, String s) {
		public static EnteroCadena of(Integer a, String s) {
			return new EnteroCadena(a, s);
		}
		public EnteroCadena nx() {
			EnteroCadena tupla = null;
			
			if(a()%3==0) {
				tupla = of(a()+2, s()+a().toString());
			}else {
				tupla = of(a()+2, s().substring(a()%s().length()));
			}
			return tupla;
		}
	}
		
	public static Map<Integer,List<String>> ejercicio1 (Integer varA, String varB, Integer varC, String
			varD, Integer varE) {
			UnaryOperator<EnteroCadena> nx = elem ->
			{
			return EnteroCadena.of(elem.a()+2,
					elem.a()%3==0?
					elem.s()+elem.a().toString():
					elem.s().substring(elem.a()%elem.s().length()));
			};
			
			return Stream.iterate(EnteroCadena.of(varA,varB), elem -> elem.a() < varC, nx)
					.map(elem -> elem.s()+varD)
					.filter(nom -> nom.length() < varE)
					.collect(Collectors.groupingBy(String::length));
			}
	
	
	
	
	public static Map<Integer, List<String>> ejercicio1Iterativo(Integer varA, String varB, Integer varC, String
			varD, Integer varE){
		Map<Integer, List<String>> ac = new HashMap<>();
		EnteroCadena e = EnteroCadena.of(varA, varB);

		while(e.a()<varC) {
			String p = e.s()+varD;
			if(p.length()<varE) {
				
				Integer key = p.length();
				if(ac.containsKey(key)) {
					List<String> ls = ac.get(key);
					ls.add(p);
					ac.put(key, ls);
				}else {
					List<String> ls = new ArrayList<>();
					ls.add(p);
					ac.put(key, ls);
				}
			}
			e = e.nx();
		}
		return ac;
	}
	
	
	public static Map<Integer, List<String>> ejercicio1Recursivo(Integer varA, String varB, Integer varC, String
			varD, Integer varE){
		return ejercicio1RecursivoAux(varA, varB, varC, varD,  varE, new HashMap<>() );
	}

	private static Map<Integer, List<String>> ejercicio1RecursivoAux(Integer varA, String varB, Integer varC,
			String varD, Integer varE, Map<Integer, List<String>> ac) {
			
		EnteroCadena e = EnteroCadena.of(varA, varB);
		
		if(e.a()<varC) {
			String p = e.s()+varD;
			if(p.length()<varE) {
				Integer key = p.length();
				if(ac.containsKey(key)) {
					List<String> ls = ac.get(key);
					ls.add(p);
					ac.put(key, ls);
					e = e.nx();
					return ejercicio1RecursivoAux(e.a(), e.s(), varC, varD, varE, ac);
				}else {
					List<String> ls = new ArrayList<>();
					ls.add(p);
					ac.put(key, ls);
					e = e.nx();
					return ejercicio1RecursivoAux(e.a(), e.s(), varC, varD, varE, ac);
				}
			}
			
		}
		return ac;
	}
}
