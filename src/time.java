package finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class time{
  private ArrayList<String> times=new ArrayList;
  
  public time() {
	  Input data=new Input("C:\\Users\\Owner\\git"
	  		     + "\\20332203BMS\\stop_times.txt");
	  Scanner read=new Scanner("C:\\Users\\Owner\\git"
	  		         + "\\20332203BMS\\stop_times.txt");
	  int lines =Double.POSITIVE_INFINITY;
	  while(read.hasNextLine()) {
		  read.nextLine();
		  lines++;
	  }
	  String currentLine=read.nextLine();//first line is a heading
	  int count=0;
	  while((currentLine=read.nextLine())!=null) {
		  count++;
		  times.add(currentLine);
	  }
	  times.sort(data);
  }
  public ArrayList getGivenTimes(String user) {
	  ArrayList<String>result=new ArrayList;
	  time arrivalTime=new time();
	  int num=0;
	  for(int i=0;i<times.size();i++) {
		  if(times[i].time.compareTo(arrivalTime)) {
			  result[num].add;
			  num++;
		  }
	  }
	  return result;
  }
  
}

