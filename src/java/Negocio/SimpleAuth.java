/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author jorge.correa
 */
public class SimpleAuth extends Authenticator{
    
    public String username = null;
    public String password = null;
    
    public SimpleAuth(String user, String pwd){
        username = user;
        password = pwd;
    }
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username,password);
    }
    
}
