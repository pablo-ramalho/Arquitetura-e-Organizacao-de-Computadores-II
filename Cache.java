public class Cache{

    private int nsets;                  //Nº DE CONJUNTOS
    private int bsize;                  //TAMANHO DO BLOCO (BYTES)
    private int assoc;                  //GRAU DE ASSOCIATIVIDADE
    private char substituicao;          //POLÍTICA DE SUBSTITUIÇÃO
    private int flag_saida;             //FORMATO DE SAÍDA (0 = FORMATO LIVRE, 1 = FORMATO PADRÃO)
    private String arquivo_de_entrada;  //ARQUIVO QUE CONTÉM OS ENDEREÇOS

    private int tamanho;                //TAMANHO TOTAL DA CACHE (BYTES)

    private int validade[][];           //MATRIZ PARA OS BITS DE VALIDADE
    private int tag[][];                //MATRIZ PARA OS BITS DE TAG
    private int data[][];               //MATRIZ PARA OS DADOS

    private int nIndice;                 //Nº DE BITS PARA O ÍNDICE
    private int nOffset;                 //Nº DE BITS PARA O OFFSET
    private int nTag;                    //Nº DE BITS PARA O TAG

    /**
     * Constrói uma cache com:
     * @param nsets o número de conjuntos que esta cache possui
     * @param bsize o número de Bytes por bloco
     * @param assoc o grau de associatividade
     * @param substituicao a política/algoritmo de substituição
     * @param flag_saida o formato da saída (0 = formato livre, 1 = formato padrão)
     * @param arquivo_de_entrada o arquivo que contém os endereços de memória que devem ser lidos
     */
    public Cache(int nsets, int bsize, int assoc, char substituicao, int flag_saida, String arquivo_de_entrada){
        this.criarCache(nsets, bsize, assoc, substituicao);

        this.flag_saida = flag_saida;
        this.arquivo_de_entrada = arquivo_de_entrada;

    }

    private void criarCache(int nsets, int bsize, int assoc, char substituicao){
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.substituicao = substituicao;

        this.tamanho = this.nsets * this.bsize * this.assoc;

        this.validade = new int[ this.nsets ][ this.assoc ];
        this.tag = new int[ this.nsets ][ this.assoc ];
        this.data = new int[ this.nsets ][ (this.bsize / 4) * this.assoc ];

        this.nIndice = ( (int)( Math.log10(this.tamanho) ) / (int)( Math.log10(2) ) ) - ( (int)( Math.log10(this.bsize) ) / (int)( Math.log10(2) ) ) - ( (int)Math.log10(this.assoc) / ( (int)Math.log10(2) ) );
        this.nOffset = (int)( Math.log10(this.bsize) ) / (int)( Math.log10(2) );
        this.nTag = 32 - (nIndice + nOffset);

    }

    private void mapearEndereços(){

    }

    @Override
    public String toString(){
        return "Informações sobre a cache criada:\n" +
               "Nº de Conjuntos: " + this.nsets + " conjuntos\n" +
               "Tamanho do Bloco: " + this.bsize + " Bytes por bloco\n" +
               "Grau de Associatividade: " + this.assoc + "-way\n" +
               "Política de Substituição: " + (
                                               this.substituicao == 'l' ? "LRU" :
                                               this.substituicao == 'r' ? "FIFO" :
                                               this.substituicao == 'r'
                                              ) + "\n" +
               "Tamanho: " + (
                              this.tamanho < (int)(Math.pow(2, 10)) ? this.tamanho + " Bytes" :
                              this.tamanho < (int)(Math.pow(2, 20)) ? (this.tamanho) / (int)(Math.pow(2, 10)) + "KB" :
                                                                          (this.tamanho) / (int)(Math.pow(2, 20))  + "MB"
                             ) +  "\n" +
               "Tamanho do Índice: " + this.nIndice + " bits\n" +
               "Tamanho do Offset: " + this.nOffset + " bits\n" +
               "Tamanho do Tag: " + this.nTag + " bits\n";

    }
    
}
