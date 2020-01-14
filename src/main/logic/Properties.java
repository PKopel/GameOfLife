package main.logic;

import java.util.LinkedList;

public class Properties {
    private static Properties properties = null;
    private LinkedList<Integer> gettingAlive = null;
    private LinkedList<Integer> stayingAlive = null;

    private Properties(){}

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }


    public LinkedList<Integer> getStayingAlive() {
        return stayingAlive;
    }

    public void setStayingAlive(LinkedList<Integer> stayingAlive) {
        this.stayingAlive = stayingAlive;
    }

    public LinkedList<Integer> getGettingAlive() {
        return gettingAlive;
    }

    public void setGettingAlive(LinkedList<Integer> gettingAlive) {
        this.gettingAlive = gettingAlive;
    }
}
