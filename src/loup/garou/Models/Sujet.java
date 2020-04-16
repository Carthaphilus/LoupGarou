package loup.garou.Models;

import java.util.ArrayList;
import java.util.List;


public class Sujet {

    private List<Observateur> obs;

    public Sujet() {
        obs=new ArrayList<>();
    }
    
    public void register(Observateur o){
        obs.add(o);
    }
    
    public void unregister(Observateur o){
        obs.remove(o);
    }
    public void notifierATous(int rowIndex){
        for(Observateur o:obs){
            o.notifier();
        }
    }
    
}
