 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginsystem;

/**
 *
 * @author RC_Student_lab
 */
public class LoginSystem {

    public static void main(String[] args) {
        
        IDandPassword idandPassword = new IDandPassword();
        
        LoginPage loginPage = new LoginPage(idandPassword.getLoginInfo());
    }
}
