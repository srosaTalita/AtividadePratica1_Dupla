package modulo.aula1_VS;

public class part1 {

    public static void main(String[] args) {

        System.out.println("--------------- Java no Vs ---------------");
        
        //Cut String
        String msg = "Hello World";
        System.out.print(msg.substring(0, msg.indexOf(" "))); 

        //Explicit Conversion (Caracter - String)
        char ex1 = 'h';
        char ex2 = 'p';
        String exS = Character.toString(ex1) + Character.toString(ex2);
        System.out.println(exS);
    }
}