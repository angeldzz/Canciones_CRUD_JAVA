/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_canciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pinto
 */
class ModeloBD {

    //atributos de conexionn a la BD
    private String url = "jdbc:mysql://localhost:3306/canciones";
    private String usuario = "canciones";
    private String contraseña = "canciones";

    //conexion a la BD
    Connection conexion;

    public ModeloBD() {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Cancion> listaCanciones() {

        ArrayList<Cancion> listaCanc = new ArrayList<>();

        try {

            String consulta = "SELECT * FROM canciones";

            Statement sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery(consulta);

            while (resultado.next()) {
                Cancion cnc = new Cancion(resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("artista"),
                        resultado.getString("album"),
                        resultado.getInt("anio"),
                        resultado.getString("genero"));
                listaCanc.add(cnc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaCanc;
    }

    public Boolean insertarBD(Cancion cnc) {
        Boolean resultadoIns = true;
        try {
            //creamos la consulta para la inserccion
            String insercion = "INSERT INTO canciones (titulo,artista,album,anio,genero) VALUES (?,?,?,?,?)";

            //creamos el preparedstatement para posteriormente añadir los datos a la BD
            PreparedStatement prepstat = conexion.prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS);

            //a continuacion añadimos todos los datos en el orden en el que creamos
            //la consulta
            prepstat.setString(1, cnc.getTitulo());
            prepstat.setString(2, cnc.getArtista());
            prepstat.setString(3, cnc.getAlbum());
            prepstat.setInt(4, cnc.getAnio());
            prepstat.setString(5, cnc.getGenero());

            //a continacion generamos la consulta
            prepstat.executeUpdate();

            //Hacemos un resultset para posteriormente coger la clave
            //consukta para recuperar el id de la fila insertada
            ResultSet resultado = prepstat.getGeneratedKeys();
            resultado.next();
            cnc.setId(resultado.getInt(1));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return resultadoIns = false;
        }
        return resultadoIns;
    }

    public boolean borrarCancion(Cancion cncSeleccionada) {
        Boolean res = true;
        //creamos el borrado de datos
        String borrado = "DELETE FROM canciones WHERE id=?";

        //creamos el preparedstatement para posteriormente añadir los datos a la BD
        PreparedStatement prepstat;
        try {
            prepstat = conexion.prepareStatement(borrado);

            //a continuacion añadimos todos los datos en el orden en el que creamos
            //la consulta
            prepstat.setInt(1, cncSeleccionada.getId());
            
            Integer filasBorradas = prepstat.executeUpdate();
            if (filasBorradas != 1) {
                res=false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            res=false;
        }
        return res;
    }

boolean modificaCancion(Cancion cncSeleccionada) {
         Boolean res = true;
       
       String modificar = "UPDATE canciones SET titulo=?,artista=?,album=?,anio=?,genero=? WHERE id = ?";
       
        try {
            PreparedStatement ps = conexion.prepareStatement(modificar);
            
            ps.setString(1, cncSeleccionada.getTitulo());
             ps.setString(2, cncSeleccionada.getArtista());
             ps.setString(3, cncSeleccionada.getAlbum());
            ps.setInt(4,cncSeleccionada.getAnio());
             ps.setString(5, cncSeleccionada.getGenero());
            ps.setInt(6, cncSeleccionada.getId());
            
            Integer filasBorradas = ps.executeUpdate();
            if(filasBorradas != 1){
                res = false;
            }
            
        } catch (SQLException ex) {
            res = false;
        }
       
       return res;
}
}
