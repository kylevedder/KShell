/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.awt.event.KeyEvent;

/**
 *
 * @author kyle
 */
public class KeyInput
{

    /**
     * Handles all key input from UI.
     *
     * @param e Key Event from even listener
     */
    public static void handleKeyInput(KeyEvent e)
    {
        if (isKey(e, KeyEvent.VK_ENTER))
        {
            enter(e);
        }
        else if (isKey(e, KeyEvent.VK_UP))
        {
            up(e);
        }
        else if (isKey(e, KeyEvent.VK_ESCAPE))
        {
            escape(e);
        }
        else if (isKey(e, KeyEvent.VK_TAB))
        {
            tab(e);
        }
        else if (isKey(e, KeyEvent.VK_BACK_SPACE))
        {
            backspace(e);
        }
        else if (isKey(e, KeyEvent.VK_SPACE) || !e.isActionKey() && !isKey(e, KeyEvent.VK_ALT) && !isKey(e, KeyEvent.VK_CONTROL))
        {
            typeKey(e);
        }
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

    private static void enter(KeyEvent e)
    {

    }

    private static void backspace(KeyEvent e)
    {

    }

    private static void up(KeyEvent e)
    {

    }

    private static void escape(KeyEvent e)
    {

    }

    private static void tab(KeyEvent e)
    {

    }
    
    private static void typeKey(KeyEvent e)
    {
        
    }
    
    
    
    /**
     * Updates the UI with the a specific String
     * @param s 
     */
    private static void updateUI(String s)
    {
        
    }
}
