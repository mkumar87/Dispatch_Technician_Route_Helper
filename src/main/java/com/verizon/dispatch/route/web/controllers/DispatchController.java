package com.verizon.dispatch.route.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.verizon.dispatch.route.domain.User;
import com.verizon.dispatch.route.domain.TechRoute;
import com.verizon.dispatch.route.repositories.mongodb.DispatchRepository;
import com.verizon.dispatch.route.repositories.mongodb.TechRouteRepository;
import com.verizon.dispatch.route.util.EmailTemplate;

/**
 * Controller for the Cloud Foundry workshop - Spring MVC version.
 * 
 */
@Controller
public class DispatchController {
	
	
		private static final Logger logger = LoggerFactory.getLogger(DispatchController.class);
			
		@Autowired
		private DispatchRepository dispatchRepository;
		
		@Autowired
		private TechRouteRepository techRouteRepository;
		
		@Autowired (required=false) Cloud cloud;

		/**
		 * Gets basic environment information.  This is the application's
		 * default action.
		 * @param model The model for this action.
		 * @return The path to the view.
		 * @throws IOException 
		 * @throws JsonMappingException 
		 * @throws JsonParseException 
		 */
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String index(Model model) throws Exception {
				
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			String serverTime = dateFormat.format(date);
			model.addAttribute("serverTime", serverTime);
			
			String port = System.getenv("PORT");
			model.addAttribute("port", port);

			String vcapServices = System.getenv("VCAP_SERVICES");
			model.addAttribute("vcapServices", vcapServices);
			
			if (cloud == null ){
				model.addAttribute("isCloudEnvironment",false);
			} else {
				model.addAttribute("isCloudEnvironment",true);
				model.addAttribute("vcapApplication", cloud.getApplicationInstanceInfo().getProperties());
				logger.info("VCAP_SERVICES [{}] ", vcapServices);
				logger.info("VCAP_APPLICATION [{}] ", System.getenv("VCAP_APPLICATION"));
			}
			
			logger.info("Current date and time = [{}], port = [{}].", serverTime, port);
			
			/*User update = new User();
			update.setId("2548579");	    
		    update.setEmail("pavan.akurathi@gmail.com");	    
		    update.setFirstName("Pavan");	    
		    update.setLastName("Kumar");	    
		    update.setPhoneNumber("8332898007");
		    update.setZipCode("500050");	    
		    update.setPoolMode("P");
		    update.setVehicleType("4 Wheeler");
		    update.setVehicleCapacity("4");
		    update.setIsEnrolled("Y");
		    String plocation[] =  {"78.340129","17.493686"};
		    update.setLocation(plocation);
		    update.setStartDateTime("30-07-2015");	   
		    dispatchRepository.save(update);	    
		    
		    update = new User();
		    update.setId("2548580");	    
		    update.setEmail("satyapavan@gmail.com");	    
		    update.setFirstName("Satya");	    
		    update.setLastName("Pavan");	    
		    update.setPhoneNumber("121313123");
		    update.setZipCode("500050");
		     update.setPoolMode("N");
		    String slocation[] =  {"78.360294","17.484168"};
		    update.setLocation(slocation);
		    update.setStartDateTime("07/30/2015");	   
		    dispatchRepository.save(update);	
		    
		    
		    update = new User();
		    update.setId("2548581");	    
		    update.setEmail("surenganti@gmail.com");	    
		    update.setFirstName("Surendra");	    
		    update.setLastName("Ganti");	    
		    update.setPhoneNumber("8332898007");	  
		    update.setZipCode("500050");
		    update.setPoolMode("N");
		    String glocation[] =  {"78.533762","17.449104"};
		    update.setLocation(glocation);
		    update.setStartDateTime("07/30/2015");	    
		    dispatchRepository.save(update);	*/
			
			return "index";
		}
		
		
		
		@RequestMapping(value = "/faq", method = RequestMethod.GET)
		public String faq(@RequestParam("username") String userId, Model model) {
			model.addAttribute("username", userId);
			return "faq";
		}
		
		
		
		@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
		public String login(@RequestParam("username") String userId, Model model) {
			
			User user = dispatchRepository.findOne(userId);		

			if(user!=null && !user.getId().equals(""))
			{
				model.addAttribute("empid", userId);
				model.addAttribute("firstname", user.getFirstName());
				model.addAttribute("lastname", user.getLastName());
				model.addAttribute("email", user.getEmail());
				model.addAttribute("zipcode", String.valueOf(user.getZipCode()));
				model.addAttribute("status", user.getIsEnrolled());
				Date startDate = user.getStartDate();
				if(startDate!=null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
					String sd = sdf.format(startDate);
					model.addAttribute("startDate", sd.split("~")[0]);
					model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
					model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
				}
				model.addAttribute("addressDesc", user.getAddressDesc());
				model.addAttribute("poolType", user.getPoolMode());
				model.addAttribute("vehicleType", user.getVehicleType());
				model.addAttribute("capacity", String.valueOf(user.getVehicleCapacity()));
				if(user.getPoolMode()!=null && user.getPoolMode().equals("P"))
				{	
					List<User> tUsers = dispatchRepository.findByProviderUserId(userId);
					if(tUsers!=null)
					{
						StringBuilder takers = new StringBuilder();
						StringBuilder currentPool = new StringBuilder();
						takers.append("[");
						for(User u: tUsers)
						{
							if(u.getIsEnrolled().equals("Y") && u.getPoolMode().equals("N") && u.getLocation()!=null && u.getLocation().length == 2)
							{
								
								if(takers.toString().length() > 1)
									takers.append(",");
								takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
								.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
								
								if(currentPool.toString().length()>0)
									currentPool.append("<br>");
								currentPool.append(u.getFirstName()).append(" ").append(u.getLastName()).append(" | ").append(u.getPhoneNumber()).append(" | ").append(u.getEmail());
							}
													
						}
						takers.append("]");				
						model.addAttribute("others", takers.toString());
						model.addAttribute("currentPool",currentPool.toString());
					}
					
				}
				else if(user.getPoolMode()!=null && user.getPoolMode().equals("N"))
				{
					if(user.getProviderUserId()!=null)
					{
						StringBuilder providers = new StringBuilder();					
						providers.append("[");
						User u = dispatchRepository.findOne(user.getProviderUserId());
						
							if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
							{		
									providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
									.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");	
									String currentPool = u.getFirstName()+ " "+u.getLastName()+" | "+u.getPhoneNumber()+" | "+u.getEmail();
									model.addAttribute("currentPool",currentPool);
							}
						
						providers.append("]"); 
						model.addAttribute("others", providers.toString());
						
					}
				}
				double[] geoData = user.getLocation();		
				if(geoData!=null && geoData.length == 2)
				{
					model.addAttribute("location", (String.valueOf(geoData[1])+","+String.valueOf(geoData[0])));
					// set near users
				}
									
				return "poolingRequest";
			}
			else
			{
				model.addAttribute("message","Login failed. If you are a new user please register first.");
				return "index";
			}
		}
		
		@RequestMapping(value = "/report", method = RequestMethod.GET)
		public String report(@RequestParam("username") String userId, Model model) {
			
			if(userId!=null)
			{
				List<User> users = dispatchRepository.findAll();	
				StringBuilder providers = new StringBuilder();
				StringBuilder takers = new StringBuilder();
				StringBuilder mappedusers = new StringBuilder();
				providers.append("["); takers.append("[");
				mappedusers.append("[");
				Set<String> usersChecked = new HashSet<String>(); 
				for(User u: users)
				{	
					if(u.getIsEnrolled()!=null && u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
					{
						if(u.getProviderUserId()!=null && !usersChecked.contains(u.getId()))
						{
							usersChecked.add(u.getId());
							usersChecked.add(u.getProviderUserId());
							if(mappedusers.toString().length() > 1)
								mappedusers.append(",");
							mappedusers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("<br>")
							.append(u.getId()).append("<br>").append(u.getPhoneNumber()).append("<br>").append(u.getEmail())
							.append("',")
							.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("M").append("']");
							
							u = dispatchRepository.findOne(u.getProviderUserId());	
							
							mappedusers.append(",['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("<br>")
							.append(u.getId()).append("<br>").append(u.getPhoneNumber()).append("<br>").append(u.getEmail())
							.append("',")
							.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("M").append("']");
						}
						else if(u.getPoolMode().equals("P") && !usersChecked.contains(u.getId()))
						{
							List<User> pu = dispatchRepository.findByProviderUserId(u.getId());
							if(!(pu!=null && pu.size()>0))
							{
								usersChecked.add(u.getId());
								if(providers.toString().length() > 1)
									providers.append(",");
								providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("<br>")
								.append(u.getId()).append("<br>").append(u.getPhoneNumber()).append("<br>").append(u.getEmail())
								.append("',")
								.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
							}
						}
						else if(u.getPoolMode().equals("N") && !usersChecked.contains(u.getId()))
						{
							usersChecked.add(u.getId());
							if(takers.toString().length() > 1)
								takers.append(",");
							takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("<br>")
							.append(u.getId()).append("<br>").append(u.getPhoneNumber()).append("<br>").append(u.getEmail())
							.append("',")
							.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
						}
					}
				}
				providers.append("]"); takers.append("]");mappedusers.append("]");
				model.addAttribute("providers", providers.toString());
				model.addAttribute("takers", takers.toString());
				model.addAttribute("mappedusers", mappedusers.toString());			
				model.addAttribute("username", userId);
				return "reports";
			}
			else
			{
				model.addAttribute("message", "Session expired!! please login again");
				return "index";
			}
		}
		
//		@RequestMapping(value = "/submitRequest", method = RequestMethod.POST)
//		public String submitRequest(@RequestParam("userId") String userId,
//				@RequestParam("avlVehicleChk") String avlVehicleChk,
//				Model model) {
//			
//			if(userId!=null)
//			{
//				User user = dispatchRepository.findOne(userId);
//				String prevProviderUserId = user.getProviderUserId();
//				if(prevProviderUserId!=null && !prevProviderUserId.equals(avlVehicleChk))
//				{
//					User prevProvider = dispatchRepository.findOne(user.getProviderUserId());
//					prevProvider.setPickCount(prevProvider.getPickCount()>0?prevProvider.getPickCount()-1:0);
//					prevProvider.setAvailableCount(prevProvider.getVehicleCapacity()-prevProvider.getPickCount());
//					dispatchRepository.save(prevProvider);
//					// send email to old provider that user de-tagged from his pool
//					String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, prevProvider.getFirstName())
//							.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
//							.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
//							.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
//					logger.info("SENDING EMAIL");
//					CommonUtil.sendEmail(prevProvider.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);
//					// send email to the user that he is de-tagged from his current pool
//					emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, user.getFirstName())
//							.replace(EmailTemplate.FIRST_NAME, prevProvider.getFirstName()).replace(EmailTemplate.LAST_NAME, prevProvider.getLastName())
//							.replace(EmailTemplate.MOBILE, prevProvider.getPhoneNumber()).replace(EmailTemplate.EMAIL, prevProvider.getEmail())
//							.replace(EmailTemplate.ADDRESS, prevProvider.getAddressDesc());
//					logger.info("SENDING EMAIL");
//					CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
//				}
//				user.setProviderUserId(avlVehicleChk);
//				dispatchRepository.save(user);
//				model.addAttribute("empid", userId);
//				model.addAttribute("firstname", user.getFirstName());
//				model.addAttribute("lastname", user.getLastName());
//				model.addAttribute("email", user.getEmail());
//				model.addAttribute("zipcode", String.valueOf(user.getZipCode()));
//				model.addAttribute("status", user.getIsEnrolled());
//				Date startDate = user.getStartDate();
//				if(startDate!=null)
//				{
//					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
//					String sd = sdf.format(startDate);
//					model.addAttribute("startDate", sd.split("~")[0]);
//					model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
//					model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
//				}			
//				model.addAttribute("addressDesc", user.getAddressDesc());
//				model.addAttribute("poolType", user.getPoolMode());
//				model.addAttribute("vehicleType", user.getVehicleType());
//				model.addAttribute("capacity", String.valueOf(user.getVehicleCapacity()));
//				if(user.getProviderUserId()!=null)
//				{				
//					User providerUser = dispatchRepository.findOne(user.getProviderUserId());
//					if(prevProviderUserId ==null)
//						prevProviderUserId = "";
//					if(!prevProviderUserId.equals(avlVehicleChk))
//					{
//						providerUser.setPickCount(providerUser.getPickCount()+1);	
//						providerUser.setAvailableCount(providerUser.getVehicleCapacity()-providerUser.getPickCount());
//						dispatchRepository.save(providerUser);
//						// send email to new provider that user tagged to his pool
//						String emailBody = EmailTemplate.TEXT_CAR_POOL_ENROLLED_PROVIDER.replace(EmailTemplate.RECEIPIENT, providerUser.getFirstName())
//								.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
//								.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
//								.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
//						logger.info("SENDING EMAIL");
//						CommonUtil.sendEmail(providerUser.getEmail(),EmailTemplate.SUB_CAR_POOL_ENROLLED_PROVIDER, emailBody);
//						// send email to the user that he is de-tagged from his current pool
//						emailBody = EmailTemplate.TEXT_CAR_POOL_ENROLLED_USER.replace(EmailTemplate.RECEIPIENT, user.getFirstName())
//								.replace(EmailTemplate.FIRST_NAME, providerUser.getFirstName()).replace(EmailTemplate.LAST_NAME, providerUser.getLastName())
//								.replace(EmailTemplate.MOBILE, providerUser.getPhoneNumber()).replace(EmailTemplate.EMAIL, providerUser.getEmail())
//								.replace(EmailTemplate.ADDRESS, providerUser.getAddressDesc());
//						CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_ENROLLED_USER, emailBody);
//						logger.info("SENDING EMAIL");
//					}
//					StringBuilder providers = new StringBuilder();					
//					providers.append("[");
//					providers.append("['").append(providerUser.getFirstName()).append(" ").append(providerUser.getLastName()).append("',")
//							.append(providerUser.getLocation()[1]).append(",").append(providerUser.getLocation()[0]).append(",'").append("P").append("']");									
//					providers.append("]"); 
//					model.addAttribute("others", providers.toString());
//					String currentPool = providerUser.getFirstName()+ " "+providerUser.getLastName()+" | "+providerUser.getPhoneNumber()+" | "+providerUser.getEmail();
//					model.addAttribute("currentPool",currentPool);	
//				}
//				double[] geoData = user.getLocation();		
//				if(geoData!=null && geoData.length == 2)
//					model.addAttribute("location", (String.valueOf(geoData[1])+","+String.valueOf(geoData[0])));								
//				return "poolingRequest";			
//			}
//			else
//			{
//				model.addAttribute("message", "Session expired!! please login again");
//				return "index";
//			}
//		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(@RequestParam("username") String userId,			
				@RequestParam("userLocation") String location,
				@RequestParam("status") String enrolledStatus,
				@RequestParam("poolType") String poolMode,
				@RequestParam("capacity") String vehicleCapacity,
				@RequestParam("startDate") String startDate,
				@RequestParam("startTimeHr") String startTimeHr,			
				@RequestParam("startTimeMin") String startTimeMin,
				@RequestParam("addressDesc") String addressDesc,
				@RequestParam("vehicleType") String vehicleType,
				Model model) {
			
			User user = dispatchRepository.findOne(userId);		
			if(user!=null && !user.getId().equals(""))
			{
				user.setAddressDesc(addressDesc);
				if(poolMode.equals("P") && vehicleCapacity!=null && !vehicleCapacity.trim().equals(""))
					user.setVehicleCapacity(Integer.parseInt(vehicleCapacity));
				if(poolMode.equals("P") && vehicleType!=null && !vehicleType.trim().equals(""))
					user.setVehicleType(vehicleType);
				
				if(user.getPickCount()==0 && poolMode.equals("P"))
				{
					user.setPickCount(0);				
				}
				user.setAvailableCount(user.getVehicleCapacity()-user.getPickCount());
				logger.info("update Data:: "+location+","+enrolledStatus+","+poolMode+","+vehicleCapacity+","+startDate+","+startTimeHr+","+startTimeMin+","+addressDesc+","+vehicleType);			
				boolean isLocUpdate = false;
				boolean isStatusUpdate = false;
				boolean isPoolModeUpdate = false;
				boolean isStartDateUpdate = false;
				boolean isSentEmail = false;
				isStatusUpdate = !(user.getIsEnrolled()!= null && enrolledStatus.equals(user.getIsEnrolled()));
				isPoolModeUpdate = !(user.getPoolMode()!= null && poolMode.equals(user.getPoolMode()));
				
				if(location!=null && location.contains(","))
				{
					if(location.contains("("))
						location = location.substring(1, location.length()-1);
					// flip lat and long for mongo
					double [] loc = new double[2];				
					loc[0] = Double.parseDouble(location.split(",")[1].trim());
					loc[1] = Double.parseDouble(location.split(",")[0].trim());
					double [] prevLoc = user.getLocation();
					if(!(prevLoc!=null && prevLoc[0] == loc[0] && prevLoc[1] == loc[1]))
					{
						user.setLocation(loc);
						isLocUpdate = true;					
					}				
				}			
				
				Date stDate = user.getStartDate();
				String sDate=null, sHr=null, sMin=null;
				if(stDate!=null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
					String sd = sdf.format(stDate);
					sDate = sd.split("~")[0];
					sHr = sd.split("~")[1].split(":")[0];
					sMin = sd.split("~")[1].split(":")[1];
				}
			
				isStartDateUpdate = !(sDate!= null && startDate.equals(sDate) && sHr!= null && startTimeHr.equals(sHr)
						&& sMin!= null && startTimeMin.equals(sMin));
				if(isStartDateUpdate)
				{
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						String sttDate = startDate+" "+startTimeHr+":"+startTimeMin;
						user.setStartDate(sdf.parse(sttDate));
					}
					catch(Exception e)
					{
						logger.error("issue parsing date field");
						e.printStackTrace();
					}
				}
				
				
				if((isStatusUpdate || isLocUpdate || isStartDateUpdate) && user.getIsEnrolled()!= null)
				{
					if(user.getIsEnrolled().equals("N") && (isLocUpdate || isStartDateUpdate))
					{					
							if(user.getPoolMode().equals("N"))
							{
								if(user.getProviderUserId()!=null)
								{
									User pUser = dispatchRepository.findOne(user.getProviderUserId());
									pUser.setPickCount(pUser.getPickCount()>0?pUser.getPickCount()-1:0);
									pUser.setAvailableCount(pUser.getVehicleCapacity() - pUser.getPickCount());
									dispatchRepository.save(pUser);
									user.setProviderUserId(null);										
									String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, pUser.getFirstName())
											.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
											.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
											.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
									CommonUtil.sendEmail(pUser.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);								
								}							
								isSentEmail = true;
							}
							else
							{
								List<User> pUsers = dispatchRepository.findByProviderUserId(userId);
								for(User u:pUsers)
								{
									u.setProviderUserId(null);
									dispatchRepository.save(u);
									String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, u.getFirstName())
											.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
											.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
											.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
									CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
									isSentEmail = true;
								}
								user.setPickCount(0);
								user.setAvailableCount(user.getVehicleCapacity());
							}					
					}								
				}
				
				if(isPoolModeUpdate && user.getPoolMode()!= null)
				{
						if(user.getPoolMode().equals("N"))
							{
								if(!isSentEmail)
								{
									if(user.getProviderUserId()!=null)
									{
										User pUser = dispatchRepository.findOne(user.getProviderUserId());
										pUser.setPickCount(pUser.getPickCount()>0?pUser.getPickCount()-1:0);
										pUser.setAvailableCount(pUser.getVehicleCapacity() - pUser.getPickCount());
										dispatchRepository.save(pUser);
										user.setProviderUserId(null);								
										String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_PROVIDER.replace(EmailTemplate.RECEIPIENT, pUser.getFirstName())
												.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
												.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
												.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
										CommonUtil.sendEmail(pUser.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_PROVIDER, emailBody);
										isSentEmail = true;
									}							
								}
								user.setPickCount(0);
								user.setAvailableCount(user.getVehicleCapacity());
							}
							else
							{
								if(!isSentEmail)
								{
									List<User> pUsers = dispatchRepository.findByProviderUserId(userId);
									for(User u:pUsers)
									{
										u.setProviderUserId(null);
										dispatchRepository.save(u);
										String emailBody = EmailTemplate.TEXT_CAR_POOL_DROPPED_USER.replace(EmailTemplate.RECEIPIENT, u.getFirstName())
												.replace(EmailTemplate.FIRST_NAME, user.getFirstName()).replace(EmailTemplate.LAST_NAME, user.getLastName())
												.replace(EmailTemplate.MOBILE, user.getPhoneNumber()).replace(EmailTemplate.EMAIL, user.getEmail())
												.replace(EmailTemplate.ADDRESS, user.getAddressDesc());
										CommonUtil.sendEmail(user.getEmail(),EmailTemplate.SUB_CAR_POOL_DROPPED_USER, emailBody);
										isSentEmail = true;
									}
								}
								user.setPickCount(0);
								user.setAvailableCount(user.getVehicleCapacity());
							}						
				}			
				
				
				if(poolMode!=null && poolMode.equals("P"))
				{	
					List<User> tUsers = dispatchRepository.findByProviderUserId(userId);
				
					if(isLocUpdate)
					{
						List<TechRoute> deleteur = TechRouteRepository.findByUserId(user.getId());
						TechRouteRepository.delete(deleteur);
						logger.info("Route point deleted: "+(deleteur!=null?deleteur.size():0));
						TechRoute[] routepoints = CommonUtil.getRoutePoints(user.getLocation());
						if(routepoints!=null)
						{
							for(TechRoute ur: routepoints)
							{
								ur.setUserId(user.getId());
								TechRouteRepository.save(ur);
								
							}
						}
					}
					if(tUsers!=null)
					{
						StringBuilder takers = new StringBuilder();
						StringBuilder currentPool = new StringBuilder();
						takers.append("[");
						for(User u: tUsers)
						{
							if(u.getIsEnrolled().equals("Y") && u.getPoolMode().equals("N") && u.getLocation()!=null && u.getLocation().length == 2)
							{							
									if(takers.toString().length() > 1)
										takers.append(",");
									takers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
									.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("N").append("']");
							
								if(currentPool.toString().length()>0)
									currentPool.append("<br>");
								currentPool.append(u.getFirstName()).append(" ").append(u.getLastName()).append(" | ").append(u.getPhoneNumber()).append(" | ").append(u.getEmail());
							}
							
						}
						takers.append("]");				
						model.addAttribute("others", takers.toString());
						model.addAttribute("currentPool",currentPool.toString());
					}
					
				}
				else if(poolMode!=null && poolMode.equals("N"))
				{
					if(user.getProviderUserId()!=null)
					{
						StringBuilder providers = new StringBuilder();					
						providers.append("[");
						User u = dispatchRepository.findOne(user.getProviderUserId());
						
							if(u.getIsEnrolled().equals("Y") && u.getLocation()!=null && u.getLocation().length == 2)
							{		
									providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
									.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
									String currentPool = u.getFirstName()+ " "+u.getLastName()+" | "+u.getPhoneNumber()+" | "+u.getEmail();
									model.addAttribute("currentPool",currentPool);
							}					
						providers.append("]"); 
						model.addAttribute("others", providers.toString());
						
					}
				}
				
				user.setIsEnrolled(enrolledStatus);
				user.setPoolMode(poolMode);		
							
				dispatchRepository.save(user);
				
				model.addAttribute("empid", userId);
				model.addAttribute("firstname", user.getFirstName());
				model.addAttribute("lastname", user.getLastName());
				model.addAttribute("email", user.getEmail());
				model.addAttribute("zipcode", String.valueOf(user.getZipCode()));
				model.addAttribute("status", user.getIsEnrolled());
				Date attStDate = user.getStartDate();
				if(attStDate!=null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy~HH:mm");
					String sd = sdf.format(attStDate);
					model.addAttribute("startDate", sd.split("~")[0]);
					model.addAttribute("startTimeHr", sd.split("~")[1].split(":")[0]);
					model.addAttribute("startTimeMin", sd.split("~")[1].split(":")[1]);
				}
				model.addAttribute("addressDesc", user.getAddressDesc());
				model.addAttribute("poolType", user.getPoolMode());
				model.addAttribute("vehicleType", user.getVehicleType());
				model.addAttribute("capacity", String.valueOf(user.getVehicleCapacity()));
				
				double[] geoData = user.getLocation();		
				if(geoData!=null && geoData.length == 2)
				{
					model.addAttribute("location", (String.valueOf(geoData[1])+","+String.valueOf(geoData[0])));				
				}			
				if(user.getPoolMode().equals("P"))
					return "poolingRequest";
				else
				{
					List<User> providerList = new ArrayList<User>();
					// get all users who are enrolled and of type P and who have start time within less 1 hr this user start time and 
					//who have pickcount < vehicle capacity and whose route fall within this user location. 
					List<String> userIdList = dispatchRepository.getProvidersList();	// to change
					if(userIdList!=null && userIdList.size()>0)
					{
						Point point = new Point(user.getLocation()[0], user.getLocation()[1]);
						Distance distance = new Distance(1, Metrics.KILOMETERS);
						List<TechRoute> ur = TechRouteRepository.findByLocationNear(point, distance);					
						logger.info("user routes found:"+(ur!=null?ur.size():0));
						if(ur!= null && ur.size()>0)
						{
							Set<String> avUserIdSet = new HashSet<String>();
							for(TechRoute usr: ur)
							{
								avUserIdSet.add(usr.getUserId());
							}
							if(user.getProviderUserId()!=null)
								avUserIdSet.remove(user.getProviderUserId());
							logger.info("near by providers identified :"+avUserIdSet.size());
							if(avUserIdSet.size()>0)
							{
								List<User> users = dispatchRepository.getAvUsersList(avUserIdSet);
								logger.info("user list :"+(users!=null?users.size():0));
								logger.info("users size provider:: "+users.size());
								StringBuilder providers = new StringBuilder();				
								providers.append("[");
								if(users!=null)
								for(User u: users)
								{
										if(providers.toString().length() > 1)
												providers.append(",");
											providers.append("['").append(u.getFirstName()).append(" ").append(u.getLastName()).append("',")
											.append(u.getLocation()[1]).append(",").append(u.getLocation()[0]).append(",'").append("P").append("']");
											providerList.add(u);
								}
								providers.append("]");
								logger.info("provider json:"+providers.toString());
								model.addAttribute("providers", providers.toString());
								model.addAttribute("providerList",providerList);
							}
						}
					}
					return "availableVehicleDetails";
				}
			}
			else
			{
				model.addAttribute("message", "Session expired!! please login again");
				return "index";
			}
		}
		
	}	

