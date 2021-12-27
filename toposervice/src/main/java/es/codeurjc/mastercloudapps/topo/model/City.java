package es.codeurjc.mastercloudapps.topo.model;

import static lombok.AccessLevel.PROTECTED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Search with aprox words
@Document(collation = "{'locale':'es', 'strength':1}")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class City {

    @Id
    private String id;

    @Setter
    private String landscape;

    public City(String landscape) {
        this(null, landscape);
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", landscape='" + landscape + '\'' +
                '}';
    }
}