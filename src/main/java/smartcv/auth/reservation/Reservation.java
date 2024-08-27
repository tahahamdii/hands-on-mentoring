package smartcv.auth.reservation;


import jakarta.persistence.*;
import lombok.*;
import smartcv.auth.menu.Menu;
import smartcv.auth.model.User;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private Date reservationDate;
    private Date cancellationDeadline;

    private boolean isCancelled;

    public Reservation(User user, Menu menu, Date reservationDate, Date cancellationDeadline) {
        this.user = user;
        this.menu = menu;
        this.reservationDate = reservationDate;
        this.cancellationDeadline = cancellationDeadline;
        this.isCancelled = false;
    }
}