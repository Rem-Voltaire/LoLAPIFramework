package test.lolapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class LoLSummonerAPI {

	private static final String API_KEY = "15a35373-12e2-4135-83d4-5e124730d170";
	private final String API_VERSION;
	
	private String baseUrl;
	
	private static Long summonerID;
	private String summonerInfoByName;
	private String summonerInfoByID;
	private String summonerRunes;
	private String summonerName;
	private String summonerMasteries;
	
	/**
	 * @see URL usage -> baseURL + api item desired
	 */
	public LoLSummonerAPI ()
	{
		API_VERSION = "1.4";		
	}
	
	/**
	 * 
	 * @param name
	 * @return Returns BufferedReader object
	 * @throws IOException
	 */
	public JSONObject getSummonerInfoByName(String name, String server)
	{
		baseUrl = "https://"+server+".api.pvp.net/api/lol/"+server+"/v"+API_VERSION;
		summonerInfoByName = "/summoner/by-name/" + name + "?api_key=" + API_KEY;
		
		try{
			URL tmpUrl = new URL(baseUrl + summonerInfoByName);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			JSONObject obj = new JSONObject (tmpString);
			summonerID = obj.getJSONObject(name).getLong("id");
						
			return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
		//return new BufferedReader(new InputStreamReader(tmpUrl.openStream()));		
	}
	
	public JSONObject getSummonerInfoByID(Long id)
	{
		summonerID = id;
		summonerInfoByID = "/summoner/"+summonerID + "?api_key=" + API_KEY;
		
		try{
			URL tmpUrl = new URL(baseUrl + summonerInfoByID);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			return new JSONObject (tmpString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
	}
	
	public JSONObject getSummonerName(Long id)
	{
		summonerID = id;
		summonerName = "/summoner/"+summonerID+"/name" + "?api_key=" + API_KEY;
		
		try {	
			URL tmpUrl = new URL(baseUrl + summonerName);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			return new JSONObject (tmpString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
	}
	
	public JSONObject getSummonerMasteries(Long id)
	{
		summonerID = id;
		summonerMasteries = "/summoner/"+summonerID+"/masteries" + "?api_key=" + API_KEY;
		
		try {
			URL tmpUrl = new URL(baseUrl + summonerMasteries);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			return new JSONObject (tmpString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
	}
	
	public JSONObject getSummonerRunes(Long id)
	{
		summonerID = id;
		summonerRunes = "/summoner/"+summonerID+"/runes" + "?api_key=" + API_KEY;
		try {
			URL tmpUrl = new URL(baseUrl + summonerRunes);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			return new JSONObject (tmpString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
	}
	
	public static final String getAPI_KEY() {
		return API_KEY;
	}

	public static Long getSummonerID() {
		return summonerID;
	}

	
	public static void main(String[] args){
		
		String name = JOptionPane.showInputDialog("Please enter the summoner name: ");
		String server = JOptionPane.showInputDialog("Please enter the server: ");
		
		LoLSummonerAPI api = new LoLSummonerAPI();

			JSONObject obj = api.getSummonerInfoByName(name, server);
			System.out.println(obj);
			
			obj = api.getSummonerInfoByID(LoLSummonerAPI.getSummonerID());
			System.out.println(obj);
			
			obj = api.getSummonerName(LoLSummonerAPI.getSummonerID());
			System.out.println(obj);
			
			obj = api.getSummonerRunes(LoLSummonerAPI.getSummonerID());
			System.out.println(obj);
			
			obj = api.getSummonerMasteries(LoLSummonerAPI.getSummonerID());
			System.out.println(obj);
	}

}
