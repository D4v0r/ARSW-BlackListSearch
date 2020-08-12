/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){

        // Create 3 threads
        Thread threadOne = new Thread(new CountThread(0, 99));
        Thread threadTwo = new Thread(new CountThread(99, 199));
        Thread threadThree = new Thread(new CountThread(200, 299));

        //Start or Run
        threadOne.run();
        threadTwo.run();
        threadThree.run();
    }
    
}
