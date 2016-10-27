package edu.ufp.inf.lp2.Projecto;

public class Localizacao implements java.io.Serializable {

    private double lat;

    private double longi;
    /**
     * Contrutor da Classe Localizacao.
     * @param lat Latitude.
     * @param longi Longitude.
     */
    public Localizacao(double lat, double longi) {
        this.lat = lat;
        this.longi = longi;
    }

    /**
     * Metodo para obter a latitude da localizacao.
     *
     * @return lat Latitude da Localizacao.
     */
    public double getLat() {
        return lat;
    }

    /**
     * Metodo para atribuir uma latitude à localização.
     *
     * @param lat Latitude da Localizacao.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Metodo para obter a longitude da localizacao.
     *
     * @return longi Longitude da Localização.
     */
    public double getLongi() {
        return longi;
    }

    /**
     * Metodo para atribuir uma longitude à localização.
     *
     * @param longi Longitude da localização.
     */
    public void setLongi(double longi) {
        this.longi = longi;
    }

    @Override
    public String toString() {
        return "Localizacao{" + "lat=" + lat + ", longi=" + longi + '}';
    }

}
