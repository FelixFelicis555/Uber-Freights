package com.uber.lastmile.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RouteRequest{
    Double riderSourceX;
    Double riderSourceY;
    Double riderDestinationX;
    Double riderDestinationY;
}