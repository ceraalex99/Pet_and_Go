package helpers;

import java.util.List;
import java.util.Optional;

public class Niveles {

    private static Niveles niveles;
    private static int MAXlevel = 30;

    private static List<Integer> nivelesPunto;

    private Niveles(){
        int puntos = 0;
        for(int i = 0; i < MAXlevel;i++ ) {
            if(i<3)
                puntos += 10;
            if(i<6)
                puntos += 20;
            else
                puntos += 40;
            nivelesPunto.add(puntos);
        }
    }

    public static  Niveles getInstance(){
        if(niveles == null)
            niveles = new Niveles();
        return niveles;
    }


    public static int getNivelPorPuntos(int puntos){
        Optional<Integer> puntosNivelMinimo = nivelesPunto.stream().filter(n -> n >= puntos).findFirst();
        if(puntosNivelMinimo.isPresent())
            return puntosNivelMinimo.get();
        else
            return 0;
    }

}
