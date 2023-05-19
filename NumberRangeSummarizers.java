
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.StringBuilder;
 
 public class  NumberRangeSummarizers implements NumberRangeSummarize
 {
	 private Collection<Integer> ranges = new ArrayList<Integer>();
	 
	 public void set_ranges(Collection<Integer> range)
	 {
		 ranges = range;
	 }
	 public Collection<Integer> get_ranges()
	 {
		 return ranges;
	 }
	 
	 /*
	 collects the input data.
	 */
	 public Collection<Integer> collect(String input) throws ClassCastException
	 {
		 ranges.clear();
		 //ignore all non-numerical characters except commas.
		 String[] string = input.replaceAll("[^0-9.,]+","").split(",");
		 for(int i = 0; i < string.length; i++)
		 {
			 ranges.add(Integer.parseInt(string[i]));
		 }
		 
		 return ranges;
	 }
	 
	 /*
	 The summarizeCollection method takes the Collection
	 input data and groups the numbers into ranges when they are sequential.
	 */
	 public String summarizeCollection(Collection<Integer> col) throws ClassCastException
	 {
		 StringBuilder myString = new StringBuilder("");
		 ArrayList<Integer> array  = new ArrayList<Integer>(col);
		 
		//list sort
		 array.sort(Comparator.naturalOrder()); 
		 
		 StringBuilder str = new StringBuilder("");
		 for(int i = 0; i < array.size(); i++)
		 {
			 //range elements is they are sequential 
			 if(i!=0 && array.get(i)-array.get(i-1)==1)
			 {
				 if(str.toString().equals(""))
				 {
					 str.append(array.get(i-1).toString()).append("-").append(array.get(i).toString());
				 }
				 else if(!str.toString().equals(""))
				 {
					 //ranges update
					 str.replace(str.indexOf("-")+1,str.length(),array.get(i).toString());
				 }
				 //append at the end of the range
				 if(i == array.size()-1)
				 {
					 myString.append(str);
				 }
			 }
	
			 //add non-sequential to the string 
			 else if(i!=0 && array.get(i)-array.get(i-1)!=1)
			 {
				//Ensures duplicates are served
				 if(!str.toString().equals("") && array.get(i)-array.get(i-1)!=0) 
				 {
					 myString.append(str).append(", ");
					 str.setLength(0);
				 }
				 else if(str.toString().equals("") && array.get(i)-array.get(i-1)!=0) //cater for duplicates
				 {
					 myString.append(array.get(i-1).toString()).append(", ");
				 }
		
				 if(i == array.size()-1)
				 {
					 if(str.toString().equals(""))
					 {
						 myString.append(array.get(i).toString());
					 }
					 else
					 {
						//Ensures long duplicates are served
						 myString.append(str);
					 }
				 }
			 }
		 }
		 // output ranged string 
		 return myString.toString();
	 }
	 public static void main(String[] args)
	 {
		 NumberRangeSummarizers object =new  NumberRangeSummarizers();
		 object.set_ranges(object.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31"));
		 String string = object.summarizeCollection(object.get_ranges());
		 System.out.println(string);	
	 }
 }
 
