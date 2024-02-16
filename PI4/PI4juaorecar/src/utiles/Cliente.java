package utiles;

public record Cliente(int id, Double beneficio) {

	public static Cliente of(int id, Double beneficio) {
		return new Cliente(id, beneficio);
	}
	
	public static Cliente ofFormat(String[] forma) {
		Integer id = Integer.valueOf(forma[0].trim());
		Double beneficio = Double.valueOf(forma[1].trim());
		return of(id, beneficio);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", beneficio=" + beneficio + "]";
	}
	
}
