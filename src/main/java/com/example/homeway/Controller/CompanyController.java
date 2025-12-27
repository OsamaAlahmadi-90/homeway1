package com.example.homeway.Controller;
import com.example.homeway.API.ApiResponse;
import com.example.homeway.Model.User;
import com.example.homeway.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/get/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable Integer companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity<?> getCompaniesByRole(@PathVariable String role) {
        return ResponseEntity.ok(companyService.getCompaniesByRole(role));
    }

    //inspection
    @PutMapping("/inspection/approve/{requestId}/price/{price}")
    public ResponseEntity<?> approveInspectionRequest(@AuthenticationPrincipal User user, @PathVariable Integer requestId, @PathVariable Double price) {
        companyService.approveInspectionRequest(user, requestId, price);
        return ResponseEntity.ok(new ApiResponse("request approved + offer created"));
    }

    @PutMapping("/inspection/start/{requestId}")
    public ResponseEntity<?> startInspectionRequest(@AuthenticationPrincipal User user,@PathVariable Integer requestId) {
        companyService.startInspectionRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("request started (in_progress)"));
    }

    @PutMapping("/inspection/complete/{requestId}")
    public ResponseEntity<?> completeInspectionRequest(@AuthenticationPrincipal User user,@PathVariable Integer requestId) {
        companyService.completeInspectionRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("request completed"));
    }

    @PutMapping("/inspection/reject/{requestId}")
    public ResponseEntity<?> rejectInspectionRequest(@AuthenticationPrincipal User user,@PathVariable Integer requestId) {
        companyService.rejectInspectionRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("request rejected"));
    }

    @PutMapping("/moving/approve/{requestId}/price/{price}")
    public ResponseEntity<?> approveMoving(@AuthenticationPrincipal User user, @PathVariable Integer requestId, @PathVariable Double price) {
        companyService.approveMovingRequest(user, requestId, price);
        return ResponseEntity.ok(new ApiResponse("moving request approved and offer created"));
    }

    @PutMapping("/moving/start/{requestId}")
    public ResponseEntity<?> startMoving(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.startMovingRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("moving request started and is in_progress"));
    }

    @PutMapping("/moving/complete/{requestId}")
    public ResponseEntity<?> completeMoving(@AuthenticationPrincipal User user,@PathVariable Integer requestId) {
        companyService.completeMovingRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("moving request completed"));
    }

    @PutMapping("/moving/reject/{requestId}")
    public ResponseEntity<?> rejectMoving(@AuthenticationPrincipal User user,@PathVariable Integer requestId) {
        companyService.rejectMovingRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("moving request rejected"));
    }

    @PutMapping("/maintenance/approve/{requestId}/price/{price}")
    public ResponseEntity<?> approveMaintenance(@AuthenticationPrincipal User user,@PathVariable Integer requestId,@PathVariable Double price) {
        companyService.approveMaintenanceRequest(user, requestId, price);
        return ResponseEntity.ok(new ApiResponse("maintenance request approved and offer created"));
    }

    @PutMapping("/maintenance/start/{requestId}")
    public ResponseEntity<?> startMaintenance(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.startMaintenanceRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("maintenance request started and is in_progress"));
    }

    @PutMapping("/maintenance/complete/{requestId}")
    public ResponseEntity<?> completeMaintenance(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.completeMaintenanceRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("maintenance request completed"));
    }

    @PutMapping("/maintenance/reject/{requestId}")
    public ResponseEntity<?> rejectMaintenance(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.rejectMaintenanceRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("maintenance request rejected"));
    }

    @PutMapping("/redesign/approve/{requestId}/price/{price}")
    public ResponseEntity<?> approveRedesign(@AuthenticationPrincipal User user, @PathVariable Integer requestId, @PathVariable Double price) {
        companyService.approveRedesignRequest(user, requestId, price);
        return ResponseEntity.ok(new ApiResponse("redesign request approved and offer created"));
    }

    @PutMapping("/redesign/start/{requestId}")
    public ResponseEntity<?> startRedesign(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.startRedesignRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("redesign request started and is in_progress"));
    }

    @PutMapping("/redesign/complete/{requestId}")
    public ResponseEntity<?> completeRedesign(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.completeRedesignRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("redesign request completed"));
    }

    @PutMapping("/redesign/reject/{requestId}")
    public ResponseEntity<?> rejectRedesign(@AuthenticationPrincipal User user, @PathVariable Integer requestId) {
        companyService.rejectRedesignRequest(user, requestId);
        return ResponseEntity.ok(new ApiResponse("redesign request rejected"));
    }
}
