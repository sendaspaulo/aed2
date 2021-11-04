import java.io.*;
import java.io.FileInputStream;
import java.util.Date;

       
 class Series { // declaracao de atributos
    private String nome;
    private String formato;
    private String duracao;
    private String paisDeOrigem;
    private String idiomaOriginal;
    private String emissora;
    private String transmissao;
    private String numTemps;
    private String numEp;


    public Series(){ // construtor 
        this.nome= "";
        this.formato= "";
        this.duracao= "";
        this.paisDeOrigem= "";
        this.idiomaOriginal = "";
        this.emissora= "";
        this.transmissao= "";
        this.numEp= "";
        this.numTemps= "";
    }

    // contrutor secundario
    public Series(String nome, String formato, String duracao, String paisDeOrigem, String idiomaOriginal, String emissora, String transmissao, String numEp, String numTemps){
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
public void setEpisodios(String numEp){
    this.numEp = numEp;
}

//método para setar o atributo seasons
public void setTemporadas(String numTemps){
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
public String getSeasons(){ 
    return this.numTemps; 
}
//método para retornar o atributo episodes
public String getEpisodes(){ 
    return this.numEp; 
}






    public void imprimir(){
        MyIO.println(nome+" "+formato+" "+duracao+" "+paisDeOrigem+" "+idiomaOriginal+" "+emissora+" "+transmissao+" "+numTemps+" "+numEp);

    }


    public static String justInt(String line) { // Retorna apenas a parte inteira da linha
		String resp = "";
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
				resp += line.charAt(i);
			} else {
				i = line.length();
			}
		}
		return resp;
	}


////// tratamento de dados ///////////////////////////////////////////////////
 //método para tratar a linha, deixar apenas números e converter o retorno de String para Integer
 public static String removetags(String line) { // Remove as tags html da linha
    String newline = "";
    int i = 0;
    while (i < line.length()) {

        if (line.charAt(i) == '<') {
            i++;
            while (line.charAt(i) != '>')
                i++;
        } else {
            newline += line.charAt(i);
        }
        i++;

    }
    newline = newline.replaceAll("&nbsp;", "");
    newline = newline.replaceAll("&#160;", "");
    newline = newline.trim();

    newline = newline.replaceAll("(lista de episódios)", "");
    newline = newline.replaceAll("(Lista de episódios)", "");
    newline = newline.replaceAll("(até o momento)", "");

    return newline;
}

public static String searchName(String fileName) { // Retorna o nome da série
    String resp = "";
    for (int i = 0; i < fileName.length(); i++) {
        if (fileName.charAt(i) == '_') {
            resp += ' ';
        } else {
            resp += fileName.charAt(i);
        }
    }
    return resp.substring(0, resp.length() - 5);
}

    public void ler(String endereco) throws Exception{
        
        
    FileReader fileReader = new FileReader("/tmp/series/" + endereco);

    BufferedReader br = new BufferedReader(fileReader);
try{

    

    nome = searchName(endereco);

    while (!br.readLine().contains("Formato"))
        ;
    formato = removetags(br.readLine());

    while (!br.readLine().contains("Duração"))
        ;
    duracao = removetags(br.readLine());

    while (!br.readLine().contains("País de origem"))
        ;
    paisDeOrigem = removetags(br.readLine());

    while (!br.readLine().contains("Idioma original"))
        ;
    idiomaOriginal = removetags(br.readLine());

    while (!br.readLine().contains("Emissora de televisão"))
        ;
    emissora = removetags(br.readLine());

    while (!br.readLine().contains("Transmissão original"))
        ;
    transmissao = removetags(br.readLine());

    while (!br.readLine().contains("N.º de temporadas"))
        ;
    numTemps = justInt(removetags(br.readLine()));

    while (!br.readLine().contains("N.º de episódios"))
        ;
    numEp = justInt(removetags(br.readLine()));

    br.close();
            //Tratamento de exceções
            } catch(FileNotFoundException e) {
                System.out.println("Unable to open file '" + endereco + "'");                
            } catch(IOException e) {
                System.out.println("Error reading file '" + endereco + "'");
            }
        }

    }



class Lista {
    private Series[] array;
    private int n;

    public Lista(){
        this(100);
    
    }

    public Lista (int tam){
        array = new Series[tam];
        n=0;
    }

    public Series[] getArray(){
        return this.array;
    }

    public int getN(){
        return this.n;
    }

    public void inserirFim (Series classe) throws Exception{
        if (n>=array.length){
            throw new Exception("Erro ao inserir");
        }
        array[n] = classe;
        n++;
    }

    public void mostrar(){
        for(int i=0 ; i<n ;i++){
         
            array[i].imprimir();
        }

}

   


}







public class certo {

     //troca de posiçaos
     public static void swap(int i, int j,Series[] array) {
        Series temp = array[i];
        array[i] = array[j];
        array[j] = temp;
     }
    
         // n = tamanho do array
        // ordenaçao por seleçao
        public static void selectionSort(Lista lista, int n) {
        Series[] array = lista.getArray();
        for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n ; j++) {
				if (array[menor].getCountry().compareTo(array[j].getCountry()) > 0) {
					menor = j;
				} else if (array[menor].getCountry().compareTo(array[j].getCountry()) == 0) {
					if (array[menor].getnome().compareTo(array[j].getnome()) > 0)
						menor = j;
				}
			}
			swap(menor, i, array);
		}

        
    }


    static int comparacoes = 0;
    


    public static Series lerDados(String entrada)throws Exception{
        Series classe = new Series();
        classe.ler(entrada);

        return classe;
    }

    

    public static boolean isFim(String s) {
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
}
public static long now(){
    return new Date().getTime();
}  
    public static void main(String[] args)throws Exception {

        
        MyIO.setCharset("UTF-8");


        Lista lista = new Lista();
        String[] entrada = new String[1000];
        //String[] entrada2= new String[1000];
        int numEntrada = 0;
        //int numEntrada2 = 0;


    
        do {
           entrada[numEntrada] = MyIO.readLine(); 
           //lista.inserirFim(lerDados(entrada[numEntrada])); 

        } while (isFim(entrada[numEntrada++]) == false);
             numEntrada--;  


            for (int i=0;i<numEntrada;i++){
                lista.inserirFim(lerDados(entrada[i]));
            }

           selectionSort(lista, numEntrada);
           lista.mostrar();



            
            
        

            

           
            
            //lista.mostrar();
        } // ultima chave main


    
}
