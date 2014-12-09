/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class FileWrapper extends File
{

    public FileWrapper(String parent, String child)
    {
        super(parent, child);
    }

    public FileWrapper(String pathname)
    {
        super(pathname);
    }

    public FileWrapper(File parent, String child)
    {
        super(parent, child);
    }

    public FileWrapper(URI uri)
    {
        super(uri);
    }

    @Override
    public String getCanonicalPath()
    {
        try
        {
            return super.getCanonicalPath(); //To change body of generated methods, choose Tools | Templates.
        }
        catch (IOException ex)
        {
            return null;
        }
    }
    
    

}
