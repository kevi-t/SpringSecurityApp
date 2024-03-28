package com.project.lab.utils;

import com.project.lab.responses.UniversalResponse;

public class SessionMsg {
    public UniversalResponse getSessionErrorMsg(){
        return UniversalResponse.builder().status("1").message("Session time Expired").build();
    }
}