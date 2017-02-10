package com.example.valdeslab.learningapp;

import java.util.ArrayList;
import java.util.UUID;

public class DataManager {

    private static DataManager dataManager;
    private ArrayList<Data> arrayList;

    public static DataManager get(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager(){
        arrayList = new ArrayList<>();
    }

    public ArrayList<Data> getArrayList(){
        return arrayList;
    }

    public void putData(Data data){
        arrayList.add(data);
    }
    public void removeData(UUID id) {
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getId() == id) arrayList.remove(i);
        }
    }

}
