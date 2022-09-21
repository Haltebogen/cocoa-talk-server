package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.PaginationResponseDto;
import com.haltebogen.gittalk.dto.notification.NotificationResponseDto;
import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.response.ResponseHandler;
import com.haltebogen.gittalk.service.notification.NotificationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary="알림 리스트", description = "알림 이벤트 리스트를 제공한다.")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=500, message = "Server Error")
    })
    @GetMapping("")
    public ResponseEntity<Object> searchMember(
            @PageableDefault Pageable pageable,
            Principal principal
    ) {
        String memberId = principal.getName();
        Page<Notification> pageData = notificationService.getNotifications(pageable, Long.valueOf(memberId));
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, new PaginationResponseDto(
                pageData.getTotalPages(),
                pageData.hasNext(),
                pageData.stream().map(NotificationResponseDto::new).collect(Collectors.toList())
        ));
    }

    @PostMapping("/{notificationId}")
    public ResponseEntity<Object> removeNotification(
            @PathVariable Long notificationId
    ) {
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, {});
    }
}
