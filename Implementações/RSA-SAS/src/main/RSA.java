package main;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private int maxLength = 1024;
	private long fTCreateKey;
	private BigInteger p, q, n, z, e, d;
	private Random randomGenerator;

	public RSA() {
		long sTCreateKey = System.currentTimeMillis();
		
		randomGenerator = new Random();
		this.p = BigInteger.probablePrime(maxLength, randomGenerator);
		this.q = BigInteger.probablePrime(maxLength, randomGenerator);
		this.n = this.p.multiply(this.q);
		this.z = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));
		this.e = BigInteger.probablePrime(maxLength / 2, randomGenerator);
		
		while (z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0) {
			e.add(BigInteger.ONE);
		}
		
		d = e.modInverse(z);
		
		long eTCreateKey = System.currentTimeMillis();
		this.fTCreateKey = eTCreateKey - sTCreateKey;
	}

	// Encrypting the message
	public byte[] encryptMessage(byte[] message) {
		return (new BigInteger(message)).modPow(e, n).toByteArray();
	}

	// Decrypting the message
	public byte[] decryptMessage(byte[] message) {
		return (new BigInteger(message)).modPow(d, n).toByteArray();
	}
	
    public String toString() {
        return 	"Variáveis" +
        		"\np: " + p.toString() +
        		"\nq: " + q.toString() +
        		"\nn: " + n.toString() +
        		"\nz: " + z.toString() +
        		"\ne: " + e.toString() +
        		"\nd: " + d.toString() +
        		"\n----------";
    }

	public long getfTCreateKey() {
		return fTCreateKey;
	}
    
}