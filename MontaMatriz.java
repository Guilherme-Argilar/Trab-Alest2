import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MontaMatriz {
    private int linhas;
    private int colunas;
    char matriz [][];
    ArrayList<Character> portais;
    private char inicio;
    private char fim;
    private int linhaInicio;
    private int colunaInicio;
    private int linhaFim;
    private int colunaFim;

    public MontaMatriz(File f){
        linhas=contaLinhas(f);
        colunas=contaColunas(f);
        matriz= new char[linhas][colunas];
        portais=new ArrayList<Character>();
        iniciaMatriz(f);
    }

    public int getLinhas(){
        return linhas;
    }

    public int getColunas(){
        return colunas;
    }

    private int contaColunas(File f) {
        int cont=0;
        try {

            
            Scanner fileIn = new Scanner(f);
            fileIn.nextLine();
            String tamanho = fileIn.nextLine();
            cont=tamanho.length();
            fileIn.close();
        }catch ( FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        return cont;
     }

    private int contaLinhas(File f) {
        int cont=0;
        try {

            
            Scanner fileIn = new Scanner(f);
            fileIn.nextLine();
            while (fileIn.hasNextLine()) {
                cont++;
                fileIn.nextLine();
            }
            fileIn.close();
        }catch ( FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        return cont;
     }

    private void iniciaMatriz(File f){
        try {
            Scanner fileIn = new Scanner(f);
            String destino =fileIn.nextLine();
            String[] slpit= destino.split(" ");
            inicio=slpit[0].charAt(0);
            fim=slpit[1].charAt(0);
           
            for(int i=0; i<linhas;i++){
                if(fileIn.hasNextLine()){
                String aux=fileIn.nextLine();
                for(int j=0; j<colunas;j++){
                    char c=aux.charAt(j);
                    matriz[i][j]=c;
                   
                    if(!portais.contains(c)&& c!=' '&&  c!='#' && c!='.') {
                        portais.add(matriz[i][j]);
                    }
                    if(j==0&&c==inicio){
                        linhaInicio=i;
                        colunaInicio=j;
                    }else if(j==colunas-1&&c==inicio){
                        linhaInicio=i;
                        colunaInicio=j;
                    }else if(i==0&&c==inicio){
                        linhaInicio=i;
                        colunaInicio=j;
                    }else if(i==linhas-1&&c==inicio){
                        linhaInicio=i;
                        colunaInicio=j;
                    }
                    else if(j!=0&&j!=colunas-1&&i!=0&&i!=linhas-1&&c==fim){
                        linhaFim=i;
                        colunaFim=j;
                    }


                    }
                }
            }
            fileIn.close();
        }catch ( FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 

    }

    public void getPortais(){
        for (int i=0;i<portais.size();i++) {
            System.out.print(portais.get(i)+" ");
        }
        System.out.println("comeca em: "+ (linhaInicio +1) +" "+ (colunaInicio+1));
        System.out.println("termina em: "+ (linhaFim +1) +" "+ (colunaFim+1));

    }
    
    public String toString() {
        String msg="";
        for(int i=0; i<linhas;i++){
            msg +="\n";
            for(int j=0; j<colunas;j++){
                msg +=matriz[i][j];
            }
        }
        return msg;
    }
}



