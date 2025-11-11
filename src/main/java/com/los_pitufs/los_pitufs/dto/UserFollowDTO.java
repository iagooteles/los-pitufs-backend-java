package com.los_pitufs.los_pitufs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFollowDTO {
    private Long id;
    private String username;
    private String profilePictureUrl;
}
