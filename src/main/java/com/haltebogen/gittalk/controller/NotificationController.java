package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;
}
