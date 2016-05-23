package net.madicorp.autodoc.classes;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Diop Sega
 *
 */
public class Dates
{

	static Locale locale = Locale.getDefault();
	static Date actuelle = new Date();
	

	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public static Date date()
	{
		String dat = dateFormat.format(actuelle);
		Date D=null;
		try {
			D = dateFormat.parse(dat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return D;
	}
	
	public static Date date(String date)
	{
	//	String dat = dateFormat.format(date);
		Date D=null;
		try {
			D = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return D;
	}
}