package Service;

import Domain.Recipe;
import Repository.Repository;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;

public class Service {
    private Repository repository;

    public Service(Repository repository) throws Exception {
        this.repository = repository;
        if(this.repository.getAll().isEmpty())
        {
            this.initializeDataBase();
        }

    }

    public ArrayList<Recipe> getAll(){
        return repository.getAll();
    }

    public void add(String name, int time, String ingredients) throws Exception {
        Recipe recipe = new Recipe(name,time,ingredients);
        this.repository.add(recipe);
    }
    private void initializeDataBase() throws Exception {
        Recipe recipe1 = new Recipe("Chiftelute", 50, "oua;ulei;usturoi;");
        Recipe recipe2 = new Recipe("Pizza", 45, "apa,drojdie,sos");
        Recipe recipe3 = new Recipe("Quesadilla", 35, "carne de pui,porumb,sos");
        Recipe recipe4 = new Recipe("Supa de legume", 34, "mix de legume,sos,usturoi");
        Recipe recipe5 = new Recipe("Tarta", 40, "faina;porumb;sos");

        this.repository.add(recipe1);
        this.repository.add(recipe2);
        this.repository.add(recipe3);
        this.repository.add(recipe4);
        this.repository.add(recipe5);
    }

    public ArrayList<Recipe> filterByString(String r) throws Exception {
        ArrayList<Recipe> filtrat = new ArrayList<>();
        for (Recipe recipe : this.getAll()) {
            //verificam daca contine stringul
            if (recipe.getIngredients().contains(r)) {
                filtrat.add(recipe);
            }
        }
        if (filtrat.isEmpty())
            throw new Exception("NU EXISTA RETETA CU ACEST INGREDIENT");
        return filtrat;
    }
//    public ArrayList<Recipe> cumparaturiByString(String r) throws Exception {
//        ArrayList<Recipe> cumparat = new ArrayList<>();
//        for (Recipe recipe : this.getAll()) {
//            //verificam daca contine stringul
//            if (recipe.getName().contains(r)) {
//                cumparat.add(recipe);
//            }
//        }
//        if (cumparat.isEmpty())
//            throw new Exception("NU EXISTA RETETA");
//        return cumparat;
//    }
}
