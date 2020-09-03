package application;


public class ProjList
{
	private int totalHours;
	private boolean duplicateError;
	private boolean removeError;
	private int[] pnArray;
	private int[] phArray;
	public final  int MAX_PROJS = 10;
	
	public ProjList()
	{ 
	  totalHours = 0;
	  duplicateError = false;
	  removeError = false;
	  pnArray = new int[MAX_PROJS];
	  phArray = new int[MAX_PROJS];

	  for (int i=0; i<MAX_PROJS; i++)
	  {
		  pnArray[i] = -1;
		  phArray[i] = 0;
	  }
	}

	
	public boolean exists(int pn)
	{
		boolean found = false;
		
		for (int i=0; i<MAX_PROJS; i++)
		{
			if (pnArray[i] == pn)
				found = true;
		}
		
		return found;
	}
	
	
	public void addProj(int pn, int ph, int idx)
	{
		if (exists(pn))
			duplicateError = true;
		else
		{
			pnArray[idx] = pn;
			phArray[idx] = ph;
			totalHours+= ph;
		}
	}
	
	
	public int getTotalHours()
	{	return totalHours;	}

	
	public boolean duplicateError()
	{	return duplicateError;  }
	
	
	public boolean removeError()
	{	return removeError;   }
	
	
	public int[] getPnArray()
	{	return pnArray;		}
	
	
	public int[] getPhArray()
	{	return phArray; 	}
	
	
	public void reset()
	{
		totalHours = 0;
		duplicateError = false;
		removeError = false;
		for (int i=0; i<MAX_PROJS; i++)
		{
		  pnArray[i] = -1;
		  phArray[i] = 0;
		}
	}
	
	
	public String toString()
	{
		String str = "[";
		
		for (int i=0; i<MAX_PROJS; i++)
			str+= "(" + pnArray[i] + "," + phArray[i] + "),  ";
		return str + "]\n";
	}
	
	public static void main(String[] args)
	{
		
		ProjList list = new ProjList();
		
		list.addProj(1,2,1);
		list.addProj(3,2,2);
		list.addProj(5,3,3);
		list.addProj(3,1,4);
		list.addProj(6, 50, 6);
		
		System.out.println("total hours: " + list.getTotalHours());
		System.out.println(list.toString());
		if (list.duplicateError())
			System.out.println("There was a duplicate.");
		
		if (list.getTotalHours() > 40)
			System.out.println("The hours exceeded 40:  " + list.getTotalHours() + "\n");

		list.reset();
		System.out.println("clear list: " + list.toString() );
		System.out.println("total hours: " + list.getTotalHours() );

	}

}
