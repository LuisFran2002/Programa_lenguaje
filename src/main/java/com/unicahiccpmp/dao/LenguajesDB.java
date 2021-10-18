/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.dao;

import com.unicahiccpmp.programa_lenguaje.lenguajeitem;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author obetancourth
 */

public class LenguajesDB {
    private ArrayList _lenguajesItems;
    
    public LenguajesDB(){
        this._lenguajesItems = new ArrayList<lenguajeitem>();
    }
    
    public ArrayList<lenguajeitem> getlenguajeitem(){
        return this.getlenguajeitem(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS LENGUAJE"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " NOMBRE TEXT NOT NULL,"
                        + " CLASE TEXT NOT NULL,"
                        + " PERIODO INTEGER NOT NULL,"
                        + " YEAR INTEGER NOT NULL"
                        + ")";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<lenguajeitem> getlenguajeitem(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from LENGUAJE;");

                this._lenguajesItems.clear();
                while (misRegistro.next()) {
                    lenguajeitem registro = new lenguajeitem();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setNombres(misRegistro.getString("NOMBRE"));
                    registro.setClase(misRegistro.getString("CLASE"));
                    registro.setPeriodo(misRegistro.getInt("PERIODO"));
                    registro.setYear(misRegistro.getInt("YEAR"));
                    this._lenguajesItems.add(registro);
                }
                comando.close();
           }
           return this._lenguajesItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._lenguajesItems;
        }   
    }

    public lenguajeitem getLenguajeItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM LENGUAJE WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            lenguajeitem registro = new lenguajeitem();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setNombres(misRegistro.getString("NOMBRE"));
                registro.setClase(misRegistro.getString("CLASE"));
                registro.setPeriodo(misRegistro.getInt("PERIODO"));
                registro.setYear(misRegistro.getInt("YEAR"));
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }

    public int updateLenguajeItem(lenguajeitem ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE LENGUAJE set NOMBRE=?, CLASE=?, PERIODO=?, YEAR = ? where ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);

            comando.setString(1, ItemToUpdate.getNombres());
            comando.setString(2, ItemToUpdate.getClase());
            comando.setInt(3, ItemToUpdate.getPeriodo());
            comando.setInt(4, ItemToUpdate.getYear());
            comando.setInt(5, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;

        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertLenguajeItem(lenguajeitem ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO LENGUAJE (NOMBRE, CLASE, PERIODO, YEAR) values (?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);

            comando.setString(1, ItemToInsert.getNombres());
            comando.setString(2, ItemToInsert.getClase());
            comando.setInt(3, ItemToInsert.getPeriodo());
            comando.setInt(4, ItemToInsert.getYear());

            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;

        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    public int deleteLenguajeItem(lenguajeitem ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM LENGUAJE WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);

            comando.setInt(1, ItemToDelete.getId());

            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;

        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}