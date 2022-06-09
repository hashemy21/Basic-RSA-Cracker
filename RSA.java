import java.math.BigInteger;
import java.util.*;  


public class RSA {
	
    
    public RSA() 
    {
       
    }  
    
    public static int getGCD(int a, int b)
    {
        while (a != 0 && b != 0)
        {
            if (a > b)
                a %= b;
            else
                b %= a;
        }
        return Math.max(a, b);
    }
    
    public static boolean areCoprime(int e, int phi)
    { 
        return getGCD(e,phi) == 1;
    }
    
    public static ArrayList<Integer> strConverter(String s) {

    	ArrayList<Integer> t = new ArrayList<Integer>();

    for (int i = 0; i < s.length(); ++i) {
        char ch = s.charAt(i);
        int n = (int)ch - (int)'a' + 1;
        t.add(n);
    }
    return t;
    }
    
    
    public static int findPhi(BigInteger n) 
    {
    	BigInteger min = new BigInteger("2");
        BigInteger p = min;
        
        while(p.compareTo(n.divide(min)) <= 0){

            if(n.mod(p).equals(BigInteger.ZERO)){
                BigInteger q = n.divide(p);
                System.out.println("The prime factors of n are"+" "+"(" + p + ", " + q + ")");
                BigInteger x=(p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
                int phi=x.intValue();
                System.out.println("The value of phi is"+" "+x);
                return phi;
            }
            p = p.nextProbablePrime();
        }
        System.out.println("Cannot be factorized");
		return 0;	
    }

    
    public static int mInverse(int e, int phi)
    {
        for (int x = 1; x < phi; x++)
            if (((e%phi) * (x%phi)) % phi == 1)
                return x;
        return 1;
    }
    
    public static String decrypt(ArrayList<Integer> cipher, int d, int n) 
    {
    	String plaintext="";
    	for(int i=0;cipher.size()>i;i++) {
    		plaintext +=(int) (Math.pow(cipher.get(i), d))%n;
    	}
    	return plaintext;
    }
    
 
    
    public static void main(String[] args)
    {
    	
    	Scanner sc= new Scanner(System.in);
    	
    	System.out.println("Enter e ");  
    	int e= sc.nextInt(); 
    	
    	System.out.println("Enter n ");  
    	BigInteger n= sc.nextBigInteger(); 
    	
    	System.out.println("Enter cipher ");  
    	String cipherText= sc.next();
    	
    	ArrayList<Integer> cipher=strConverter(cipherText);
    	System.out.println(cipher);
    	
    	int z=n.intValue();
        
    	int phi=findPhi(n);
        
        if(areCoprime(e,phi)==true)
        {
        	int d=mInverse(e,phi);
        	System.out.println("The private key is "+ d);

        	String plaintext=decrypt(cipher,d,z);
        	System.out.println("The plaintext is "+plaintext);
        	
        }
        else
        {
        	System.out.println("e and phi need to be coprime to compute the multiplicative inverse");
        }
        
    	
    	
    	
    }}    

