/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kshell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class CommandHolder
{
    private List<Command> list;
    
    public CommandHolder()
    {
        list = Collections.synchronizedList(new ArrayList<Command>());
    }
    
    
    /**
     * Add an Command to the 
     * @param cmd 
     */
    public void addCommand(Command cmd)
    {
        list.add(cmd);
    }
    
    
}
