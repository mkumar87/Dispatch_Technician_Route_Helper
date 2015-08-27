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
import com.verizon.dispatch.route.util.CommonUtil;

/**
 * Controller for the Cloud Foundry workshop - Spring MVC version.
 * 
 */
@Controller
public class DispatchController {
	
}
