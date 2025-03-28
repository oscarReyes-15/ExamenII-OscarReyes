/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psn;

/**
 *  Total: 5% - 1.25 pts
 * @author LENOVO
 */
public class Entry {
    /** para almacenar el nombre de usuario (username)
     */
    String username; 
    /** para guardar la posición del registro del usuario en un archivo.
     */
    long pos;        
    /** que apunta al siguiente elemento en una lista enlazada
     */
    Entry next;      

    
    /**Inicializa username y pos con los valores proporcionados como parámetros. 
     * El atributo next se inicializa en null por defecto. (5%)
     * @param username
     * @param pos
     */
    public Entry(String username, long pos) {
        this.username = username;
        this.pos = pos;
        next = null;
    }
    
    
}
