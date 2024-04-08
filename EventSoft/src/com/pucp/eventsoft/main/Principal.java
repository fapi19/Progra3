/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.eventsoft.main;

import com.pucp.eventsoft.colaboradores.dao.PersonaDAO;
import com.pucp.eventsoft.colaboradores.model.OrigenArtista;
import com.pucp.eventsoft.colaboradores.model.Persona;
import com.pucp.eventsoft.colaboradores.mysql.PersonaMySQL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Fapi
 */
public class Principal {
    public static void main(String[] args) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Persona p1 = new Persona("GIANMARCO",OrigenArtista.NACIONAL,"JAVIER",
        "ZIGNAGO",sdf.parse("17-08-1970"),'M');
        Persona p2 = new Persona("SHAKIRA",OrigenArtista.INTERNACIONAL,"ISABEL",
        "MEBARAK",sdf.parse("02-02-1977"),'F');
        //se registran los artistas de tipo persona
        PersonaDAO daoPersona = new PersonaMySQL();
        evaluarResultado(daoPersona.insertar(p1),p1.getNombreArtistico());
        evaluarResultado(daoPersona.insertar(p2),p2.getNombreArtistico());
        ArrayList<Persona>artistasPersonas = daoPersona.listarTodasPersonas();
        for(Persona p : artistasPersonas){
           p.imprimirDatos();
        }
        
    }
    
    public static void evaluarResultado(int resultado, String nombreArtistico){
        if(resultado==1){
            System.out.println("Se ha registrado con exito al artista " + nombreArtistico);
        }
        else{
            System.out.println("Ha fallado el registro de: "+ nombreArtistico);
        }
    }
}
