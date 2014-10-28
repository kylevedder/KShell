/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author kyle
 */
public class KeyInput
{

    private static Input commandLine = new Input();

    /**
     * Handles all key input from UI.
     *
     * @param e Key Event from even listener
     */
    public static void handleKeyInput(KeyEvent e, UI ui)
    {
        if (isKey(e, KeyEvent.VK_ENTER))
        {
            enter(ui);
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
        ui.updateUI(commandLine);
//        System.out.println(commandLine.toString());
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
     * Adds the given key to the commandLine.
     *
     * @param e
     */
    private static void typeKey(KeyEvent e)
    {
        commandLine.append(String.valueOf(e.getKeyChar()));
    }

    private static void enter(UI ui)
    {
        if (!commandLine.isEmpty())
        {
            CommandMemory.add(commandLine);
        }
        ui.appendText("\n");
        commandLine = new Input();
    }

    private static void backspace()
    {
        commandLine.delete(commandLine.getLength() - 1);
    }

    private static void up()
    {
        commandLine = new Input(CommandMemory.getNextCommand().toString());
    }

    private static void down()
    {
        commandLine = new Input(CommandMemory.getPrevCommand().toString());
    }

    private static void escape()
    {
        commandLine = new Input();
    }

    private static void tab()
    {

    }
}
