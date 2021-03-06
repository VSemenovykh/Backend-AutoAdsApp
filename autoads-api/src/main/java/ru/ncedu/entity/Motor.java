package ru.ncedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "motor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 1, max = 16)
    @Column(name = "motor_type")
    private String motorType;

    @NonNull
    @Min(0)
    @Column(name = "volume")
    private double volume;
}
