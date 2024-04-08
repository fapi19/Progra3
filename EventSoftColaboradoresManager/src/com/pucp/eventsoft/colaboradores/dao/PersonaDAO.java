/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pucp.eventsoft.colaboradores.dao;

import com.pucp.eventsoft.colaboradores.model.Persona;
import java.util.ArrayList;

/**
 *
 * @author Fapi
 */
public interface PersonaDAO {
    public ArrayList<Persona> listarTodasPersonas();
    public int insertar(Persona persona);
//    public int modificar(Persona persona);
//    public int eliminar(int id);
}
