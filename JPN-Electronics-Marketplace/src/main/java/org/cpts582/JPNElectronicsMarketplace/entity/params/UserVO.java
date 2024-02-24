package org.cpts582.JPNElectronicsMarketplace.entity.params;

import lombok.Data;

@Data
public class UserVO {
    private Long userId;

    private String userEmail;

    private String userName;

    private String userContactInfo;
}
