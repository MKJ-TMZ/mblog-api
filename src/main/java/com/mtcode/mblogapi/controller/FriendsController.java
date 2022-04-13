package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.entity.Friends;
import com.mtcode.mblogapi.entity.FriendsInfo;
import com.mtcode.mblogapi.service.FriendsInfoService;
import com.mtcode.mblogapi.service.FriendsService;
import com.mtcode.mblogapi.util.MarkdownUtils;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TangMingZhang
 * @date 2022/4/13
 */
@RestController
@RequestMapping("/friends")
@AllArgsConstructor
public class FriendsController {

    private final FriendsService friendsService;
    private final FriendsInfoService friendsInfoService;

    @GetMapping("")
    public Result get() {
        List<Friends> friendsList =
                friendsService.list(Wrappers.lambdaQuery(Friends.class).eq(Friends::getIsPublished, true));
        if (friendsList == null) {
            friendsList = new ArrayList<>();
        }
        FriendsInfo friendsInfo = friendsInfoService.getOne(Wrappers.lambdaQuery());
        if (friendsInfo == null) {
            friendsInfo = new FriendsInfo();
        } else {
            friendsInfo.setContent(MarkdownUtils.markdownToHtml(friendsInfo.getContent()));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("friendsList", friendsList);
        result.put("friendsInfo", friendsInfo);

        return Result.ok(result);
    }
}
