/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import kshell.JSON.JSONObjectWrapper;
import kshell.file.FileUtils;

/**
 *
 * @author Kyle
 */
public class LookupList
{

    File lookupFile;
    boolean fileParsed = false;
    public static String COMMAND_KEY = "command";
    public static String PATH_KEY = "path";
    private HashMap<String, File> commandMap = null;

    /**
     * Accepts a File as a file to parse for commands.
     *
     * @param lookupFile File to parse
     */
    public LookupList(File lookupFile)
    {
        this.lookupFile = lookupFile;
        if (this.lookupFile != null)
        {
            //if file exists, parse
            if (this.lookupFile.exists())
            {
                commandMap = parseFile(lookupFile);
            }
            //file does not exist
            else
            {
                commandMap = new HashMap<String, File>();
                //make a new lookup file
                generateLookupFile(lookupFile);
            }
        }
        else
        {
            fileParsed = false;
        }
    }

    
    /**
     * Parse the file and generate a random name.
     *
     * @param f
     * @return
     */
    private HashMap<String, File> parseFile(File f)
    {
        HashMap<String, File> commandMap = null;
        JSONObjectWrapper json = null;
        if (f.exists())
        {
            BufferedReader br = null;
            commandMap = new HashMap<String, File>();
            try
            {
                String sCurrentLine;
                br = new BufferedReader(new FileReader(f.getCanonicalPath()));

                //read each line
                while ((sCurrentLine = br.readLine()) != null)
                {
                    //trim the line
                    sCurrentLine = sCurrentLine.trim();
                    //parse JSON
                    json = new JSONObjectWrapper(sCurrentLine);
                    //get the command name
                    String commandName = json.getString(COMMAND_KEY);
                    //get the path file
                    File pathFile = new File(json.getString(PATH_KEY));

                    //if the path and command are not null
                    if (commandName != null && pathFile != null)
                    {
                        commandMap.put(commandName, pathFile);
                    }
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
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
                    ex.printStackTrace();
                }
            }
        }
        return commandMap;
    }
    
    /**
     * Creates a new file with a header and general information.
     * @param f 
     */
    private void generateLookupFile(File f)
    {
        
        String contents = 
               FileUtils.FILE_COMMENT_STARTER + "This is a command lookup file. All commands must be entered below in JSON\n\r"
                + FileUtils.FILE_COMMENT_STARTER + "Example:\n\r"
                + FileUtils.FILE_COMMENT_STARTER + new JSONObjectWrapper("").put(COMMAND_KEY, "COMMAND_NAME").put(PATH_KEY, "PATH_KEY").toString();
        FileUtils.createFile(f, contents);
    }
    
       

}
