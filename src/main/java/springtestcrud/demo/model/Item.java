package springtestcrud.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "items")
@Setter
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "A")
    @Column(name = "Id")
    private Long id;

    private String name;
    private String comment;

    public Item(String name, String comment) {
    }

    public Item(Long id, String name, String comment) {

        this.id = id;
        this.name = name;
        this.comment = comment;
    }

}