//package com.example.exampleproject.GeoLocation;
//
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class GeoIPTestController  {
//    private RawDBDemoGeoIPLocationService locationService;
//
//    public GeoIPTestController() throws IOException {
//        locationService = new RawDBDemoGeoIPLocationService();
//    }
//
//    @PostMapping("/GeoIPTest")
//    public GeoIP getLocation(
//            @RequestParam(value="ipAddress", required=true) String ipAddress
//    ) throws Exception {
//
//        GeoIPLocationService <String, GeoIP> locationService
//                = new RawDBDemoGeoIPLocationService();
//        return locationService.getLocation(ipAddress);
//    }
//}