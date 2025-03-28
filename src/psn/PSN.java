/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psn;

import java.io.IOException;

/**
 *  Total: 20% - 5 pts
 * @author LENOVO
 */
public class PSN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            PSNUsers psn = new PSNUsers();
            new GUI(psn);
        } catch (IOException e) {
             System.err.println(e);
        }
    }
    
}
