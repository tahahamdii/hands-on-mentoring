package smartcv.auth.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date date_birthday;
    private String address;
    private String user_abstract;
    private String mobile;
    private String linkedin_link;
    private String other_link;
    private List<String> roles;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
