package main.logic;

import java.util.LinkedList;

public class Properties {
    private static Properties properties = null;
    private LinkedList<Integer> gettingAlive = new LinkedList<>();
    private LinkedList<Integer> stayingAlive = new LinkedList<>();

    private Properties() {
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }

    public void parseRules(String rules) {
        boolean part = true;
        for (Character c : rules.toCharArray()) {
            if (c == '/') part = false;
            else {
                if (part) stayingAlive.add(Integer.parseInt(String.valueOf(c)));
                else gettingAlive.add(Integer.parseInt(String.valueOf(c)));
            }
        }
    }

    public LinkedList<Integer> getStayingAlive() {
        return stayingAlive;
    }

    public LinkedList<Integer> getGettingAlive() {
        return gettingAlive;
    }
}
