package com.example.GeolocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geolocation")
public class GeolocationController {
    private final GeolocationService geolocationService;

    @Autowired
    public GeolocationController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GetMapping("/evaluate-geofences")
    public ResponseEntity<BatchEvaluateGeofencesResponse> evaluateGeofences(
            @RequestParam String collectionName,
            @RequestParam String deviceId,
            @RequestParam double latitude,
            @RequestParam double longitude) {

        BatchEvaluateGeofencesResponse response = geolocationService.evaluateGeofences(collectionName, deviceId, latitude, longitude);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-device-position")
    public ResponseEntity<BatchGetDevicePositionResponse> getDevicePosition(
            @RequestParam String trackerName,
            @RequestParam String deviceId) {

        BatchGetDevicePositionResponse response = geolocationService.getDevicePosition(trackerName, deviceId);
        return ResponseEntity.ok(response);
    }
}

