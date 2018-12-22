package org.fabgas.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormat extends java.text.SimpleDateFormat{
	private static final long serialVersionUID = 1L;
	/**
	 * <pre><b><i><h1 style=font-size:1.1em>strictParse</h1></i></b>
	 * <code>public {@link Date} strictParse({@link String} source)
     * 	throws {@link ParseException}</code></pre>
	 * <p>
	 * Parses text from the given string to produce a date. 
	 * The method will throw a {@link ParseException} if the string does not perfectly match the format string.
	 * <p>
	 * @param source A String that should be parsed.	
	 * @return A {@link Date} parsed from the string.
	 * @throws ParseException if the specified string cannot be parsed perfectly.
	 * 
	 */
	 public Date strictParse(String source) throws ParseException {  
		Date parsedDate = super.parse(source);
		if (!source.equals(this.format(parsedDate))) {
			throw new ParseException("Date does not fit the date format", 0);
		}
		return parsedDate;
	}
	
	public SimpleDateFormat() throws ParseException {
		super();
	}
	public SimpleDateFormat(String arg0) {
		super(arg0);
	}
	public SimpleDateFormat(String pattern,DateFormatSymbols formatSymbols) {
		super(pattern,formatSymbols);
	}
	public SimpleDateFormat(String pattern,Locale locale) {
		super(pattern,locale);
	}
}
