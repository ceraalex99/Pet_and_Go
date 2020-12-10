package domain.services;

import domain.models.Quedada;

public class Posicion {

    private double latitud;
    private double longitud;

    public Posicion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Posicion(Quedada q) {
        this.latitud = q.getLatitud();
        this.longitud = q.getLongitud();
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


}
