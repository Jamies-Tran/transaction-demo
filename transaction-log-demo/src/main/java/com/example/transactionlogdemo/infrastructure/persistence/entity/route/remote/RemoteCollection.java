package com.example.transactionlogdemo.infrastructure.persistence.entity.route.remote;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoteCollection {
    String remoteCode;
    String serverId;
    String serverName;
    String host;
    String rewrite;
    String hostProxy;
    Boolean isProxy;
    Boolean isG;
}
