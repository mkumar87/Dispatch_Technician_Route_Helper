package com.verizon.dispatch.route.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.verizon.dispatch.route.domain.TechRoute;
import com.verizon.dispatch.route.util.SendGrid.Email;
import com.verizon.dispatch.route.util.SendGrid.Response;

public class CommonUtil {

	public static List<TechRoute> decodePoly(String encoded) {

	    List<TechRoute> poly = new ArrayList<TechRoute>();
	    int index = 0, len = encoded.length();
	    int lat = 0, lng = 0;
	    int seq = 0;
	    double[] loc = null;
	    while (index < len) {
	        int b, shift = 0, result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lat += dlat;

	        shift = 0;
	        result = 0;
	        do {
	            b = encoded.charAt(index++) - 63;
	            result |= (b & 0x1f) << shift;
	            shift += 5;
	        } while (b >= 0x20);
	        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	        lng += dlng;

	        TechRoute p = new TechRoute();
	        loc = new double[2];
	        loc[0] = ((double) lng / 1E5);
	        loc[1] = ((double) lat / 1E5);	        
	        p.setLocation(loc);	        	        
	        poly.add(p);
	        System.out.println(seq+"-"+loc);
	    }
	    return poly;
	}
	
	public static TechRoute[] getRoutePoints(double[] sourceLocation)
	{
		// Destination always marked as Titus - 17.438878,78.381206
		   double lat2 = 17.438878;
		   double lon2 = 78.381206;
		   double lat1 = sourceLocation[1];
		   double lon1 = sourceLocation[0];
		   TechRoute[] array = null;
		   String url = "http://maps.googleapis.com/maps/api/directions/json?";
		   List<NameValuePair> params = new LinkedList<NameValuePair>();
		   params.add(new BasicNameValuePair("origin", lat1 + "," + lon1));
		   params.add(new BasicNameValuePair("destination", lat2 + "," + lon2));		   

		   String paramString = URLEncodedUtils.format(params, "utf-8");
		   url += paramString;	
		   try{
		    HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream is = null;
			is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
			    sb.append(line + "\n");
			}
			is.close();
			reader.close();
			String result = sb.toString();
			System.out.println("Route String:: "+ result);
			JSONObject jsonObject = new JSONObject(result);
			JSONArray routeArray = jsonObject.getJSONArray("routes");
			if(routeArray.getJSONObject(0)!=null)
			{
				JSONObject routes = routeArray.getJSONObject(0);
				if(routes!=null)
				{
					JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
					if(overviewPolylines!=null)
					{
						String encodedString = overviewPolylines.getString("points");
						List<TechRoute> pointToDraw = decodePoly(encodedString);
						System.out.println("Route points:: "+ pointToDraw.size());
						if(pointToDraw.size()>0)
							array = pointToDraw.toArray(new TechRoute[pointToDraw.size()]);
					}
				}
			}
		   }
		   catch(Exception e)
		   {
			   System.err.println("Exception in finding routepoints");
			   e.printStackTrace();
		   }
		   return array;
	}
	
	public static void sendEmail(String To, String subject, String text)
	{
		Vcapenv vcapenv = new Vcapenv();
		String sendgrid_username = vcapenv.SENDGRID_USERNAME();
		String sendgrid_password = vcapenv.SENDGRID_PASSWORD();		
		SendGrid sendgrid = new SendGrid(sendgrid_username, sendgrid_password);				
		Email email = new Email();				
		email.addTo(To);
		email.setFrom("vdsi.carpooling@gmail.com");
		email.setSubject(subject);
		email.setText(text);
		try{
			Response r = sendgrid.send(email);			
		}catch(Exception e)
		{
			System.out.println("exception in sending email:");
			e.printStackTrace();
		}
	}

}