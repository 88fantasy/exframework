package org.exframework.gateway.sso.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.exframework.gateway.sso.SsoConstants;
import org.exframework.gateway.sso.entity.KeyPairString;
import org.exframework.gateway.sso.utils.CaptchaUtil;
import org.exframework.support.rest.entity.ApiResponse;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.exception.ApiException;
import org.exframework.support.rest.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

/**
 * 工具
 *
 * @author: rwe
 * @version: 1.0
 * @date: 2022-5-15
 */
@Api(tags = "工具")
@RestController
@RequestMapping(value = "/tool")
public class ToolController {

    private final Logger log = LoggerFactory.getLogger(ToolController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "随机盐值")
    @GetMapping("/random")
    @ResponseBody
    public ApiResponseData<String> getRandom(@RequestParam("username") String username) {
        if (StrUtil.isBlank(username)) {
            return ApiResponseData.paramError();
        }

        KeyPair keyPair = SecureUtil.generateKeyPair("SM2", 1024, username.getBytes(StandardCharsets.UTF_8));

        KeyPairString keyPairString = new KeyPairString(keyPair);
        try {
            String json = new ObjectMapper().writeValueAsString(keyPairString);
            redisTemplate.opsForValue().set(SsoConstants.KEY_SALT_PREFIX.concat(username), json, 120, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            throw new ServerException("json转换失败");
        }
        //将q值提取出来并且转成16进制
        String q = HexUtil.encodeHexStr(((BCECPublicKey) keyPair.getPublic()).getQ().getEncoded(false));
        return new ApiResponseData<>(q);
    }

}
