
public class Main {
	
    public static void main(String[] args) {
        // Coordenadas de Montevideo
        double latMontevideo = -34.90367464769443;
        double lonMontevideo = -56.21226754075775;

        // Coordenadas de Buenos Aires
        double latBuenosAires = -34.579376602394646;
        double lonBuenosAires = -58.37213306326029;

        // Calcular la distancia
        double distancia = haversine(latMontevideo, lonMontevideo, latBuenosAires, lonBuenosAires);

        System.out.printf("La distancia entre Montevideo y Buenos Aires es aproximadamente %.2f kilometros.%n", distancia);
    }

	public static double haversine(Double lat1, Double long1, Double lat2, Double long2) {
	
        final int R = 6371; // Radio de la Tierra en kilómetros

        // Convertir las coordenadas de grados a radianes
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(long1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(long2);

        // Diferencia de coordenadas
        double dlat = lat2Rad - lat1Rad;
        double dlon = lon2Rad - lon1Rad;

        // Fórmula de la distancia haversine
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }



}
