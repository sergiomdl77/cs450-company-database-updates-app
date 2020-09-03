package application;


public class DepenList 
{
	private boolean duplicateError;
	private String[] fnArray;
	private String[] relArray;
	private String[] sexArray;
	private String[] bdArray;
	public final  int MAX_DEPEN = 10;
	
	public DepenList()
	{ 
	  duplicateError = false;
	  fnArray = new String[MAX_DEPEN];
	  relArray = new String[MAX_DEPEN];
	  sexArray = new String[MAX_DEPEN];
	  bdArray = new String[MAX_DEPEN];

	  for (int i=0; i<MAX_DEPEN; i++)
	  {
		  fnArray[i] = "";
		  relArray[i] = "";
		  sexArray[i] = "";
		  bdArray[i] = "";
	  }
	}

	
	public boolean exists(String fn, String rel, String sex, String bd)
	{
		boolean found = false;
		
		for (int i=0; i<MAX_DEPEN; i++)
		{
			if (fnArray[i].compareTo(fn) == 0 && relArray[i].compareTo(rel) == 0
				&& sexArray[i].compareTo(sex) == 0 && bdArray[i].compareTo(bd) == 0)
				found = true;
		}
		
		return found;
	}
	
	
	public void addDepen(String fn, String sex, String bd, String rel, int idx)
	{
		if (exists(fn,rel,sex,bd))
			duplicateError = true;
		else
		{
			fnArray[idx] = fn;
			relArray[idx] = rel;
			sexArray[idx] = sex;
			bdArray[idx] = bd;
		}
	}
	
	
	public boolean duplicateError()
	{	return duplicateError;  }
	
	
	public String[] getFnArray()
	{	return fnArray;		}
	
	
	public String[] getRelArray()
	{	return relArray; 	}
	
	
	public String[] getSexArray()
	{	return sexArray;		}
	
	
	public String[] getBdArray()
	{	return bdArray; 	}
	
	public void reset()
	{
		duplicateError = false;
		
		for (int i=0; i<MAX_DEPEN; i++)
		{
		  fnArray[i] = "";
		  relArray[i] = "";
		  sexArray[i] = "";
		  bdArray[i] = "";
		  
		}
	}
	
	
	public String toString()
	{
		String str = "[";
		
		for (int i=0; i<MAX_DEPEN; i++)
			str+= "(" + fnArray[i] + "," + relArray[i] + "), ";
		return str + "]\n";
	}
	
	public static void main(String[] args)
	{
		
		DepenList list = new DepenList();
		
		list.addDepen("Evelyn","causin","F","01-OCT-81",1);
		list.addDepen("Shirley","causin","F","17-JUN-85",2);
		list.addDepen("Evelyn","causin","F","01-OCT-81",3);
		list.addDepen("Marvin","primo","M","24-JAN-78",4);
		
		System.out.println(list.toString());
		if (list.duplicateError())
			System.out.println("There was a duplicate.");
		
		list.reset();
		System.out.println("clear list: " + list.toString() );

	}

}
