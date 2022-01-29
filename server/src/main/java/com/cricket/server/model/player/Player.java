package com.cricket.server.model.player;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @TableGenerator(name = "player_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="player_id_gen", initialValue=1000, allocationSize=10)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "player_gen")
    @Column(name = "player_id", nullable = false)
    private Integer playerId;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    private String nationality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return playerId.hashCode();
    }
}
