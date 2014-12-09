/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class FileUtils
{
    
    public static String FILE_COMMENT_STARTER = "//";
    
     /**
     * Reads the file, but then drops all lines beginning with a comment.
     *
     * @param filePath Path to the file to read
     * @return ArrayList<String> of all non-comment lines of the file
     */
    public static ArrayList<String> readCommandFile(String filePath)
    {
        ArrayList<String> arrayList = readFile(filePath);

        //loop that removes all elements in the ArrayList 
        //that start with the comment
        int i = 0;
        while (i < arrayList.size())
        {
            String s = arrayList.get(i);
            if (s.startsWith(FILE_COMMENT_STARTER) || s.isEmpty())
            {
                arrayList.remove(i);
            }
            else
            {
                i++;
            }
        }
        return arrayList;
    }
    
    /**
     * Reads a file and returns its raw output, sans linebreaks
     *
     * @param filePath String of the path to the file
     * @return ArrayList<String> containing each line in order
     */
    public static ArrayList<String> readFile(String filePath)
    {
        BufferedReader br = null;

        ArrayList<String> fileLines = new ArrayList<String>();
        File f = new File(filePath);
        if (!f.exists())
        {
            System.out.println("File not found!");
            return null;
        }
        try
        {
            String sCurrentLine = null;
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null)
            {
                //adds the line and trims it to remove excess whitespace
                fileLines.add(sCurrentLine.trim());
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File Not Found Exception!");
            return null;
        }
        catch (IOException ex)
        {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File Read Failed Exception!");
            return null;
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileLines;
    }
    
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
