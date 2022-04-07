package finalProject;

import java.util.ArrayList;
import java.util.Scanner;
//import ui.ProgressBar;
//import utility.*;
import java.io.File;


public class time {
	private ArrayList<String> times=new ArrayList();
	  
	  public time() {
		  Input data=new Input("C:\\Users\\Owner\\git"
		  		     + "\\20332203BMS\\stop_times.txt");
		  Scanner read=new Scanner("C:\\Users\\Owner\\git"
		  		         + "\\20332203BMS\\stop_times.txt");
		  int lines =-1;
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
	  public ArrayList<String> getGivenTimes(String user) {
		  ArrayList<String>result=new ArrayList<String>();
		  time arrivalTime=new time();
		  int num=0;
		  for(int i=0;i<times.size();i++) {
			  if(times.get(i).compareTo(arrivalTime)) {
				  result.add(times.get(i));
				  num++;
			  }
		  }
		  return result;
	  }
	  
}

