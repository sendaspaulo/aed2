
import java.io.*;
import java.io.FileInputStream;

       
public class Series { // declaracao de atributos
    private String nome;
    private String formato;
    private String duracao;
    private String paisDeOrigem;
    private String idiomaOriginal;
    private String emissora;
    private String transmissao;
    private int numTemps;
    private int numEp;


    public Series(){ // construtor 
        this.nome= "";
        this.formato= "";
        this.duracao= "";
        this.paisDeOrigem= "";
        this.idiomaOriginal = "";
        this.emissora= "";
        this.transmissao= "";
        this.numEp= 0;
        this.numTemps= 0;
    }

    // contrutor secundario
    public Series(String nome, String formato, String duracao, String paisDeOrigem, String idiomaOriginal, String emissora, String transmissao, int numEp, int numTemps){
        this.nome =  nome;
        this.formato =  formato;
        this.duracao = duracao;
        this.paisDeOrigem =  paisDeOrigem;
        this.idiomaOriginal = idiomaOriginal;
        this.emissora = emissora;
        this.transmissao = transmissao;
        this.numEp= numEp;
        this.numTemps = numTemps;
    }

// setters








public void setNome(String nome){
    this.nome = nome;
}
//método para setar o atributo formato
public void setFormato(String formato){
    this.formato = formato;
}
//método para setar o atributo duration
public void setDuracao(String duracao){
    this.duracao = duracao;
}
//método para setar o atributo country
public void setpaisDeOrigem(String paisDeOrigem){
    this.paisDeOrigem = paisDeOrigem;
}
//método para setar o atributo language
public void setIdioma(String idiomaOriginal){
    this.idiomaOriginal = idiomaOriginal;
}
//método para setar o atributo broadcaster
public void setEmissora(String emissora){
    this.emissora = emissora;
}
//método para setar o atributo streaming
public void setTransmissao(String transmissao){
    this.transmissao = transmissao;
}
//método para setar o atributo episodes
public void setEpisodios(int numEp){
    this.numEp = numEp;
}

//método para setar o atributo seasons
public void setTemporadas(int numTemps){
    this.numTemps = numTemps;
}





///////////// getters /////////////////////////////////////////////////

    public String getnome(){
        return this.nome;
    }

//método para retornar o atributo format
public String getFormat(){ 
    return this.formato; 
}
//método para retornar o atributo duration
public String getDuration(){ 
    return this.duracao; 
}
//método para retornar o atributo country
public String getCountry(){ 
    return this.paisDeOrigem; 
}
//método para retornar o atributo language
public String getLanguage(){ 
    return this.idiomaOriginal; 
}
//método para retornar o atributo broadcaster
public String getBroadcaster(){ 
    return this.emissora; 
}
//método para retornar o atributo streaming
public String getStreaming(){ 
    return this.transmissao; 
}
//método para retornar o atributo seasons
public int getSeasons(){ 
    return this.numTemps; 
}
//método para retornar o atributo episodes
public int getEpisodes(){ 
    return this.numEp; 
}






    public void imprimir(){
        MyIO.println(nome+" "+formato+" "+paisDeOrigem+" "+idiomaOriginal+" "+emissora+" "+transmissao+" "+numTemps+" "+numEp);

    }




////// tratamento de dados ///////////////////////////////////////////////////
 //método para tratar a linha, deixar apenas números e converter o retorno de String para Integer
 public int justInt(String line){
    String resp = "";
    for(int i = 0; i < line.length(); i++){
        if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
            resp += line.charAt(i);
        } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
            i = line.length();
        }
    }
    return Integer.parseInt(resp); //conversão da string resp para número inteiro a ser retornado
}





     //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
     public String removetags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // é testado para verificar se o contador i ainda está dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o laço de repetição é encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exceções presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags é concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }

    public void ler(String endereco) throws Exception{
        
        InputStreamReader irs =  new InputStreamReader(new FileInputStream(endereco));
        BufferedReader br =  new BufferedReader(irs);

try{

        //nome
        while(!br.readLine().contains("<table class=\"infobox_v2\""));
        br.readLine();
        this.nome=removetags(br.readLine());
    

        //formato
        while(!br.readLine().contains("Formato"));
        this.formato=removetags(br.readLine());

        // duracao
        while(!br.readLine().contains("País de origem"));
        this.paisDeOrigem=removetags(br.readLine());



            //set idioma da série
            while(!br.readLine().contains("Idioma original"));
            this.idiomaOriginal = removetags(br.readLine());

            //set emissora da série
            while(!br.readLine().contains("Emissora de televisão"));
            this.emissora = removetags(br.readLine());

            //set transmissão original da série
            while(!br.readLine().contains("Transmissão original"));
            this.transmissao = removetags(br.readLine());

            //set temporadas da série
            while(!br.readLine().contains("N.º de temporadas"));
            this.numTemps = justInt(removetags(br.readLine()));

            //set episódios da série
            while(!br.readLine().contains("N.º de episódios"));
            this.numEp = justInt(removetags(br.readLine()));

            this.imprimir();

            br.close();         
            //Tratamento de exceções
            } catch(FileNotFoundException e) {
                System.out.println("Unable to open file '" + endereco + "'");                
            } catch(IOException e) {
                System.out.println("Error reading file '" + endereco + "'");
            }
        }


public static boolean isFim(String s) {
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
}


public static void main(String[] args)throws Exception {
    Series classe = new Series();
    String[] entrada = new String[1000];
    int numEntrada = 0;

    do {
       entrada[numEntrada] = MyIO.readLine();    
       classe.ler(entrada[numEntrada]);
           
          

    } while (isFim(entrada[numEntrada++]) == false);
         numEntrada--;  
         

    }



}


      
