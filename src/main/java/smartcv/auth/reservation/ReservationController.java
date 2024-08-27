package smartcv.auth.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartcv.auth.serviceImpl.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public ResponseEntity<Map<String, Object>> getReservationsByUserId(@PathVariable long userId) {
        Optional<List<Reservation>> reservationsOpt = Optional.ofNullable(reservationService.getReservationsByUserId(userId));

        if (reservationsOpt.isPresent()) {
            List<Reservation> reservations = reservationsOpt.get();
            List<Map<String, Object>> reservationList = new ArrayList<>();

            for (Reservation reservation : reservations) {
                Map<String, Object> reservationData = new HashMap<>();
                reservationData.put("id", reservation.getId());
                reservationData.put("reservationDate", reservation.getReservationDate());
                reservationData.put("cancellationDeadline", reservation.getCancellationDeadline());
                reservationData.put("isCancelled", reservation.isCancelled());
                reservationData.put("menu", reservation.getMenu().getId());  // Return only menu ID or other necessary fields

                reservationList.add(reservationData);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("reservations", reservationList);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
