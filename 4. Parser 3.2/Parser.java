import java.io.*;

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
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
        switch(look.tag)
        {
            case '(':
                stat();
                match(Tag.EOF);
            break;

            default:
                error("Errore: funzione prog()");
            break;
        }
    }

    private void statlist() {
        switch(look.tag)
        {
            case '(':
                stat();
                statlistp();
            break;

            default:
                error("Errore: funzione statlist()");
            break;
        }
    }

    private void statlistp(){
        switch(look.tag)
        {
            case '(':
                stat();
                statlistp();
            break;

            case ')':
            break;

            default:
                error("Errore: funzione statlistp()");
            break;
        }
    }

    private void stat(){
        switch(look.tag)
        {
            case '(':
                match('(');
                statp();
                match(')');
            break;

            default:
                error("Errore: funzione stat()");
            break;
        }
    }

    private void statp(){
        switch(look.tag)
        {
            case '=':
                match('=');
                match(Tag.ID);
                expr();
            break;

            case 259: //cond
                match(259);
                bexpr();
                stat();
                elseopt();
            break;

            case 263: //while
                match(263);
                bexpr();
                stat();
            break;

            case 264: //do
                match(264);
                statlist();
            break;

            case 266: //print
                match(266);
                exprlist();
            break;

            case 267: //read
                match(267);
                match(Tag.ID);
            break;

            default:
                error("Errore: funzione statp()");
            break;
        }
    }

    private void elseopt(){
        switch(look.tag)
        {
            case '(':
                match('(');
                match(262);
                stat();
                match(')');
            break;

            case ')':
            break;

            default:
                error("Errore: funzione elseopt()");
            break;

        }
    }

    private void bexpr(){
        switch(look.tag)
        {
            case '(':
                match('(');
                bexprp();
                match(')');
            break;

            default:
                error("Errore: funzione bexpr()");
            break;
        }
    }

    private void bexprp(){
        switch(look.tag)
        {
            case 258: //relop
                match(258);
                expr();
                expr();
            break;

            default:
                error("Errore: funzione bexprp()");
            break;
        }
    }

    private void expr(){
        switch(look.tag)
        {
            case 256: //num
                match(256);
            break;

            case 257: //ID
                match(257);
            break;

            case '(':
                match('(');
                exprp();
                match(')');
            break;

            default:
                error("Errore: funzione expr()");
            break;
        }
    }

    private void exprp(){
        switch(look.tag)
        {
            case '+':
                match('+');
                exprlist();
            break;

            case '-':
                match('-');
                expr();
                expr();
            break;

            case '*':
                match('*');
                exprlist();
            break;

            case '/':
                match('/');
                expr();
                expr();
            break;

            default:
                error("Errore: funzione exprp()");
            break;
        }
    }

    private void exprlist(){
        switch(look.tag)
        {
            case 256: //NUM
                expr();
                exprlistp();
            break;

            case 257: //ID
                expr();
                exprlistp();
            break;

            case '(':
                expr();
                exprlistp();
            break;

            case ')':
            break;
            
            default:
                error("Errore: funzione exprlist()");
            break;
        }    
    }

    private void exprlistp(){
        switch(look.tag)
        {
            case 256: //NUM
                expr();
                exprlistp();
            break;

            case 257: //ID
                expr();
                exprlistp();
            break;

            case '(':
                expr();
                exprlistp();
            break;

            case ')':
            break;

            default:
                error("Errore: funzione exprlist()");
            break;
        }
    }

   
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./inserisci il codice qui.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}