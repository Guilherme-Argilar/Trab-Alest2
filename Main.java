import java.io.File;

public class Main {
   

    public static void main(String[] args){
        File file = new File("caso1.txt");
        MontaMatriz a= new MontaMatriz(file);
        System.out.println("Linhas: "+a.getLinhas()+" Colunas: "+a.getColunas());
        a.getPortais();
        System.out.println(a);

    }
    
}
