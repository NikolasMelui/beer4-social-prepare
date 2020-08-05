package beer4.social.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
}
