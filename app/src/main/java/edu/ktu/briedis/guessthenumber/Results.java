package edu.ktu.briedis.guessthenumber;

public class Results {

    private int id;
    private String name;
    private int age;
    private int difficulty;

    public Results(int id, String name, int age, int difficulty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.difficulty = difficulty;
    }

    public Results() {
        this.id = id;
        this.name = name;
        this.age = age;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}