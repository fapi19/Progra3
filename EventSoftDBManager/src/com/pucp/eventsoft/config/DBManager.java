/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pucp.eventsoft.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Fapi
 */
public class DBManager {
    private static DBManager dbManager;
    private Connection con;
    private String host = "lab05-lengpro2-2023-1.c2db6dog5s6y.us-east-1.rds.amazonaws.com";
    private String port = "3306";
    private String db = "lab5";
    private String username = "admin";
    private String password = "inf282lp220231";
    
    private DBManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+host+":"+port+'/'+db;
            this.con = DriverManager.getConnection(url,username,password);
            System.out.println("....conexion realizada...");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public synchronized static DBManager getInstance(){
        if(dbManager==null){
            dbManager= new DBManager();
        }
        return dbManager;
    }
}
