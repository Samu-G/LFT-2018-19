public class NumberTok extends Token {
	
	public int lexeme = 0;
	
	public NumberTok(int tag, int s){
		super(tag);
		lexeme=s;
	}
	
    public String toString() { return "<" + tag + ", " + lexeme + ">"; }

	
	public static final NumberTok
	zero = new NumberTok(Tag.NUM, 0);
}
