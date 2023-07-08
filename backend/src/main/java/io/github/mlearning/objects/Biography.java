package io.github.mlearning.objects;


import io.github.mlearning.entities.impl.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Biography {

    private String name;
    private Map<Long, String> biographyMap;

    public Biography() {
    }

    public Biography(UserEntity entity) {
        this.name = entity.getUsername() + "'s biography";
        this.biographyMap = new HashMap<>();

        this.biographyMap.put(System.currentTimeMillis(), "This is the first login");
    }

    public void addLogin() {
        this.biographyMap.put(System.currentTimeMillis(), "This is another login");
    }
}
