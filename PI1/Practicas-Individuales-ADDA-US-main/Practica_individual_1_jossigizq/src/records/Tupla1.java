package records;

public record Tupla1(Integer varA, String varB) {
	
	/*
	 *  Tupla1 es el equivalente a EnteroCadena
	 */

	public static Tupla1 of(Integer varA, String varB) {
		return new Tupla1(varA, varB);
	}
			
	public Tupla1 next() {
				
		Integer varA_out = this.varA() + 2;
	
		if(this.varA() % 3 == 0) {
			String varB_out = this.varB() + this.varA().toString();
			return Tupla1.of(varA_out, varB_out);
		} else {
			String varB_out = this.varB().substring(this.varA() % this.varB().length());
			return Tupla1.of(varA_out, varB_out);
		}
				

	}

}
