package modelo;

public class Noticia {
    String titulo;
    String texto;
    String imagen;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Noticia(String titulo, String texto, String imagen) {
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
    }
}
