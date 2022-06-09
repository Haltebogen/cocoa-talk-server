package com.haltebogen.gittalk.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    /**
     * - data: json format data field
     * - message: message sent from server to client
     * - status: status code
     *
     * data format:
     * {
     *     "message": "ok"
     *     "status": 200
     *     summary:{}
     *     "data": {
     *          id:1
     *          ...
     *     }

     * }
     */
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Summary summary, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("summary", summary);
        map.put("data", data);

        return new ResponseEntity<Object>(map,status);
    }
}
