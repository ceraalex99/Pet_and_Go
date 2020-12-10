package domain.services;

public class CalculadoraDistancia {

    private CalculadoraDistancia(){
        throw new IllegalStateException("Utility class");
    }

   static final double RADIO_TIERRA = 6378;


    public static double getDistanciaKMetros(Posicion p1, Posicion p2){
        double distanciLatitud = Math.toRadians(p2.getLatitud() - p1.getLatitud());
        double distanciLongitud = Math.toRadians(p2.getLongitud() - p1.getLongitud());

        double a = Math.pow(Math.sin(distanciLatitud/2),2) + Math.cos(Math.toRadians(p1.getLatitud())) * Math.cos(Math.toRadians(p2.getLatitud())) * Math.pow(Math.sin(distanciLongitud/2),2);

        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return RADIO_TIERRA * c;
    }

    public static int getDistanciaMetros(Posicion p1, Posicion p2){
        double metros = getDistanciaKMetros(p1,p2)  * 1000;
        return (int) metros;
    }


}
