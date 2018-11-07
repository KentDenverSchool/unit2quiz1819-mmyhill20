/*
* Hashtable
* Purpose: The goal of Hashtable is
* Author/s: Morgan Myhill
* 10/2/18
* On My Honor: MM
* Collaborators:
*
*/

import java.util.ArrayList;

//The hashCode() method is implemented in the Object class and therefore each class in Java inherits it.
public class Hashtable {
    private Node [] store;
    int numFilled;
    //initializes an array of size capacity

    public Hashtable(int capacity){
        store = new Node[capacity];
    }

    //put hashes the key to an index in your array, and places the value there. Fails if there are collisions/repeat keys.
    public void put(String key, String value){
        putPriv(key, value, store.length);
    }

    private void putPriv(String key, String value, int len){//len is len of array to mod over
        int indToInsert = key.hashCode() % len;
        Node toChain = store[indToInsert];
        while(toChain.getPoint() != null){
            toChain = toChain.getPoint();
        }
        toChain.changePointer(new Node(key.length() + " " + key + value));//stores value and key
    }

    //get hashes the key to get the index, and returns that element. Returns null if key not found.
    public String get(String key){
        if(store[key.hashCode() % store.length] != null) {
            Node next = store[key.hashCode() % store.length];
            String toReturn = next.getData();
            while (next.getPoint() != null){
                toReturn += next.getData() + " or ";
            }
            return toReturn;
        }
        return "key not found";
    }

    private int getNumFilled(){
        return numFilled;
    }

    private Node[] getStored(){
        return store;
    }

    //Updates m to the new value. Rehashes all keys
    public void resize(int newM){
        Hashtable toReplace = new Hashtable(newM);
        if(newM < numFilled) System.out.println("Too much to store: requested space too small");
        else {
            for (int i = 0; i < store.length; i++){
                if(store[i] != null) {
                    Node next = store[i];
                    while (next.getPoint() != null){
                        int indVal = next.getData().indexOf(" ") + Integer.parseInt(next.getData().substring(0, next.getData().indexOf(" ")));
                        String key = next.getData().substring(next.getData().indexOf(" ") + 1, indVal);
                        String val = next.getData().substring(indVal);
                        toReplace.put(key, val);
                    }
                }
                store = toReplace.getStored();//shallow copy ok?
                numFilled = toReplace.getNumFilled();
            }
        }
    }

    private void doubleM(){
        //simply deep copy current array into array double the size
        Node[] replace = new Node[store.length * 2];
        for(int i = 0; i < store.length; i++){
            replace[i] = store[i];
        }

        store = replace;
        numFilled = replace.length;
    }
}

