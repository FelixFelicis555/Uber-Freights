package com.uber.lastmile.controller;

import com.uber.lastmile.model.requests.RouteRequest;
import com.uber.lastmile.model.responses.RouteOptionResponse;
import com.uber.lastmile.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RouteController {

    @Autowired
    private PackageService packageService;

    @PostMapping({ "/route" })
    public List<RouteOptionResponse> firstPage(@RequestBody RouteRequest routeRequest) {
        return packageService.getPackageRoutesWithProximity(routeRequest);
    }

}

  
