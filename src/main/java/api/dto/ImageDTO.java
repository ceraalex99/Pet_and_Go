package api.dto;

public class ImageDTO {
    private byte[] imagen ;


    public ImageDTO() {
    }

    public ImageDTO(byte[] imagen) {
       this.imagen = imagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
