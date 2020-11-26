import java.io.File;

public class Main {
   

    public static void main(String[] args){
        File file = new File("caso8.txt");
        MontaMatriz a= new MontaMatriz(file);
        Bfs b = new Bfs(a);
        int v[]=a.getPosicaoInicio();
        char in=a.getCharNaMatriz(v[0], v[1]);
        v=a.getPosicaoFim();
        char fim=a.getCharNaMatriz(v[0], v[1]);

        int resutlado =b.resolve();
        if(resutlado==-1)System.out.println("nao foi possivel chegar ate o final");
        else System.out.println("levou "+resutlado+" unidades de tempo para sair de "+in+" (externo) e chegar em "+fim+" (Interno)");
    }
    
}
