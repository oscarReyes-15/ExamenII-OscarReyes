/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psn;

/**
 *  Implementa una lista enlazada de objetos Entry, 
 *  utilizada para almacenar usuarios en memoria 
 *  y facilitar búsquedas rápidas. 
 * 
 *  Total: 15% - 3.75%
 * @author LENOVO
 */
public class HashTable {
    //
    Entry HEAD;
    
    
    /**Crea un nuevo Entry y lo agrega al final de la lista.
     * @param username
     * @param pos
     */
    void add(String username, long pos) {
        // si la cabeza esta null, init la cabeza con el primer entry
        if (HEAD == null) {
            HEAD = new Entry (username, pos);
        } else {
            // iteramos a traves de cada nodo para llegar al ultimo, el nodo con el next null lo sera
            Entry temporal = HEAD;
            
            // iterando...
            while (temporal.next != null) {
                temporal = temporal.next;
            }
            
            // agregamos el entry en el next null
            temporal.next = new Entry (username, pos);
        }
    }

    /**Busca un Entry con el username dado y lo elimina de la lista.
     * @param username
     */
    void remove(String username){
        // chequeamos si el head tiene el valor
        if (HEAD.username.equals(username)) {
            HEAD = HEAD.next; // ahora la cabeza sera la siguiente
        } else {
            // sino toca iterar womp womp
            Entry temporal = HEAD;
            
            // iterando...
            while (temporal.next != null && !temporal.next.username.equals(username)) {
                temporal = temporal.next;
            }
            
            // si es igual al user borramos, sino, nunca existio en la lista
            if (temporal.next.username.equals(username)) {
                temporal.next = temporal.next.next; 
            }
        }
    }

    /**
     * Busca el usuario en la lista enlazada, si lo encuentra, retorna su pos
     * @param username
     */
    long search(String username){
        // toca iterar por la lista OTRA VEZ
        Entry temporal = HEAD;
        
        if (temporal == null) {
            return -1;
        }
        
        // aplicar iteracion al igual que la funcion remove
        while (temporal.next != null && !temporal.next.username.equals(username)) {
                temporal = temporal.next;
        }
        
        // si es null no encontro nada
        if (temporal.next == null) {
            
            return -1;
        } else {
            return temporal.next.pos;
        }
        
    }
}
