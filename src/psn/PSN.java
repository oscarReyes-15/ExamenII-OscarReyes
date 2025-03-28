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
            
            // Agregar usuarios
            psn.addUser("player1");
            psn.addUser("player2");
            psn.addUser("player3");
            
            // Mostrar información de los usuarios
            psn.playerInfo("player1");
            psn.playerInfo("player2");
            psn.playerInfo("player3");
            
            // Agregar trofeos
            psn.addTrophieTo("player1", "Game A", "First Blood", Trophy.ORO);
            psn.addTrophieTo("player2", "Game B", "Unstoppable", Trophy.PLATINO);
            psn.addTrophieTo("player3", "Game C", "Master Explorer", Trophy.PLATA);
            
            // Mostrar información después de añadir trofeos
            psn.playerInfo("player1");
            psn.playerInfo("player2");
            psn.playerInfo("player3");
            
            // Desactivar un usuario
            psn.deactivateUser("player2");
            
            // Intentar mostrar la información del usuario desactivado
            psn.playerInfo("player2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
