/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.eventsoft.colaboradores.mysql;
import com.pucp.eventsoft.colaboradores.dao.PersonaDAO;
import com.pucp.eventsoft.colaboradores.model.OrigenArtista;
import com.pucp.eventsoft.colaboradores.model.Persona;
import com.pucp.eventsoft.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Fapi
 */
public class PersonaMySQL implements PersonaDAO{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs,rs2;
    private String sql;
    private Statement st;
    @Override

    public ArrayList<Persona> listarTodasPersonas() {
       ArrayList<Persona> personas = new ArrayList<Persona>();
        try{
            con = DBManager.getInstance().getConnection();
            st = con.createStatement();
            sql = "SELECT id_artista,nombre_artistico,origen_artista FROM artista";
            rs = st.executeQuery(sql);
            while(rs.next()){
                sql = "SELECT id_persona,nombre,apellido,fecha_nacimiento,genero FROM persona";
                rs2 = st.executeQuery(sql);
                if(rs2.getInt("id_persona") == rs.getInt("id_artista")){
                    Persona persona = new Persona();
                    persona.setIdArtista(rs.getInt("id_artista"));
                    persona.setNombreArtistico(rs.getString("nombre_artistico"));
                    String origen = rs.getString("origen_artistico");
                    if(origen=="NACIONAL"){
                        persona.setOrigenArtista(OrigenArtista.NACIONAL);
                    }else{
                        persona.setOrigenArtista(OrigenArtista.INTERNACIONAL);
                    }
                    persona.setNombre(rs2.getString("nombre"));
                    persona.setApellido(rs2.getString("apellido"));
                    persona.setFechaNacimiento(rs2.getDate("fecha_nacimiento"));
                    personas.add(persona);
                }
                
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return personas;
    }

    @Override
    public int insertar(Persona persona) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            

                  
            sql = "INSERT INTO artista(nombre_artistico,origen_artista) VALUES(?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1,persona.getNombreArtistico());
            ps.setString(2, persona.getOrigenArtista().toString());
            resultado = ps.executeUpdate();
            
            sql = "SELECT @@last_insert_id AS id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            persona.setIdArtista(rs.getInt("id"));
            
            sql="INSERT INTO persona(id_persona,nombre,apellido,fecha_nacimiento,genero) VALUES(?,?,?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setInt(1,persona.getIdArtista());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setDate(4,new java.sql.Date(persona.getFechaNacimiento().getTime()));
            ps.setString(5, String.valueOf(persona.getGenero()));
            resultado=ps.executeUpdate();
            
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
   // System.out.println("hola");
}
