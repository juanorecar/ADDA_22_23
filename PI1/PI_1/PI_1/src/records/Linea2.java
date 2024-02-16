package records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Linea2(Integer a, Integer b, String s) {

	public static Linea2 of(Integer a, Integer b, String s) {
		
		return new Linea2(a,b,s);
		
	}
	
	public static Linea2 of2(String a, String b, String s) {
		
		return new Linea2(Integer.parseInt(a),Integer.parseInt(b),s);	
		
	}
	
	public static Linea2 parse(String linea) {
		List<String> aux = Arrays.stream(linea.split(",")).collect(Collectors.toList());
		return Linea2.of2(aux.get(0), aux.get(1), aux.get(2));
	}
	
}
