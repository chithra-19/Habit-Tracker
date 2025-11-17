package com.habittracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.habittracker.model.User;
import com.habittracker.response.dto.ChartDataResponseDTO;
import com.habittracker.response.dto.DashboardSummaryResponseDTO;
import com.habittracker.service.DashboardService;
import com.habittracker.service.UserService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private UserService userService;

    // ✅ Endpoint for dashboard summary
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponseDTO> getSummary(@AuthenticationPrincipal UserDetails userDetails) {
        User user = getUser(userDetails);
        DashboardSummaryResponseDTO summary = dashboardService.getSummary(user.getId(), user.getUsername());
        return ResponseEntity.ok(summary);
    }

    // ✅ Endpoint for chart data (last 7 days)
    @GetMapping("/chart")
    public ResponseEntity<List<ChartDataResponseDTO>> getChart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = getUser(userDetails);
        List<ChartDataResponseDTO> chartData = dashboardService.getChartData(user.getId());
        return ResponseEntity.ok(chartData);
    }

    // 🔹 Helper: fetch User entity from JWT
    private User getUser(UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
