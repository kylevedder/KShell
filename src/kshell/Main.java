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

    public static CommandHolder ch = null;
    public static Lock chLock = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        UI ui = new UI();
        ch = new CommandHolder();
        chLock = new Lock();
        CommandProcessorThread cpt = new CommandProcessorThread();
        cpt.start();
    }

}
