/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

/**
 *
 * @author kyle
 */
public class Command
{
    String command;
    String args;
    String param;

    public Command(String command, String args, String param)
    {
        this.command = command;
        this.args = args;
        this.param = param;
    }        
    
    
}