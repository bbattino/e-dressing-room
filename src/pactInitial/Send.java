package pactInitial;
	import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;


	public class Send {
		@SuppressWarnings("static-access")
		public static void main(String[] args){
			Send mes = new Send (" ");
			mes.impressionString();
		}
		private static String message;
		//private static String echoResponse ="first";
				
		public Send(String arg){
			message=arg;
		}
		
		public static void impressionString(){
			try {
				  // Connect to the pipe
				while(true){
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
					  //System.out.println("Response: " + lessSpace(echoResponse) );
					  Thread.sleep(100);
					  String response = lessSpace(echoResponse);
					  String curentWord="";
					  String xString="",yString="";
					  float x,y;
					  for (int i=0;i<response.length();i++){
						  if (response.substring(i,i+1).equals(" ")){
							   xString = curentWord;
							   yString = response.substring(i+1);
							 // Main.x=Integer.parseInt(curentWord);
							 // Main.y=Integer.parseInt(response.substring(i+1));
						  }
						  else 
							  curentWord+=response.substring(i,i+1);
						  //System.out.println("valeur de x "+xString);
						 // System.out.println("valeur de y "+yString);
						  if(xString!="" && yString!= ""){
						  if(xString.substring(0,1).equals("+")) x=Float.parseFloat(xString.substring(1));
						  else x=Float.parseFloat(xString);
						  if(yString.substring(0,1).equals("+")) y=Float.parseFloat(yString.substring(1));
						  else y=Float.parseFloat(yString);
						  
						
						  int xvalue = (int) (1100*x+644);
						  int yValue = (int) (-611*y+672);
						  
						  //System.out.println("valeur x : "+xvalue);
						  //System.out.println("valeur y : "+yValue);
						  
						  Main.x=xvalue;
						  Main.y=yValue;
						  }
					  }
					  pipe.close();  
				  }
				 
				} catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
				}
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
		}
			
			/*try {
				  // Connect to the pipe
				  RandomAccessFile pipe = new RandomAccessFile("\\\\.\\pipe\\mynamedpipe", "rw");
				  String text= Send.message;
					Thread thread = new Thread(){
						public void run(){
							try {
								Thread.sleep(1000);
								try {

									 System.out.println("Response: " + lessSpace(echoResponse) );
									pipe.close();
									impressionString();
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					};
				  
				  for(int i =0; i<text.length();i++){
					  char a = text.charAt(i);
					  String temp = Character.toString(a);
						pipe.write(temp.getBytes());
						Thread.sleep(00,1);
					}
					 // read response
				  thread.run();
				   echoResponse = pipe.readLine();

				 pipe.close();
				} catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
		}
			}
		
		public static String communicationString(){
			String response = "";
			try {
				  // Connect to the pipe
				  RandomAccessFile pipe = new RandomAccessFile("\\\\.\\pipe\\mynamedpipe", "rw");
				  String text= Send.message;
				  
				  pipe.write((moreSpace(text)).getBytes());
				
					  // read response
					  String echoResponse = pipe.readLine();
					  System.out.println("Response: " + lessSpace(echoResponse) );
					  response=lessSpace(echoResponse);
				  pipe.close();
				  
				  
				} catch (Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
				}
			return (response);
		}*/