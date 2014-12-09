/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell.comms.jario;

import kshell.comms.JSONObjectWrapper;

/**
 *
 * @author Kyle
 */
public class JarIOInterpreter
{

    private JSONObjectWrapper jsonObj = null;
    private JarIOTypesEnum type = null;
    private String jsonString = null;

    /**
     * Sets up the JarIO class with a jsonObject
     *
     * @param jsonString
     */
    public JarIOInterpreter(String jsonString)
    {
        this.jsonString = jsonString;
        if (jsonString != null)
        {
            this.jsonObj = new JSONObjectWrapper(jsonString);
            System.out.println(jsonString);
            for(JarIOTypesEnum jarIOTypes : JarIOTypesEnum.values())
            {
                if(this.jsonObj.has(jarIOTypes.toString()))
                {
                    this.type = jarIOTypes;
                }
            }
        }
    }
    
    /**
     * Gets the type of command.
     * @return 
     */
    public JarIOTypesEnum getType()
    {
        return type;
    }
    
    /**
     * Returns the correct object based upon the type or null if no correct object found.
     * @return 
     */
    public JarIOInterface getObject()
    {
        if(getType() == JarIOTypesEnum.PRINT_TEXT)
        {
            return new JarIOPrintText(jsonString);
        }
        else if(getType() == JarIOTypesEnum.GET_INFO)
        {
            return new JarIOGetInfo(jsonString);
        }
        else if(getType() == JarIOTypesEnum.SET_INFO)
        {
            return new JarIOSetInfo(jsonString);
        }
        else
        {
            return null;
        }
            
    }

    @Override
    public String toString()
    {
        return jsonString;
    }
    
    

}
