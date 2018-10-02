/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Modelo {
    PreparedStatement senten;
    Connection conexion;
    Datos datos;
    
    public String Insertar (Datos datos){
        String sql = "insert into datos (nombre, apellido, edad, telefono) values (?,?,?,?,?)";
        
        try {
            senten = conexion.prepareStatement(sql);
            senten.setString(1, datos.getNombre());
            senten.setString(2, datos.getApellido());
            senten.setString(3, datos.getEdad());
            senten.setString(4, datos.getTelefono());
            senten.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    private List<Datos> Consultar(){
        
        ArrayList<Datos> consul = new ArrayList<Datos>();
        
        try {
            
            
            Statement sentencia;
            
            ResultSet resul;
            
            String sql = "SELECT * FROM datos ";
            
            sentencia = conexion.createStatement();
            
            resul = sentencia.executeQuery(sql);
            
            while (resul.next()){
                
                String nombre = resul.getString("nombre");
                String apellido = resul.getString("apellido");
                String edad = resul.getString("edad");
                String telefono = resul.getString("telefono");
                datos = new Datos(nombre, apellido, edad, telefono);
                consul.add(datos);
                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
        
        return consul;
    }
    
}
