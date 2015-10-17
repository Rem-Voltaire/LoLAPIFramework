package test.lolapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

public class LoLTeamAPI {

	private final String API_VERSION;
	
	/**
	 * The base url needed to consume any web service endpoint from the riot api for this particular class.
	 */
	private String baseUrl;
	
	/**
	 * An arraylist that stores each of a summoners team ID's. This is automatically populated when a summoner's team info is requested.
	 */
	private static ArrayList<String> teamIDArrayList;
	/**
	 * Requests info about all of a summoner's teams.
	 */
	private String teamInfoBySummonerID;
	/**
	 * Requests info about an individual team.
	 */
	private String teamInfoByTeamID;
	
	/**
	 * @see URL usage -> baseURL + api item desired
	 */
	public LoLTeamAPI ()
	{
		API_VERSION = "2.4";		
		teamIDArrayList = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @param name
	 * @return Returns BufferedReader object
	 * @throws IOException
	 */
	public JSONObject getTeamInfoBySummonerID(Long id, String server)
	{
		
		baseUrl = "https://"+server+".api.pvp.net/api/lol/"+server+"/v"+API_VERSION;
		teamInfoBySummonerID = "/team/by-summoner/" + LoLSummonerAPI.getSummonerID() + "?api_key=" + LoLSummonerAPI.getAPI_KEY();
		
		try{
			URL tmpUrl = new URL(baseUrl + teamInfoBySummonerID);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(tmpUrl.openStream()));
			String tmpString = in.readLine();
			in.close();
			JSONObject obj = new JSONObject (tmpString);

			for(Object teamObj: obj.getJSONArray(LoLSummonerAPI.getSummonerID().toString()))
			{
				if(teamObj != null)
				{
					JSONObject tmp = (JSONObject) teamObj;
					teamIDArrayList.add(tmp.getString("fullId"));
				}
			}

			return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JSONObject ("{}");
		}
		//return new BufferedReader(new InputStreamReader(tmpUrl.openStream()));		
	}
	
	public JSONObject getTeamInfoByTeamID(String teamId)
	{
		teamInfoByTeamID = "/team/"+ teamId + "?api_key=" + LoLSummonerAPI.getAPI_KEY();
		
		try{
			URL tmpUrl = new URL(baseUrl + teamInfoByTeamID);
			
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

	public static ArrayList<String> getTeamIdArrayList() {
		return teamIDArrayList;
	}

	public static void main(String[] args){
		
		/*String name = JOptionPane.showInputDialog("Please enter the summoner name: ");
		String server = JOptionPane.showInputDialog("Please enter the server: ");*/
		
		String name = "silverflame";
		String server = "na";
		
		LoLTeamAPI api = new LoLTeamAPI();
		LoLSummonerAPI apiBase = new LoLSummonerAPI();
		
		JSONObject obj = apiBase.getSummonerInfoByName(name, server);
		//System.out.println(obj);
		
		obj = api.getTeamInfoBySummonerID(LoLSummonerAPI.getSummonerID(), server);		

			System.out.println("Team List: " + obj.getJSONArray(LoLSummonerAPI.getSummonerID().toString()).getJSONObject(0).getString("fullId") + "  " + obj);
			//System.out.println(obj);
		
		for (String teamId : LoLTeamAPI.getTeamIdArrayList())
		{
			obj = api.getTeamInfoByTeamID(teamId);
			System.out.println("Team: " + obj.getJSONObject(teamId.toString()).getString("name") + "  " + obj);
		}
	}
}
