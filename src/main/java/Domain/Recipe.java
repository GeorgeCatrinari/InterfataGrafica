package Domain;

public class Recipe {
    private String name;
    private int time;
    private String ingredients;

    public String getIngredients(){
        return ingredients;
    }
    public int getTime(){
        return time;
    }

    public String getName(){
        return name;
    }

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTime(int time){
        this.time = time;
    }

    public Recipe(String name, int time, String ingredients){
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
    }

    @Override
    public String toString(){
        return "Recipe{" +
                "name=" + name + '\'' +
                ", time=" + time +
                ", ingredients" + ingredients +
                '}';
    }
}
