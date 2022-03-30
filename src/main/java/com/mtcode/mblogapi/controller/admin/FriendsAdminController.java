package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.Friends;
import com.mtcode.mblogapi.entity.FriendsInfo;
import com.mtcode.mblogapi.service.FriendsInfoService;
import com.mtcode.mblogapi.service.FriendsService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/3/30
 */
@RestController
@RequestMapping("/admin/friends")
@AllArgsConstructor
public class FriendsAdminController {

    private final FriendsService friendsService;
    private final FriendsInfoService friendsInfoService;

    @GetMapping("/page")
    public Result page(Page<Friends> query) {
        return Result.ok(friendsService.page(query));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Friends friends) {
        friendsService.updateOrSaveFriends(friends);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Friends friends) {
        boolean result = friendsService.updateById(friends);
        if (result) {
            return Result.ok();
        } else {
            return Result.create(400, "更新失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean result = friendsService.removeById(id);
        if (result) {
            return Result.ok();
        } else {
            return Result.create(400, "删除失败");
        }
    }

    @PostMapping("/info/save")
    public Result save(@RequestBody FriendsInfo friendsInfo) {
        friendsInfoService.updateOrSaveFriendsInfo(friendsInfo);
        return Result.ok();
    }

    @GetMapping("/info/detail")
    public Result detail() {
        FriendsInfo friendsInfo = friendsInfoService.getOne(Wrappers.lambdaQuery());
        if (friendsInfo == null) {
            friendsInfo = new FriendsInfo();
        }
        return Result.ok(friendsInfo);
    }
}
