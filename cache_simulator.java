public class cache_simulator{

    public static void main(String[] args){


        if(args.length == 6){
            int nsets = Integer.parseInt(args[0]);
            int bsize = Integer.parseInt(args[1]);
            int assoc = Integer.parseInt(args[2]);
            char substituicao = args[3].charAt(0);
            int flag_saida = Integer.parseInt(args[4]);
            String arquivo_de_entrada = args[5];

            Cache cache = new Cache(nsets, bsize, assoc, substituicao, flag_saida, arquivo_de_entrada);

            System.out.println(cache);
        
        }else
            System.out.println("Número de parâmetros insuficiente");

    }

}