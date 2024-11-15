package com.starbucksorder.another_back.dto.admin.response.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespAdminMenuList {
    private Long menuId;
    private String menuName;
    private int menuPrice;
    private Long menuStatus;
    private String categories;
    private String options;
}