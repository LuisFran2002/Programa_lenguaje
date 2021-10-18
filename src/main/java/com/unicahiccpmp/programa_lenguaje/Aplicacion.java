/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.programa_lenguaje;

import com.unicahiccpmp.utilities.layout;
import com.unicahiccpmp.dao.LenguajesDB;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Aplicacion {
    private Scanner _EntradaTeclado;
    private ArrayList _theLenguajes;
    private int _lenguajesId;
    private LenguajesDB _lenguajeModel;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._theLenguajes = new ArrayList<lenguajeitem>();  
        this._lenguajesId = 0;
        this._lenguajeModel = new LenguajesDB();
        this._lenguajeModel.tableInitialize();
        this._theLenguajes = this._lenguajeModel.getlenguajeitem(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarDatos();
                break;
            case "I":
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                break;
            case "E":   
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opción no reconocida!!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        layout.printHeader("Nuevo Registro del lengauje");
        
        lenguajeitem nuevolenguajeItem = new lenguajeitem();
        
        nuevolenguajeItem.setNombres(layout.obtenerValorParaCampo("Nombre del lenguaje", "Lenguaje XYZ", this._EntradaTeclado));
        nuevolenguajeItem.setClase(layout.obtenerValorParaCampo("Clase donde lo aprendio", "Clase Y", this._EntradaTeclado));
        int periodo = Integer.parseInt(layout.obtenerValorParaCampo("Num. de perido en que lo llevo: ", "1" , this._EntradaTeclado));
        nuevolenguajeItem.setPeriodo(periodo);
        int year = Integer.parseInt(layout.obtenerValorParaCampo("Año llevada", "2021", this._EntradaTeclado));
        nuevolenguajeItem.setYear(year);
        
//        nuevolenguajeItem.setId(this._lenguajesId + 1);
//        this._lenguajesId ++;
//        this._theLenguajes.add(nuevolenguajeItem);
        this._lenguajeModel.insertLenguajeItem(nuevolenguajeItem);
        this._theLenguajes = this._lenguajeModel.getlenguajeitem(true);
        
        layout.printSeparator();
        System.out.println(this._theLenguajes.size());
    }
    
    
    private void mostrarDatos(){
        layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Lenguaje");
        columnas.add("Clase");
        columnas.add("Periodo");
        columnas.add("Year");

        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(7);
        anchos.add(28);
        anchos.add(20);
        anchos.add(10);
        anchos.add(10);


       
        try {
            //Imprimir encabezado
            layout.imprimirEnColumna(columnas, anchos, "|");
            layout.printSeparator();
            for(int i = 0; i< this._theLenguajes.size(); i++){
                columnas = ((lenguajeitem) this._theLenguajes.get(i)).obtenerCampos();
                layout.imprimirEnColumna(columnas, anchos, "|");
            }

        } catch(Exception ex) {
            System.err.println("Error al imprimir" + ex.getMessage());
        }
    }
    private void actualizarRegistro(){
        layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        lenguajeitem selectlenguaje = null;
//        for( int i=0; i < this._theLenguajes.size(); i++){
//            if( selectedId == ((lenguajeitem)this._theLenguajes.get(i)).getId()) {
//                selectlenguaje = (lenguajeitem)this._theLenguajes.get(i);
//                break;
//            }
//        }
        
        selectlenguaje = this._lenguajeModel.getLenguajeItemById(selectedId);
        
        if (selectlenguaje == null ) {
            System.out.println("Registro solicitado no existe!!!");
        } else {
            selectlenguaje.setNombres(layout.obtenerValorParaCampo("Nombre del lenguaje", selectlenguaje.getNombres(), this._EntradaTeclado));
            selectlenguaje.setClase(layout.obtenerValorParaCampo("Clase donde lo aprendio", selectlenguaje.getClase(), this._EntradaTeclado));
            int periodo = Integer.parseInt(layout.obtenerValorParaCampo("Num. de perido en que lo llevo: ", String.valueOf(selectlenguaje.getPeriodo()) , this._EntradaTeclado));
            selectlenguaje.setPeriodo(periodo);
            int year = (Integer.parseInt(layout.obtenerValorParaCampo("Año llevada", String.valueOf(selectlenguaje.getYear()) , this._EntradaTeclado)));
            selectlenguaje.setYear(year);
            
            this._lenguajeModel.updateLenguajeItem(selectlenguaje);
            this._theLenguajes = this._lenguajeModel.getlenguajeitem(true);
            
            layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }

    }
        
    private void eliminarRegistro(){
        layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        /*int encontradoEnIndice = -1;
        for( int i=0; i < this._theLenguajes.size(); i++){
            if( selectedId == ((lenguajeitem)this._theLenguajes.get(i)).getId()) {
                encontradoEnIndice = i;
                break;
            }
        }
        if (encontradoEnIndice>=0){*/
        lenguajeitem selectedlenguaje = this._lenguajeModel.getLenguajeItemById(selectedId);
        if(selectedlenguaje != null){
            layout.printSeparator();
            String confirmado = layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
                //this._theLenguajes.remove(encontradoEnIndice);
                layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!!!");
                this._lenguajeModel.deleteLenguajeItem(selectedlenguaje);
                this._theLenguajes = this._lenguajeModel.getlenguajeitem(true);
            }
        } else {
            System.out.println("Registro solicitado no existe!!!");
        }

    }
}
