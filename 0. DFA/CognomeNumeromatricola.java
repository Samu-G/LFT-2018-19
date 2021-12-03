public class CognomeNumeromatricola
{
    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

	while (state >= 0 && i < s.length()) {
	    final int ch = (int)s.charAt(i++);

	    switch (state) {
			
	    case 0:
		if(ch>=65 && ch<=75)
			state = 1;
		else if(ch>=76 && ch<=90)
			state = 2;
		else
			state = -1;
		break;

	    case 1:
		if(ch>=97 && ch<=122)
			state = 1;
		else if(ch>=48 && ch<=57)
			state = 3;
		else
		    state = -1;
		break;

	    case 2:
		if(ch>=97 && ch<=122)
			state = 2;
		else if(ch>=48 && ch<=57)
			state = 3;
		else
		    state = -1;
		break;
		
		case 3:
		if(ch>=48 && ch<=57)
			state = 3;
		else if((ch>=65 && ch<=90) || (ch>=97 && ch<=122))
			state = 4;
		else
			state = -1;
		break;
		
		case 4:
		if((ch>=65 && ch<=90) || (ch>=97 && ch<=122) || (ch>=48 && ch<=57))
			state = 4;
		else
			state = -1;
		break;
		
		}
		
	}
	return state == 3;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}