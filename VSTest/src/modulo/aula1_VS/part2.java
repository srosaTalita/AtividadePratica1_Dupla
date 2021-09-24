package modulo.aula1_VS;

public class part2 {
    public static void main(String[] args) {
        // Byte = 8 bits 0-255 - 11111111
        byte minB = Byte.MIN_VALUE;
        byte maxB = Byte.MAX_VALUE;
        //Short -> 2 Bytes = 16 bits - 11111111 11111111
        short minS = Short.MIN_VALUE;
        short maxS = Short.MAX_VALUE;
        //Int -> 4 Bytes = 32 bits - 11111111 11111111 11111111 11111111
        int minI = Integer.MIN_VALUE;
        int maxI = Integer.MAX_VALUE;
        //Long -> 8 Bytes = 64 bits - 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111
        long minL = Long.MIN_VALUE;
        long maxL = Long.MAX_VALUE;

        //long n1 = minI; == Conversão Implícita - ele aceita qulquer tipo menor que ele
        //--- Explicitas ---
        //byte n2 = (byte)minL; // não converte corretamente == 0
        
    }
}
