package dev.chenjr.attendance.controller;

import dev.chenjr.attendance.service.IMenuService;
import dev.chenjr.attendance.service.dto.MenuDTO;
import dev.chenjr.attendance.service.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menus")
@Tag(name = "菜单", description = "菜单CRUD")
public class MenuController {
  @Autowired
  IMenuService menuService;

  @GetMapping("")
  @Operation(description = "返回整个菜单树")
  public RestResponse<List<MenuDTO>> listMenu() {
    List<MenuDTO> rootMenu = menuService.listMenu();
    return RestResponse.okWithData(rootMenu);
  }

  @GetMapping("/{menuId}")
  @Operation(description = "按id获取菜单，不返回子节点")
  public RestResponse<MenuDTO> getMenu(
      @PathVariable long menuId

  ) {
    MenuDTO menu = menuService.getMenu(menuId);
    return RestResponse.okWithData(menu);
  }

  @PostMapping("")
  @Operation(description = "创建菜单")
  public RestResponse<MenuDTO> createMenu(
      @RequestBody @Validated MenuDTO menuDTO
  ) {
    MenuDTO created = menuService.createMenu(menuDTO);
    return RestResponse.okWithData(created);
  }

  @DeleteMapping("/{menuId}")
  @Operation(description = "删除菜单项")
  public RestResponse<MenuDTO> deleteMenu(
      @PathVariable long menuId
  ) {
    menuService.deleteMenu(menuId);
    return RestResponse.okWithMsg("删除成功！");
  }

  @PatchMapping("/{menuId}")
  @Operation(description = "修改菜单")
  public RestResponse<MenuDTO> deleteMenu(
      @PathVariable long menuId,
      @RequestBody @Validated MenuDTO menuDTO
  ) {
    menuDTO.setId(menuId);
    MenuDTO modified = menuService.modifyMenu(menuDTO);
    return RestResponse.okWithData(modified);
  }
}
