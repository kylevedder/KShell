/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kshell;

/**
 *
 * @author Kyle
 */
public class ExecuteQueue
{
    private Output output;
    public Lock chLock = null;

    public ExecuteQueue()
    {
        output = null;
        chLock = new Lock();
    }
    
    
    
}
