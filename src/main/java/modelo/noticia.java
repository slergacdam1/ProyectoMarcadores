package modelo;

public class noticia {
    String titulo;
    String texto;

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

    public noticia(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }
}
