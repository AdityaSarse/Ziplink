package models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="Events")
public class ClickEvents {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDateTime clickDate;

    @ManyToOne
    @JoinColumn(name = "url_Mappind_id")

    private UrlMapping urlMapping;
}
