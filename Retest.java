import java.io.*;
import java.util.*;

class Retest{
	public static void main(String[] args)throws IOException{
	
		FileWriter csvWriter = new FileWriter("new.csv");

		Retest.print_decor();
		Retest.center_align("Script Made By Saurav!");
		Retest.print_decor();		

		Console c=System.console();
		String member[]={
				"Shivali","Saranmit","Tanmay","Varun",
				"Saurabh","Vijay","Akash","Abhishek",
				"Gaurav","Aayush","Chaitanya","Ankita",
				"Vaibhav","Hemant","Pooja"
		};

		ArrayList<String> members=new ArrayList<>(Arrays.asList(member));
		
		if("y".equals(c.readLine("Are some members absent? Press y for yes, n for no: "))){

			Retest.print_decor();
			Retest.center_align("Select absent members using id number");
			Retest.print_decor();

			int count=0;
			for(String i:members){
				System.out.println((count+1)+". "+members.get(count++));
			}

			String mid=c.readLine("Type all id seperated by ',' in single line: ");
			String[] id=mid.split(",");
			
			ArrayList<String> arr=new ArrayList<>(Arrays.asList(id));
			
			ArrayList<Integer> mids=new ArrayList<>();

			for(String i:arr)
				mids.add(Integer.parseInt(i)-1);

			Collections.sort(mids);
			Collections.reverse(mids);
 		
			System.out.println("Members absent:");			

			for(int i=0;i<mids.size();i++){
				int index_number=mids.get(i);
				System.out.println((i+1)+". "+members.remove(index_number));
			}
		}

		System.out.println("Total Present members: "+members.size());
		Retest.print_decor();
		Retest.center_align("Enter the Details");
		Retest.print_decor();
		int start_no=2;
		int end_no=Integer.parseInt(c.readLine("Enter the ending sheet number: "));
		
		int a=start_no;
		int b=end_no;
	
		int ngrp=Integer.parseInt(c.readLine("Enter the number of groups: "));			
		Retest.print_decor();
		Retest.center_align("Calculating Details For Retest");
		Retest.print_decor();
		int issue_grp=(int)Math.ceil(members.size()/(ngrp+0.0));
		int number_of_issues=end_no-1;

		System.out.println(
			"Start Number: "+start_no+
			"\nEnding Number: "+end_no+
			"\nTotal Number Of Groups: "+ngrp+
			"\nIssue Groups: "+issue_grp+
			"\nNumber of issues:"+number_of_issues
		);
	

		int span_grp=(int)Math.ceil(number_of_issues/(issue_grp+0.0));	
		int diff=issue_grp-(number_of_issues%issue_grp);
		System.out.println("Number of issue group that has less retest: "+diff);

		ArrayList<String> main_list=new ArrayList<>();

		if(diff != issue_grp){
    			for(int i=1;i<issue_grp+1;i++){
        			if(i<=issue_grp-diff){
            				main_list.add(a+" to "+(a+span_grp-1));
            				a+=span_grp;
				}else{
            				main_list.add(a+" to "+(a+span_grp-2));
			                a+=span_grp-1;
				}
    			}
		}
		
		else{
    			for(int i=1;i<issue_grp+1;i++){
        			main_list.add(a+" to "+(a+span_grp-1));
        			a+=span_grp;
    			}
		}

		Collections.shuffle(members);
		Retest.print_decor();

		System.out.printf("%-15s","Issue List");
		csvWriter.append("Issue List");
		csvWriter.append(",");
		
		for(int i=0;i<ngrp;i++){
			String grp_text="Group "+(i+1);
			System.out.printf("%-15s",grp_text);
			csvWriter.append(grp_text);
			if(i<ngrp-1)
				csvWriter.append(",");
			else
				csvWriter.append("\n");
		}
		System.out.println("");
		Retest.print_decor();
		int count=0;
		try{
    			for(int i=0;i<issue_grp;i++){
        			for(int j=0;j<ngrp;j++){
            				if(j==0){
                				System.out.printf("%-15s",main_list.get(i));
						csvWriter.append(main_list.get(i));
					}
					csvWriter.append(",");
            				System.out.printf("%-15s",members.get(count));
					csvWriter.append(members.get(count));
            				count+=1;
        			}
				System.out.println("");
				csvWriter.append("\n");
			}
		}catch(Exception e){
    			System.out.print("");
		}
		System.out.println("");
		Retest.print_decor();
		csvWriter.flush();
		csvWriter.close();
	}

	public static void print_decor(){
		for(int i=0;i<120;i++)
			System.out.print("=");
	} 
	
	public static void center_align(String input_text){
		int count=(120-input_text.length())/2;
		for(int i=0;i<count;i++)
			System.out.print(" ");
		System.out.println(input_text);
	} 

}