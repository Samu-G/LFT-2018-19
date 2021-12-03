public class NumberTok extends Token {
	
	public String lexeme = "";
	
	public NumberTok(int tag, String s){
		super(tag);
		lexeme=s;
	}
	
    public String toString() { return "<" + tag + ", " + lexeme + ">"; }

	
	public static final NumberTok
	zero = new NumberTok(Tag.NUM, "0");
}
