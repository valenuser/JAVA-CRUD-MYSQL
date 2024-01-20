import modulos.DB;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        
        DB bbdd = new DB();



        try{
            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.print("\n\nCRUD JAVA\n\n 1- CREAR USUARIO\n 2- VER TODOS LOS USUARIOS\n 3- BUSCAR USUARIO\n 4- ACTUALIZAR USUARIO\n 5- BORRAR USUARIO\n 6- CERRAR PROGRAMA \n\n RESPUESTA: ");
    
                int response = sc.nextInt();
    
                if(response ==  1){
                    bbdd.AddUser();
                }else if(response == 2){
                    bbdd.ReadAll();
                }else if(response == 3){
                    bbdd.SearchUser();
                }else if(response == 4){
                    bbdd.UpdateUser();
                }else if(response == 5){
                    bbdd.DeleteUser();
                }else if(response == 6){
                    break;
                }
                
            }
            System.out.println("Programa finalizado.");
        }catch(Exception e){
            System.out.println(String.format("%s", e));
        }

    }
}
