import java.io.*; 
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;

	// ... gestire i casi di (, ), {, }, +, -, *, /, =, ; ... //
	
			case '(':
				peek = ' ';
				return Token.lpt;
			
			case ')':
				peek = ' ';
				return Token.rpt;
				
			case '{':
				peek = ' ';
				return Token.lpg;
				
			case '}':
				peek = ' ';
				return Token.rpg;
				
			case '+':
				peek = ' ';
				return Token.plus;
				
			case '-':
				peek = ' ';
				return Token.minus;
				
			case '*':
				peek = ' ';
				return Token.mult;
				
			case '/':
				peek = ' ';
				return Token.div;
				
			case ';':
				peek = ' ';
				return Token.semicolon;

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
				
// ... gestire i casi di ||, <, >, <=, >=, ==, <> ... //	
				
			case '|':
				readch(br);
				if (peek == '|') {
					peek = ' ';
					return Word.or;
				} else {
					System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
				}
				
			case '<':
				readch(br);
				if (peek == '=') {
					peek = ' ';
					return Word.le;
				} else if (peek == '>') {
					peek = ' ';
					return Word.ne;
				} else
					return Word.lt;

			case '>':
				readch(br);
				if(peek == '='){
					peek = ' ';
					return Word.ge;
				} else
					return Word.gt;
				
			case '=':
				readch(br);
				if (peek == '=') {
					peek = ' ';
					return Word.eq;
				} else
					return Token.assign;
          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {
					String CheckWord = "";
					
					while((Character.isLetter(peek)) || (Character.isDigit(peek))){
						CheckWord += peek;
						peek = ' ';
						readch(br);
					}
					
						if     (CheckWord.equals("cond")) 	 return Word.cond;
						else if(CheckWord.equals("when"))	 return Word.when;
						else if(CheckWord.equals("then"))	 return Word.then;	
						else if(CheckWord.equals("else"))	 return Word.elsetok;
						else if(CheckWord.equals("while"))	 return Word.whiletok;
						else if(CheckWord.equals("do"))		 return Word.dotok;
						else if(CheckWord.equals("seq"))	 return Word.seq;
						else if(CheckWord.equals("print"))	 return Word.print;
						else if(CheckWord.equals("read"))	 return Word.read;
						else 								 return new Word(Tag.ID, CheckWord);
										
                } else if (Character.isDigit(peek)) {
					if(peek=='0'){
						readch(br);
						if(!Character.isDigit(peek)) return NumberTok.zero;
						else {
							System.err.println("Erroneous character"
                            + " after & : "  + peek );
							return null;
						}
					} else {
						String CheckDigit = "";
						while(Character.isDigit(peek)){
							CheckDigit += peek;
							peek = ' ';
							readch(br);
						} return new NumberTok(Tag.NUM, CheckDigit);
					}
                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./inserisci il codice qui.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}
