public class numeromatricolaconspazio
{
    public static boolean scan(String s)
    {
	int state = 0;
	int i = 0;

	while (state >= 0 && i < s.length()) {
	    final int ch = (int)s.charAt(i++);

	    switch (state) {
			
	    case 0:
		if ((ch==48) || (ch==50) || (ch==52) || (ch==54) || (ch==56))
		    state = 2;
		else if ((ch==49) || (ch==51) || (ch==53) || (ch==55) || (ch==57))
		    state = 3;
		else if ((ch>=97 && ch<=122) || (ch>=65 && ch<=90) || (ch==32)) //32 in ascii è lo spazio vuoto
		    state = 5;
		else
			state = -1;
		break;

	    case 2:
		if ((ch==48) || (ch==50) || (ch==52) || (ch==54) || (ch==56))
		    state = 2;
		else if ((ch==49) || (ch==51) || (ch==53) || (ch==55) || (ch==57))
		    state = 3;
		else if (ch>=65 && ch<=75)
			state = 4;
		else if (ch==32)
			state = 6;
		else
		    state = -1;
		break;

	    case 3:
		if ((ch==49) || (ch==51) || (ch==53) || (ch==55) || (ch==57))
		    state = 3;
		else if ((ch==48) || (ch==50) || (ch==52) || (ch==54) || (ch==56))
		    state = 2;
		else if (ch>=76 && ch<=90)
			state = 4;
		else if (ch==32)
			state = 6;
		else
		    state = -1;
		break;
		
		case 4:
		if (ch>=48 && ch<=57)
			state = 5;
		else if(ch>=97 && ch<=122)
			state = 4;
		else if(ch==32)
			state = 6;
		else
			state = -1;
		break;
		
		case 5:
		if ((ch>=97 && ch<=122) || (ch>=65 && ch<=90) || (ch>=48 && ch<=57) || (ch==32))
			state = 5;
		else
			state = -1;
		break;
		
		case 6:
		if(ch==32)
			state = 6;
		else if(ch>=65 && ch<=90)
			state = 4;
		else if((ch>=48 && ch<=57) || (ch>=97 && ch<=122))
			state = 5;
		break;
		
		}
		
	}
	return state == 4;
    }

    public static void main(String[] args)
    {
	System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}
