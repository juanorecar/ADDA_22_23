package records;

public record Tupla2(Integer a, Integer b, String s, Integer ac) {
	
	public static Tupla2 of(Integer a, Integer b, String s, Integer ac) {
		return new Tupla2(a,b,s,ac);
	}
	
	public Tupla2 next() {
		Tupla2 r = null;
		if(a % s.length() < b % s.length()) {
			r = of(a-1,b/2,s.substring(a % s.length(), b% s.length()), a + b + ac);
		} else {
			r = of(a/2,b-1,s.substring(b%s.length(), a%s.length()), a * b + ac);
		}
		return r;
	}

}
