/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kshell;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 */
public class Lock
{

    private CountDownLatch latch;
    
    /**
     * Creates a new lock, starts locked.
     */
    public Lock()
    {
        this.latch = new CountDownLatch(1);
    }
    
    /**
     * Unlocks, await() unblocks.
     */
    public void unlock()
    {
        this.latch.countDown();
    }
    
    /**
     * Locks the lock.
     */
    public void lock()
    {
        this.latch = new CountDownLatch(1);
    }
    
    /**
     * Blocks until the lock is unlocked.
     */
    public void await()
    {
        try
        {
            this.latch.await();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Lock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
}
