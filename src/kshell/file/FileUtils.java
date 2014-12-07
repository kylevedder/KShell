/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Kyle
 */
public class FileUtils
{
    
    public static String FILE_COMMENT_STARTER = "//";
    
    /**
     * Creates a file at the given location and writes the contents string to
     * the file.
     *
     * @param file file to be created.
     * @param contents String of the content to be written to the file.
     * @return boolean value of success
     */
    public static boolean createFile(File file, String contents)
    {
        createFile(file);
        FileWriter fw = null;
        try
        {
            
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contents);
            bw.close();
            return true;
        }
        catch (IOException ex)
        {
            
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (IOException ex)
            {
                
            }
        }
        return false;//something bad happened
    }

    /**
     * Creates a file at the given location
     *
     * @param fileString path to the file to be created.
     * @return boolean value of success
     */
    public static boolean createFile(File f)
    {        

        try
        {
            if (!f.exists() && f.getParentFile().mkdirs())//if the file doesn't already exist and all parent dirs made successfully
            {
                return f.createNewFile();//returns if the creation of the file was successful.
            }
            else
            {
                return false;//file exists, cannot make or failed to make parent dirs
            }
        }
        catch (IOException ex)
        {            
            return false;//something bad happens, return fail
        }
    }
}
