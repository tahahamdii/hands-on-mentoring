package smartcv.auth.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartcv.auth.serviceImpl.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/make")
    public ResponseEntity<Reservation> makeReservation(
            @RequestParam long userId,
            @RequestParam int menuId,
            @RequestParam String reservationDate) {

        try {
            // Convert the reservationDate string to Date
            Date date = dateFormat.parse(reservationDate);

            Reservation reservation = reservationService.makeReservation(userId, menuId, date);

            if (reservation != null) {
                return ResponseEntity.ok(reservation);
            } else {
                return ResponseEntity.badRequest().build();  // Return 400 if user or menu not found
            }
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(null);  // Return 400 if date parsing fails
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelReservation(@RequestParam int reservationId) {
        boolean success = reservationService.cancelReservation(reservationId);

        if (success) {
            return ResponseEntity.ok().build(); // Return 200 OK if cancellation was successful
        } else {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if reservation cannot be canceled
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable long userId) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }
}
