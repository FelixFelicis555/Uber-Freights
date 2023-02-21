package com.uber.lastmile.service;

import java.util.ArrayList;
import java.util.List;

import com.uber.lastmile.Constants;
import com.uber.lastmile.Utils;
import com.uber.lastmile.client.GoogleMapsClient;
import com.uber.lastmile.mapper.PackageToRouteResponse;
import com.uber.lastmile.model.db.DAOPackage;
import com.uber.lastmile.model.requests.RouteRequest;
import com.uber.lastmile.model.responses.RouteOptionResponse;
import com.uber.lastmile.repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PackageService{
    @Autowired
    private PackageRepository packageRepository;

    public DAOPackage savePackage(DAOPackage DAOPackage1) {
        System.out.println("Inside the service savePackage");
        System.out.println(DAOPackage1.toString());
        return packageRepository.save(DAOPackage1);
    }

    public DAOPackage getPackageById(Long packageId) {
        return packageRepository.findByPackageId(packageId);
    }

    public List<DAOPackage> getAllPackage() {
        return packageRepository.findAll();
    }

    public List<RouteOptionResponse> getPackageRoutesWithProximity(RouteRequest routeRequest){
        List<DAOPackage> daoPackageList = packageRepository.findAll();
        double riderSourceX = routeRequest.getRiderSourceX();
        double riderSourceY = routeRequest.getRiderSourceY();

        List<RouteOptionResponse> routeOptionResponseList = new ArrayList<>();

        for(DAOPackage daoPackage: daoPackageList){
            if(Utils.calculateDistanceInKilometer(riderSourceX, riderSourceY
                    , Double.parseDouble(daoPackage.getHubLattitude())
                    , Double.parseDouble(daoPackage.getHubLongitude())) < Constants.DISTANCE_THRESHOLD){
                routeOptionResponseList.add(PackageToRouteResponse.mapper(daoPackage));
            }
        }

        

        for(int i = 0; i < routeOptionResponseList.size(); i++){
            routeOptionResponseList.set(i, GoogleMapsClient.rewardsAndTimeFiller(routeOptionResponseList.get(i)));
        }
        return routeOptionResponseList;
    }

}