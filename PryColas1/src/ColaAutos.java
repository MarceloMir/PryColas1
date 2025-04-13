import java.util.ArrayDeque;
import java.util.Queue;

public class ColaAutos {
    Queue<Auto> autos;

    public ColaAutos(){
        autos = new ArrayDeque<>();
    }

    public void encolar(Auto dato){
        autos.add(dato);
    }

    public Auto desencolar() throws Exception{
        if(autos.isEmpty())
            throw new Exception("Ya no existen elementos");
        return autos.poll();
    }

    public String listarAutos(){
        StringBuilder sb = new StringBuilder();
        for(Auto a1: autos){
            sb.append(a1.toString());
        }
        return sb.toString();
    }
}
