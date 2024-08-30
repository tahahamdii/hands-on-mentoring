package smartcv.auth.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartcv.auth.serviceImpl.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.addFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByMenuId(@PathVariable int menuId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByMenuId(menuId);
        return ResponseEntity.ok(feedbacks);
    }
}
