package pactInitial;

import java.io.RandomAccessFile;


public class Send {
	private static String message;
			
	public Send(String arg){
		message=arg;
	}
	
	public static void impressionString(){
		try {
			  // Connect to the pipe
			  RandomAccessFile pipe = new RandomAccessFile("\\\\.\\pipe\\mynamedpipe", "rw");
			  String text= Send.message;
			  
			  for(int i =0; i<text.length();i++){
				  char a = text.charAt(i);
				  String temp = Character.toString(a);
					pipe.write(temp.getBytes());
					Thread.sleep(00,1);
				}
			
				  // read response
				  String echoResponse = pipe.readLine();
				  System.out.println("Response: " + lessSpace(echoResponse) );
			  pipe.close();
			} catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
	}}
	
	public static String communicationString(){
		String answer ="";
		try {
			  // Connect to the pipe
			  RandomAccessFile pipe = new RandomAccessFile("\\\\.\\pipe\\mynamedpipe", "rw");
			  String text= Send.message;
			  
			  pipe.write((moreSpace(text)).getBytes());
			
				  // read response
				  String echoResponse = pipe.readLine();
				  System.out.println("Response: " + lessSpace(echoResponse) );
				  answer = lessSpace(echoResponse);
			  pipe.close();
			} catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
	}
		return answer;
		}
	
	
	public static String moreSpace(String arg){
		StringBuffer stbuf = new StringBuffer();
		stbuf.append(arg);
		for (int i=0; i<arg.length();i++){
			stbuf.insert(2*i+1," ");
		}
		return stbuf.toString();
	}
	
	public static String lessSpace(String arg){
		StringBuffer stbuf = new StringBuffer();
		stbuf.append(arg);
		for (int i=((arg.length())/2); i>=1;i--){
			stbuf.deleteCharAt(2*i-1);
		}
		return stbuf.toString();
	}
	
	public static void main(String[] args){
	}
}


