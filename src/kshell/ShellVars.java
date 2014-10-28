/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kshell;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class ShellVars
{
    private static File currentDir = new File(System.getProperty("user.dir"));
    
    /**
     * Gets the file version of the current directory.
     * @return File of the current directory.
     */
    public static File getCurrentDir()
    {
        return currentDir;
    }
    
    /**
     * Gets the String version of the current directory.
     * @return String of the current directory.
     * 
     * <b>Warning</b>: can return null.
     */
    public static String getCurrentDirString()
    {
        try
        {
            return currentDir.getCanonicalPath();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ShellVars.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Gets the new line string.
     * @return new line string.
     * 
     */
    public static String getNewLineString()
    {
        return getCurrentDirString() + ">";
    }
}
