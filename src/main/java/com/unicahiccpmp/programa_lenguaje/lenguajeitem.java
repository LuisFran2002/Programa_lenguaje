/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.programa_lenguaje;

import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Luis
 */
public class lenguajeitem {

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _Nombres
     */
    public String getNombres() {
        return _Nombres;
    }

    /**
     * @param _Nombres the _Nombres to set
     */
    public void setNombres(String _Nombres) {
        this._Nombres = _Nombres;
    }

    /**
     * @return the _clase
     */
    public String getClase() {
        return _clase;
    }

    /**
     * @param _clase the _clase to set
     */
    public void setClase(String _clase) {
        this._clase = _clase;
    }

    /**
     * @return the _periodo
     */
    public int getPeriodo() {
        return _periodo;
    }

    /**
     * @param _periodo the _periodo to set
     */
    public void setPeriodo(int _periodo) {
        this._periodo = _periodo;
    }

    /**
     * @return the _year
     */
    public int getYear() {
        return _year;
    }

    /**
     * @param _year the _year to set
     */
    public void setYear(int _year) {
        this._year = _year;
    }
    
    private int _id;
    private String _Nombres;
    private String _clase;
    private int _periodo;
    private int _year;
    
    public lenguajeitem(){
    this._id = 0;
    this._Nombres = "";
    this._clase = "";
    this._periodo = 0;
    this._year = 0;
}
    
    public lenguajeitem(int id, String nombre, String clase, int periodo, int year){
        this._id = id;
        this._Nombres = nombre;
        this._clase = clase;
        this._periodo = periodo;
        this._year = year;
    }
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(this._id));
        campos.add(this._Nombres);
        campos.add(this._clase);
        campos.add(String.valueOf(this._periodo));
        campos.add(String.valueOf(this._year));

        return campos;
        
    }
    
}