import java.util.LinkedList;
import java.util.List;
public class Bfs {
    //valores retirados da matriz
    private char[][] matriz;    
    private int linhas;
    private int colunas;
    private int linhaInicio;
    private int colunaInicio;
    private int linhaFim;
    private int colunaFim;
    

    //variaveis necessarias para fazer o bfs na matriz
    private List<Integer> lq;
    private List<Integer> cq;
    private final int[] dirL= {-1,+1,0,0,0};
    private final int[] dirC= {0,0,+1,-1,0};
    private int nodos_sobrando=1;
    private int nodos_proximo_passo=0;
    boolean[][] visitado; 

    public Bfs(MontaMatriz m) {

        matriz = m.getMatriz();
        int [] posicaoInicio = m.getPosicaoInicio();
        int [] posicaoFim =m.getPosicaoFim();
        linhas = m.getLinhas();
        colunas = m.getColunas();
        linhaInicio = posicaoInicio[0];
        colunaInicio = posicaoInicio[1];
        linhaFim = posicaoFim[0];
        colunaFim = posicaoFim[1];
        lq = new LinkedList<>();
        cq = new LinkedList<>();
        visitado =new boolean[linhas][colunas]; 
    }
    
    public int resolve(){
        boolean chegouFinal = false;
        int distancia=0;
        
        lq.add(linhaInicio);
        cq.add(colunaInicio);
       
        visitado[linhaInicio][colunaInicio]=true;
        while(lq.size()>0){
            int linha = lq.remove(0);
            int coluna = cq.remove(0);

            if(linha== linhaFim && coluna== colunaFim){
                chegouFinal=true;
                break;
            }
            exploreVizinhos(linha,coluna);
            nodos_sobrando--;
            if(nodos_sobrando==0){
                nodos_sobrando=nodos_proximo_passo;
                nodos_proximo_passo=0;
                distancia++;
            }
            
            
        }
        if(chegouFinal)return distancia;
        return -1;
    }
    public void exploreVizinhos(int linha,int coluna){
        for (int i = 0; i < 5;i++){
            int lv;
            int cv;
            if(ePortal(linha,coluna)&&i==4){
                lv=procuraPortalL(linha,coluna);
                cv=procuraPortalC(linha,coluna);
               } 
           else {
                 lv= linha + dirL[i];
                 cv = coluna +dirC[i];
           }
           if(lv <0 || cv <0) continue;
           if(lv >= linhas || cv>=colunas) continue;
           if(visitado[lv][cv]) continue;
           if(matriz[lv][cv]=='#'||matriz[lv][cv]==' ')continue; 
            lq.add(lv);
            cq.add(cv);
            visitado[lv][cv]=true;
            nodos_proximo_passo++;
        }
    }

    public boolean ePortal(int linha, int coluna){
        if(matriz[linha][coluna]!='#'&& matriz[linha][coluna]!='.'&&matriz[linha][coluna]!=' ') return true;
        return false;
    }

    public int procuraPortalL(int linha, int coluna){
        for(int i=0; i<linhas; i++){
            for(int j=0; j<colunas; j++){
                if(matriz[linha][coluna]== matriz[i][j]&& (i!=linha||j!=coluna)) return i;

            }
        }return 0;
    }

    public int procuraPortalC(int linha, int coluna){
        for(int i=0; i<linhas; i++){
            for(int j=0; j<colunas; j++){
                if(matriz[linha][coluna]== matriz[i][j]&& (i!=linha||j!=coluna)) return j;

            }
        }return 0;
    }

    public String toString() {
        String msg="";
        msg += "comeca em: "+ (linhaInicio +1)+" "+(colunaInicio +1);
        msg+= "\n termina em: "+(linhaFim +1)+" "+(colunaFim +1) +"\n";

        msg += "\n";
        for(int i=0; i<linhas;i++){
            msg +="\n";
            for(int j=0; j<colunas;j++){
                msg +=matriz[i][j];
            }
        }
        return msg;
    }
}
