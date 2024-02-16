package datos;

public record Familia(Integer id) {
	
	private static int num =0;
	
	public static Familia of() {
		Integer id = num;
		num++;
		return new Familia(id);
	}
	public static Familia ofFormat(String[] formato) {
		return Familia.of();
	}
	
}
