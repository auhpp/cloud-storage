package com.cloud_storage.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private Long id;

    private String name;

    private Long yob;
}
