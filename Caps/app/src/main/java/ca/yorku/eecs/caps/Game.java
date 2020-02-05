package ca.yorku.eecs.caps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {

    private CountryDB db;

    public Game(){
        this.db = new CountryDB();
    }

    public String qa(){

        List<String> capitals = new ArrayList<>();
        capitals = db.getCapitals();
        //System.out.println(capitals.size());
        int n = capitals.size();
        int index = (int) (Math.random()*n);  //should generate a random no.between (0, n}
        String c = capitals.get(index);
        Map<String,Country> map = new TreeMap<>();
        map = db.getData();
        Country ref = map.get(c);
        if (Math.random()<0.5){

            //String q = ref.getCapital();
            return "What is the capital of " + ref.getName() + "?\n" + ref.getCapital();

        }
        else {

            //question = getCountry();
            return  ref.getCapital() + "is the capital of?" + "\n" + ref.getName();
        }
        //System.out.println(c);
        //return question;
    }

    public static void main(String[] args) {
        Game question = new Game();
        String questionAnswer = question.qa();
        // String resultq = questionAnswer.split("\\\n")[0];
        //String resulta = questionAnswer.split("\\\n")[1];
        //System.out.println(resultq + " " + resulta);
        System.out.println(question.qa());
    }
}