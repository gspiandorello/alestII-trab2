import java.util.ArrayList;

public class Grafo {

    private ArrayList<Nodo> sabores;

    public Grafo(){
        this.sabores = new ArrayList<Nodo>();
    }

    public class Nodo{

        public String sabor;
        public ArrayList<Nodo> saboresMaisFortes;

        public Nodo (String sabor){
            this.sabor = sabor;
            this.saboresMaisFortes = new ArrayList<Nodo>();
        }

    }

    public Nodo criaSabor(String sabor){
        Nodo nodo = new Nodo(sabor);
        sabores.add(nodo);
        return nodo;
    }

    public void adicionaSaborMaisForte(Nodo sabor, Nodo saborMaisForte){
        sabor.saboresMaisFortes.add(saborMaisForte);
    }

    public boolean testaSaborMaisFraco(Nodo saborMaisFraco, Nodo saborMaisForte){
        for(int i = 0; i < saborMaisFraco.saboresMaisFortes.size(); i++){
            if(saborMaisFraco.saboresMaisFortes.get(i).equals(saborMaisForte)){
                return true;
            }
        }
        return false;
    }

    public void verificaSaboresMaisFortes(Nodo saborMaisFraco, Nodo saborMaisForte){
        if(!testaSaborMaisFraco(saborMaisFraco, saborMaisForte)){
            saborMaisFraco.saboresMaisFortes.add(saborMaisForte);
        }
        for(int i = 0 ; i < saborMaisForte.saboresMaisFortes.size(); i++){
            verificaSaboresMaisFortes(saborMaisFraco, saborMaisForte.saboresMaisFortes.get(i));
        }
    }

    public void adicionaSaboresMaisFortes(){
        for(int i = 0; i < sabores.size(); i++){
            ArrayList<Grafo.Nodo> saboresMaisFortes = (ArrayList<Grafo.Nodo>) sabores.get(i).saboresMaisFortes.clone();
            for(int j = 0; j < saboresMaisFortes.size(); j++){
                verificaSaboresMaisFortes(sabores.get(i), saboresMaisFortes.get(j));
            }
        }
    }

    public int possiveisCoposComDoisSabores(){
        int possiveisCoposComDoisSabores = 0;
        for(int i = 0; i < sabores.size(); i++){
            possiveisCoposComDoisSabores += sabores.get(i).saboresMaisFortes.size();
        }
        return possiveisCoposComDoisSabores;
    }

    public int possiveisCoposComTresSabores(){
        int possiveisCoposComTresSabores = 0;
        for(int i = 0; i < sabores.size(); i++){
            ArrayList<Nodo> saboresMaisFortes = sabores.get(i).saboresMaisFortes;
            for(int j = 0; j < saboresMaisFortes.size(); j ++){
                possiveisCoposComTresSabores += saboresMaisFortes.get(j).saboresMaisFortes.size();
            }
        }
        return possiveisCoposComTresSabores;
    }

    public Nodo procuraSabor(String sabor){
        for(int i = 0; i < sabores.size(); i++){
            if(sabores.get(i).sabor.equals(sabor)){
                return sabores.get(i);
            }
        }
        return null;
    }

}
