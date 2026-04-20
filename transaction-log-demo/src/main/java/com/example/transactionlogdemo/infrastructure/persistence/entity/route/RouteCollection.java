package com.example.transactionlogdemo.infrastructure.persistence.entity.route;

import com.example.transactionlogdemo.infrastructure.persistence.entity.route.path.PathCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.route.protocol.ProtocolCollection;
import com.example.transactionlogdemo.infrastructure.persistence.entity.route.remote.RemoteCollection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("routes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteCollection {
    String id;
    String code;
    String name;
    ProtocolCollection protocol;
    PathCollection path;
    RemoteCollection remote;
}
