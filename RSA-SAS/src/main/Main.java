package main;

import java.io.IOException;
import java.util.Random;

public class Main {
	
	public static void main(String[] arguments) throws IOException {
		
		long sT = System.currentTimeMillis();
		int failed = 0;
		
		System.out.println("-------------------------------------------------- TESTES RSA --------------------------------------------------\n");
		
		failed += test(new RSA(), 100);
		failed += test(new RSA(), 200);
		failed += test(new RSA(), 250);
		failed += test(new RSA(), 300);
		failed += test(new RSA(), 1000);

		
		long eT = System.currentTimeMillis();
		long fT = eT - sT;
		
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("Testes falhos: " + failed);
        System.out.println("Tempo de execução total: " + fT + "ms");
	}

//	private static String bToS(byte[] cipher) {
//		String temp = "";
//		for (byte b : cipher) {
//			temp += Byte.toString(b);
//		}
//		return temp;
//	}
	
    public static int test(RSA rsa, int messageLength) {
        
    	long sTGenerateMessage = System.currentTimeMillis();
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random randomGenerator = new Random();
        
        String message = "";

        for (int i = 0; i < messageLength; i++) {
            int index = randomGenerator.nextInt(alphabet.length());
            message += alphabet.charAt(index);
        }
        
        long eTGenerateMessage = System.currentTimeMillis();
        long fTGenerateMessage = eTGenerateMessage - sTGenerateMessage;

        System.out.println("------------------------- Teste RSA -------------------------");
        System.out.println(rsa.toString());
        
        long sTEncryptMessage = System.currentTimeMillis();
//		System.out.println("Encriptando a seguinte mensagem: " + message);
//		System.out.println("Mensagem em bytes: " + bToS(message.getBytes()));
        byte[] encryptedMessage = rsa.encryptMessage(message.getBytes());
//        System.out.println("Mensagem encriptada: " + bToS(encryptedMessage));
        long eTEncryptMessage = System.currentTimeMillis();
        long fTEncryptMessage = eTEncryptMessage - sTEncryptMessage;
        
        long sTDecryptMessage = System.currentTimeMillis();
        byte[] decryptedMessage = rsa.decryptMessage(encryptedMessage);
//        System.out.println("Mensagem em bytes: " +bToS(decryptedMessage));
//        System.out.println("Mensagem descriptada: " + new String(decryptedMessage));
        long eTDecryptMessage = System.currentTimeMillis();
        long fTDecryptMessage = eTDecryptMessage - sTDecryptMessage;
        
        boolean success = message.equals(new String(decryptedMessage));      

        System.out.println("Tamanho da mensagem: " + messageLength + " caracteres");
        System.out.println("Mensagem descriptada com sucesso? " + success);
        System.out.println("----------");
        System.out.println("Tempo para gerar a mensagem: " + fTGenerateMessage + "ms");
        System.out.println("Tempo para criar a chave: " + rsa.getfTCreateKey() + "ms"); 
        System.out.println("Tempo de encriptar: " + fTEncryptMessage + "ms");
        System.out.println("Tempo de decriptar: " + fTDecryptMessage  + "ms");
        System.out.println("Tempo de execução geral: " +
        		(rsa.getfTCreateKey() + fTGenerateMessage + fTEncryptMessage + fTDecryptMessage) + "ms");
        
        if (success) return 0;
        return 1;
    }
}
