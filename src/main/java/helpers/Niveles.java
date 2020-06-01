package helpers;

import java.util.ArrayList;
import java.util.List;

public class Niveles {

    private static Niveles niveles;

    private List<Integer> nivelesPunto;

    private Niveles(){
        nivelesPunto = new ArrayList<>();

        int puntos = 0;
        int maxLevel = 30;
        for(int i = 0; i < maxLevel; i++ ) {
            if(i<3)
                puntos += 10;
            else if(i<6)
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


    public int getNivelPorPuntos(int puntos){
        int lvl= 0;
        for (Integer p: nivelesPunto){
            if(puntos >= p) lvl++;
        }
        return lvl;
    }

}
