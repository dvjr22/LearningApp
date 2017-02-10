package com.example.valdeslab.learningapp.DataManagement;

import java.util.UUID;

public class Data {

    private static String FIRST = "NO FIRST NAME ASSIGNED";
    private static String LAST = "NO LAST NAME ASSIGNED";
    private static int AGE = 0;

    private UUID id;
    private String first;
    private String last;
    private int age;

    public Data(){
        first = FIRST;
        last = LAST;
        age = AGE;
        id = UUID.randomUUID();
    }

    /***********************************************************************************************
     * called to retrieve data id
     *
     * @return      return the id of data
     */
    public UUID getId() {
        return id;
    }

    /***********************************************************************************************
     * called to retrieve data first name
     *
     * @return      data first name
     */
    public String getFirst() {
        return first;
    }

    /***********************************************************************************************
     * called to set data first name
     *
     * @param first     - first name of data
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /***********************************************************************************************
     * called to retrieve data last name
     *
     * @return      data last name
     */
    public String getLast() {
        return last;
    }

    /***********************************************************************************************
     * called to set data last name
     *
     * @param last      - last name of data
     */
    public void setLast(String last) {
        this.last = last;
    }

    /***********************************************************************************************
     * called to retrieve data age
     *
     * @return      - data age
     */
    public int getAge() {
        return age;
    }

    /***********************************************************************************************
     * called to set data age
     *
     * @param age       - age of data
     */
    public void setAge(int age) {
        this.age = age;
    }
}
