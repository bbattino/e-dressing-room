package pactInitial;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.darkprograms.speech.recognizer.FlacEncoder;

public class API {
	public String sendPost(String wavString,int rate) throws Exception {
	    String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.121 Safari/535.2",
	    url = "https://www.google.com/speech-api/v2/recognize?output=json&lang=fr-FR&key=AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw&client=chromium&maxresults=1&pfilter=2";

	    URL obj = new URL(url);
	    FlacEncoder flacEncoder = new FlacEncoder();
        File flacFile = new File("data/flac.flac");
        File waveFile = new File(wavString);
        flacEncoder.convertWaveToFlac(waveFile, flacFile);
        
        
      //  GoogleResponse googleResponse = getRecognizedDataForFlac(flacFile, maxResults, 44100);
        
        //Delete converted FLAC data
        //flacFile.delete();
 	   System.out.println("sending Request To API");

	    URLConnection con =  (URLConnection) obj.openConnection();

	    // add reuqest header
	    ((HttpURLConnection) con).setRequestMethod("POST");
	    con.setRequestProperty("User-Agent", USER_AGENT);
	    //con.setRequestProperty("Content-Type", "audio/l16; rate=16000");
	    con.setRequestProperty("Content-Type", "audio/x-flac; rate="+rate);
	    //con.setRequestProperty("Content-Type", "audio/x-wav; rate=16000");


	    con.setRequestProperty("AcceptEncoding", "gzip,deflate,sdch");

	    // Send post request "Content-Type", "audio/x-flac; rate=44100"
	    con.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

	    wr.write(Files.readAllBytes(Paths
	            .get("data/flac.flac")));
	    wr.flush();
	    wr.close();

	   
	    
	    int responseCode = ((HttpURLConnection) con).getResponseCode();
	   // System.out.println("\nSending 'POST' request to URL : " + url);
	   if(responseCode!=200)  System.out.println("Response Code : " + responseCode);
	   System.out.println("Analysing API response");

	    BufferedReader in = new BufferedReader(new InputStreamReader(
	            con.getInputStream(), Charset.forName("UTF-8")));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();

	    // print result
	    
	    String reponseUtile = decoupe(response.toString());
	    if(reponseUtile!=null) System.out.println(reponseUtile);
	    else System.err.println("Commande non détéctée");

	   return reponseUtile;

	}
	
	public String sendPost(String wavString) throws Exception{
		return sendPost(wavString,16000); // parfois marche mieux avec 44100 à retester avec la Kinect
	}
	
	/** Pour ne selectionner que la phrase de la réponse gg et non sa proba***/
	private String decoupe(String GoogleResponse) {
		if(GoogleResponse.length()>54){
		String substring1 = GoogleResponse.substring(55);// les 55 premiers carateres sont inutils
		int indice = 0;
		String curentSymbol=substring1.substring(indice,indice+1);
		while(!curentSymbol.equals("\"")){ indice++; curentSymbol=substring1.substring(indice,indice+1);}
		return substring1.substring(0,indice);}
		else return null;
	
	}
	public String sendPostFromLCT(String wavString){
		String s=null;
		try {
			s=sendPost(wavString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new JavaSoundRecorder();
		return s;
		
		
	}
	
	public static void main(String[] args) throws Exception{
		API t = new API();
		//if(t.sendPost("lctdata/0a.wav",44100).length()<30) System.out.println("pas fonctionnel ");

		/*for (int i=1;i<51;i++){
			if(t.sendPost("lctdata/test0.wav",10000+1000*i)==null) System.out.println("pas fonctionnel "+i);
			else System.err.println("**********"+i+"************");
		}*/
		t.sendPost("lctdata/test0.wav");
				
	}
}
