/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginsystem;

import java.util.HashMap;

/**
 *
 * @author RC_Student_lab
 */
public class IDandPassword {
    
    HashMap<String,String> logininfo = new HashMap<String,String>();
    
    IDandPassword(){
        
        
        logininfo.put("Yoh","pizza");
        logininfo.put("Cromethis","PASSWORD");
        logininfo.put("Yoh_1","Fgh123@k");
        
    }
    
    public HashMap getLoginInfo(){
        return logininfo;
    }
}        
    
        
    

