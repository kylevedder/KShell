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
public class Main
{    
    public static Lock chLock = null;
    
    public static String commandLookupFileLocation = "./functions.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        UI ui = new UI();
        chLock = new Lock();
        CommandExecuteThread cpt = new CommandExecuteThread();
        cpt.start();
    }
    
    

}
