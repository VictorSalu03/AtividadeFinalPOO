package Classes;

import java.util.Comparator;

public class ComparadorPorCódigo implements Comparator <Produto>{

    @Override
    public int compare(Produto p1, Produto p2) {
        return Integer.compare(p1.getCodigo(), p2.getCodigo());
    }
    
}
