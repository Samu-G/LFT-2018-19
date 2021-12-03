class Provaascii{

	public static boolean controllo(String s){
		
		boolean check = false;
		
		for(int i=0; i<s.length(); i++){
			int ch = (int)s.charAt(i);
			
				if((ch>=65 && ch<=90) || (ch>=97 && ch<=122) || (ch>=48 && ch<=57))
					check = true;
				else check = false;
		}
	
	return check;
	}
	
	public static void codiceascii(String s){
		
		for(int i=0; i<s.length(); i++){
		final int ch = s.charAt(i);
		System.out.println("il codice ascii della "+i+":"+ch);
		}
	}


	public static void main(String[]args){
		codiceascii(args[0]);
		codiceascii(args[1]);
	}
}