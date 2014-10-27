/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author kyle
 */
public class KeyInput
{

    private static CommandLine commandLine = new CommandLine();

    /**
     * Handles all key input from UI.
     *
     * @param e Key Event from even listener
     */
    public static void handleKeyInput(KeyEvent e)
    {
        if (isKey(e, KeyEvent.VK_ENTER))
        {
            enter();
        }
        else if (isKey(e, KeyEvent.VK_UP))
        {
            up();
        }
        else if (isKey(e, KeyEvent.VK_DOWN))
        {
            down();
        }
        else if (isKey(e, KeyEvent.VK_ESCAPE))
        {
            escape();
        }
        else if (isKey(e, KeyEvent.VK_TAB))
        {
            tab();
        }
        else if (isKey(e, KeyEvent.VK_BACK_SPACE))
        {
            backspace();
        }
        else if (isKey(e, KeyEvent.VK_SPACE) || !e.isActionKey() && !isKey(e, KeyEvent.VK_ALT) && !isKey(e, KeyEvent.VK_CONTROL) && !isKey(e, KeyEvent.VK_SHIFT))
        {
            typeKey(e);
        }
        updateUI();
        System.out.println(commandLine.toString());
    }

    /**
     * Returns if the specified value is the key pressed.
     *
     * @param e Key Event
     * @param code int value to compare against.
     * @return if key is he one specified.
     */
    private static boolean isKey(KeyEvent e, int code)
    {
        return (e.getKeyChar() == code || e.getKeyCode() == code);
    }

    
    /**
     * Update the UI with the CommandLine obj
     */
    private static void updateUI()
    {
        String line = commandLine.toLine();
    }
    
    
    private static void typeKey(KeyEvent e)
    {        
        commandLine.append(String.valueOf(e.getKeyChar()));        
    }   
    
    private static void enter()
    {
        CommandMemory.add(commandLine);
        commandLine = new CommandLine();
    }

    private static void backspace()
    {
        commandLine.delete(commandLine.getLength() - 1);
    }
    
    private static void up()
    {
        commandLine = CommandMemory.getNextCommand();
    }
    
    private static void down()
    {
        commandLine = CommandMemory.getPrevCommand();
    }

    private static void escape()
    {
        commandLine = new CommandLine();
    }

    private static void tab()
    {
        
    }    
}
