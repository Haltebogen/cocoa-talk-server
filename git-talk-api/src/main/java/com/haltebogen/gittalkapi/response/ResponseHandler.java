package com.haltebogen.gittalk.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    /**
     * <pre>
     * 1. Class Description
     * - data: json format data field
     * - message: message sent from server to client
     * - status: status code
     *
     * - data format:
     * {
     *     "message": "ok"
     *     "status": 200
     *     "data": {
     *          id:1
     *          ...
     *     }
     * }
     *
     * 2. Example
     * - status OK: ResponseHandler.generateResponse("ok", HttpStatus.OK, result);
     * - status Bad Request:  ResponseHandler.generateResponse("Please set a password of 8 or more characters, a combination of special symbols, numbers, and letters.", HttpStatus.BAD_REQUEST, null};
     * </pre>
     */

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map,status);
    }
}
