import java.io.*; 

public class Valutatore {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Valutatore(Lexer l, BufferedReader br) { 
	lex = l; 
	pbr = br;
	move(); 
    }
   
    void move() { 
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) { 
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) move();
        } else error("syntax error");
        }

    public void start() { 
	    int expr_val;

    	switch(look.tag){

        case '(':
            expr_val = expr();
            match(Tag.EOF);
            System.out.println(expr_val);
        break;

        case Tag.NUM:
            expr_val = expr();
            match(Tag.EOF);
            System.out.println(expr_val);
        break;

        default:
            expr_val = 0;
            error("Errore: funzione start()");
        break;
        }
    }

    private int expr() { 
	    int term_val, exprp_val;

	    switch(look.tag){

            case '(':
                term_val = term();
                exprp_val = exprp(term_val);
            break;

            case Tag.NUM:
                term_val = term();
                exprp_val = exprp(term_val);
            break;

            default:
                term_val = 0;
                exprp_val = 0;
                error("Errore: funzione expr()");
            break;

        }  
              
        return exprp_val;
    }

    private int exprp(int exprp_i) {
	    int term_val, exprp_val;
        
        switch (look.tag) {
            
            case '+':
                match('+');
                term_val = term();
                exprp_val = exprp(exprp_i + term_val);
            break;

            case '-':
                match('-');
                term_val = term();
                exprp_val = exprp(exprp_i - term_val);
            break;

            case ')':
                exprp_val = exprp_i;
            break;

            case Tag.EOF:
                exprp_val = exprp_i;
            break;

            default:
                term_val = 0;
                exprp_val = 0;
                error("Errore: funzione exprp()");
            break;

        }
        
        return exprp_val;
    }

    private int term() { 

    int termp_val, term_val;
        
        switch(look.tag)
        {
            case'(':
                termp_val = fact();
                term_val = termp(termp_val);
            break;

            case Tag.NUM:
                termp_val = fact();
                term_val = termp(termp_val);
            break;
    
            default:
                termp_val = 0;
                term_val = 0;
                error("Errore: funzione term()");
            break;
        }

        return term_val;
    }
    
    private int termp(int termp_i) { 

    int term_val, termp_val;

        switch(look.tag)
        {
            case '*':
                match('*');
                term_val = fact();
                termp_val = termp(termp_i * term_val);
            break;
    
            case '/':
                match('/');
                term_val = fact();
                termp_val = termp(termp_i / term_val);
            break;

            case '+':
                termp_val = termp_i;
            break;

            case '-':
                termp_val = termp_i;
            break;

            case ')':
                termp_val = termp_i;
            break;

            case Tag.EOF:
                termp_val = termp_i;
            break;

            default:
                term_val = 0;
                termp_val = 0;
                error("Errore: funzione termp()");
            break;
        }

    return termp_val;
    }
    
    private int fact() { 

    int fact_val;

        switch(look.tag)
        {
            case '(':
                match('(');
                fact_val = expr();
                match(')');
            break;

            case Tag.NUM:
                fact_val = ((NumberTok)look).lexeme;
                match(Tag.NUM);
            break;

            default:
                fact_val = 0;
                error("Errore: funzione fact()");
            break;
        }

    return fact_val;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./inserisci il codice qui.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore valutatore = new Valutatore(lex, br);
            valutatore.start();
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
