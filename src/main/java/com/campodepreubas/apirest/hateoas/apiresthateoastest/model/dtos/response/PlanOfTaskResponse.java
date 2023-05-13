package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.StatusTaskEnum;
import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;
import java.util.List;


@Data
@Builder
public class PlanOfTaskResponse {

    private Long id;
    private String name;
    private String description;
    private StatusTaskEnum status;
    private List<TaskResponse> task;
    private UserResponse user;
    private Timestamp startDate;
}
