/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import kshell.JSON.JSONObjectWrapper;
import kshell.file.FileUtils;
import kshell.file.FileWrapper;

/**
 *
 * @author Kyle
 */
public class LookupList
{

    File lookupFile;
    boolean fileParsed = false;
    public static String FUNCTION_KEY = "command_name";
    public static String PATH_KEY = "jar_path";
    private HashMap<String, FileWrapper> commandMap = null;

    /**
     * Accepts a File as a file to parse for commands.
     *
     * @param lookupFile File to parse
     */
    public LookupList(FileWrapper lookupFile)
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
                commandMap = new HashMap<String, FileWrapper>();
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
     * Gets the File from the function name.
     *
     * @return
     */
    public FileWrapper getJarFromFunctionName(String name)
    {
        return commandMap.get(name);
    }

    /**
     * Gets the String of the path to the file from the function name.
     *
     * @return
     */
    public String getJarStringFromFunctionName(String name)
    {
        return getJarFromFunctionName(name).getCanonicalPath();
    }

    /**
     * Parse the file and generate a random name.
     *
     * @param f
     * @return
     */
    private HashMap<String, FileWrapper> parseFile(FileWrapper f)
    {
        HashMap<String, FileWrapper> commandMap = null;
        JSONObjectWrapper json = null;
        if (f.exists())
        {
            BufferedReader br = null;
            commandMap = new HashMap<String, FileWrapper>();
            ArrayList<String> lines = FileUtils.readCommandFile(f.getCanonicalPath());
            for (String line : lines)
            {                
                try
                {
                    json = new JSONObjectWrapper(line);
                    //get the command name
                    String commandName = json.getString(FUNCTION_KEY);
                    //get the path file
                    FileWrapper pathFile = new FileWrapper(json.getString(PATH_KEY));                    

                    //if the path and command are not null
                    if (commandName != null && pathFile != null)
                    {
                        commandMap.put(commandName, pathFile);
                        System.out.println("Adding: " + commandName + " " + pathFile.getCanonicalPath());
                    }
                }
                catch (org.json.JSONException ex)
                {
                    json = null;
                    System.err.println("Line \"" + line + "\" from the lookup list is improperly formatted.");
                }

            }
        }
        return commandMap;
    }

    /**
     * Creates a new file with a header and general information.
     *
     * @param f
     */
    private void generateLookupFile(FileWrapper f)
    {

        String contents
                = FileUtils.FILE_COMMENT_STARTER + "This is a command lookup file. All commands must be entered below in JSON\n\r"
                + FileUtils.FILE_COMMENT_STARTER + "Example:\n\r"
                + FileUtils.FILE_COMMENT_STARTER + new JSONObjectWrapper().put(FUNCTION_KEY, "COMMAND_NAME").put(PATH_KEY, "PATH_KEY").toString();
        FileUtils.createFile(f, contents);
    }

}
