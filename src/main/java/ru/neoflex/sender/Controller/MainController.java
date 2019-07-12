package ru.neoflex.sender.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.sender.Service.MailScheduler;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MainController {
    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    @Autowired
    private DiscoveryClient discoveryClient;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "<a href='showAllServiceIds'>Show All Service Ids</a>";
    }

    @ResponseBody
    @RequestMapping(value = "/showAllServiceIds", method = RequestMethod.GET)
    public String showAllServiceIds() {
        List<String> serviceIds = this.discoveryClient.getServices();
        if (serviceIds == null || serviceIds.isEmpty()) {
            return "No services found!";
        }
        String html = "<h3>Service Ids:</h3>";
        for (String serviceId : serviceIds) {
            html += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
        }
        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/showService", method = RequestMethod.GET)
    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }
        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";
        for (ServiceInstance serviceInstance : instances) {
            html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
            html += "Host: " + serviceInstance.getHost() + "<br>";
            html += "Port: " + serviceInstance.getPort() + "<br>";
        }
        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

        return "<html>Message Sender</html>";
    }

}
