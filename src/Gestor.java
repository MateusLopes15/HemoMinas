import java.util.List;

import javax.management.RuntimeErrorException;

public class Gestor extends Pessoa{  //provavelmente retirar essa classe ou trazer coisas que tem no Hemominas, mas ai caso você ache melhor retirar, dale na narguileira
   
    
    public boolean addHemocentro(Hemocentro hemocentro){
     if (hemocentro == null) {
        throw new IllegalArgumentException("Hemocentro a ser cadastrado não pode ser nulo.");
    }
        
        if(Hemominas.getInstance().cadastrarHemocentro(hemocentro)){
           
            return true;
        }else{
            throw new RuntimeException("Erro ao adicionar novo Hemocentro");
            
        }
    
    

    
}  
public void listarHemocentros(){
     List<Hemocentro> hemocentr;
    hemocentr = Hemominas.getInstance().retornarHemocentros();
    for(int i = 0; i<Hemominas.getInstance().getQtdHemocentros();i++){
        
        System.out.println(hemocentr.get(i).getNome());
    }
    }

}
