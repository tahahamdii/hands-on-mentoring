package smartcv.auth.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smartcv.auth.serviceImpl.ReservationService;

import java.util.Date;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/make")
    public ResponseEntity<Reservation> makeReservation(@RequestParam long userId, @RequestParam int menuId, @RequestParam Date reservationDate) {
        Reservation reservation = reservationService.makeReservation(userId, menuId, reservationDate);

        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.badRequest().build();  // Return 400 if user or menu not found
        }
    }
}
