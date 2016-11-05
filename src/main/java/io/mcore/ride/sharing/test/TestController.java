package io.mcore.ride.sharing.test;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



@Controller
public class TestController {

	@Autowired DiscoveryClient client;
	
	@RequestMapping("/brands")
	public @ResponseBody String getBrands() {
	  return getClient("Vehicles");
	}

	
	  @RequestMapping("/sentence")
	  public @ResponseBody String getSentence() {
	    return 
	      getClient("LAB-4-SUBJECT") + " "
	      + getClient("LAB-4-VERB") + " "
	      + getClient("LAB-4-ARTICLE") + " "
	      + getClient("LAB-4-ADJECTIVE") + " "
	      + getClient("LAB-4-NOUN") + "."
	      ;
	  }
	  
	public String getClient(String service) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0 ) {
      	URI uri = list.get(0).getUri();
	      	if (uri !=null ) {
	      		return (new RestTemplate()).getForObject(uri,String.class);
	      	}
        }
        return null;
	}

}
