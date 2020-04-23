package api.dto;

public class imageDTO {
    private byte[] imagen ;


    public imageDTO() {
    }

    public imageDTO(byte[] imagen) {
       this.imagen = imagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
