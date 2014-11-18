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
public class UserInputList
{

    private List<UserInput> list;

    public UserInputList()
    {
        list = Collections.synchronizedList(new ArrayList<UserInput>());
    }

    /**
     * Returns if the UserInputHolder is empty.
     * @return if the Holder is empty
     */
    public boolean isEmpty()
    {
        return !(list.size() > 0);
    }
    
    /**
     * Gets the next command in the list, removing it from the holder.
     * @return UserInput object, or null if no objects are left.
     */
    public UserInput getFirstUserInput()
    {
        if (list.size() > 0)
        {
            UserInput c = list.get(0);
            list.remove(0);
            return c;
        }
        return null;
    }

    /**
     * Add an UserInput object to the SyncdList.
     *
     * @param cmd
     */
    public void addUserInput(UserInput cmd)
    {
        synchronized (list)
        {
            list.add(cmd);
        }
    }  
    
    /**
     * Empty the list.
     */
    public void clear()
    {
        list.clear();
    }
}
