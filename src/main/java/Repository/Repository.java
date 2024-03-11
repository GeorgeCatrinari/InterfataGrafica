package Repository;

import Domain.Recipe;

import java.util.ArrayList;

public class Repository {
    protected ArrayList<Recipe> entities;
    public Repository(){
        entities = new ArrayList<>();
    }

    public ArrayList<Recipe> getAll(){
        return entities;
    }

    public void add(Recipe entity) throws Exception{
        entities.add(entity);
    }
}
