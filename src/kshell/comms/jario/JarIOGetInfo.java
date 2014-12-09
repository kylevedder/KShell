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
public class JarIOGetInfo implements JarIOInterface
{    
    
    private JSONObjectWrapper jsonObj = null;
    /**
     * Sets up the JarIOPrintText class with a jsonObject
     * @param jsonString 
     */
    public JarIOGetInfo(String jsonString)
    {
        setJSON(jsonString);
    }

    @Override
    public void setJSON(String JSONString)
    {
        jsonObj = new JSONObjectWrapper(JSONString);
    }

    @Override
    public String getRawJSON()
    {
        if (jsonObj != null)
        {
            return jsonObj.toString();
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public JarIOTypesEnum getType()
    {
        return JarIOTypesEnum.GET_INFO;
    }
    
    /**
     * Gets the specific subtype of the request.
     * @return 
     */
    public JarIOGetInfoEnum getSubType()
    {
        return JarIOGetInfoEnum.valueOf(
                this.jsonObj.getString(
                        JarIOTypesEnum.GET_INFO.toString()
                ));        
    }    
}

  

