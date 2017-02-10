package com.example.valdeslab.learningapp.DataManagement;

import java.util.ArrayList;
import java.util.UUID;

public class DataManager {

    private static DataManager dataManager;
    private ArrayList<Data> arrayList;

    /***********************************************************************************************
     * called to get an instance of the data manager
     * if no data manager exits, one is created.
     * if an instance of the data manager has been created, it is returned
     *
     * @return      an instance of the data manager which contains an arraylist of data
     */
    public static DataManager get(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    /***********************************************************************************************
     *
     */
    private DataManager(){
        arrayList = new ArrayList<>();
    }

    /***********************************************************************************************
     * called to get a list of all the data currently stored
     *
     * @return      an arraylist of data
     */
    public ArrayList<Data> getArrayList(){
        return arrayList;
    }

    /***********************************************************************************************
     * called to insert data into the arraylist
     *
     * @param data  - the data that is going to be stored
     */
    public void putData(Data data){
        arrayList.add(data);
    }

    /***********************************************************************************************
     * called to remove data from the arraylist
     *
     * @param id    - the id of the data that should be removed
     */
    public void removeData(UUID id) {
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getId() == id) arrayList.remove(i);
        }
    }

}
