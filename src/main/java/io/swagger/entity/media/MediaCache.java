package io.swagger.entity.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MediaCache {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Id
    String query;

    @Lob
    @Column(columnDefinition="CLOB")
    private String data;

    public static MediaCache of(String query, Object data) throws JsonProcessingException {
        return new MediaCache(query, objectMapper.writeValueAsString(data));
    }

    public <T> T readData(Class<T> clazz) {
        if (data == null) {
            return  null;
        }
        try {
            return objectMapper.readValue(data, clazz);
        } catch (Exception e) {
            return null;
        }
    }

}
