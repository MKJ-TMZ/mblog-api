package com.mtcode.mblogapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
@Component
public class JwtTokenUtils {

    private static Long EXPIRE_TIME;
    private static String SECRET_KEY;

    @Value("${token.expire-time}")
    public void setExpireTime(long expireTime) {
        this.EXPIRE_TIME = expireTime;
    }

    @Value("${token.secret-key}")
    public void setSecretKey(String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    /**
     * 判断token是否存在
     *
     * @param token token
     * @return 结果
     */
    public static boolean TokenIsExist(String token) {
        return token != null && !"".equals(token) && !"null".equals(token);
    }

    /**
     * 生成token
     *
     * @param subject token主题
     * @return token
     */
    public static String GenerateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 生成带角色权限的token
     *
     * @param subject token主题
     * @param authorities 角色权限
     * @return token
     */
    public static String GenerateToken(String subject, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("authorities", StringUtils.join(authorities, ","))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 生成自定义过期时间token
     *
     * @param subject token主题
     * @param expireTime 过期时间
     * @return token
     */
    public static String GenerateToken(String subject, long expireTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 获取tokenBody同时校验token是否有效（无效则会抛出异常）
     *
     * @param token token
     * @return token载荷
     */
    public static Claims GetTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
