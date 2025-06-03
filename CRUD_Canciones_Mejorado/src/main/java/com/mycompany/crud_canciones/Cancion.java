/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_canciones;

/**
 *
 * @author pinto
 */
public class Cancion {

    private Integer id;

    private String titulo;

    private String artista;

    private String album;

    private Integer anio;

    private String genero;

    public Cancion(Integer id, String titulo, String artista, String album, Integer anio, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anio = anio;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cancion{" + "id=" + id + ", titulo=" + titulo + ", artista=" + artista + ", album=" + album + ", anio=" + anio + ", genero=" + genero + '}';
    }


}
