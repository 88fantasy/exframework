package org.exframework.portal.auth.config;

import com.usthe.sureness.handler.HandlerManager;
import com.usthe.sureness.matcher.DefaultPathRoleMatcher;
import com.usthe.sureness.matcher.PathTreeProvider;
import com.usthe.sureness.matcher.TreePathRoleMatcher;
import com.usthe.sureness.mgt.SurenessSecurityManager;
import com.usthe.sureness.processor.DefaultProcessorManager;
import com.usthe.sureness.processor.Processor;
import com.usthe.sureness.processor.ProcessorManager;
import com.usthe.sureness.processor.support.JwtProcessor;
import com.usthe.sureness.processor.support.NoneProcessor;
import com.usthe.sureness.provider.SurenessAccountProvider;
import com.usthe.sureness.provider.annotation.AnnotationPathTreeProvider;
import com.usthe.sureness.subject.SubjectFactory;
import com.usthe.sureness.subject.SurenessSubjectFactory;
import com.usthe.sureness.subject.creater.JwtSubjectServletCreator;
import com.usthe.sureness.subject.creater.NoneSubjectServletCreator;
import com.usthe.sureness.util.JsonWebTokenUtil;
import org.exframework.portal.auth.service.UserService;
import org.exframework.portal.auth.service.impl.DefaultUserService;
import org.exframework.portal.auth.sureness.handler.JwtRefreshTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author rwe
 * @date 2022/7/11 16:18
 **/
@Configuration
@ConditionalOnProperty(prefix = AuthConfiguration.PREFIX, name = "user")
@EnableConfigurationProperties(value = AuthProperties.class)
public class AuthConfiguration {

    final static String PREFIX = "exframework.auth";

    @Autowired
    AuthProperties properties;

    @Bean
    @ConditionalOnMissingBean
    UserService userService() {
        return new DefaultUserService(properties.getUser(), properties.getPassword(), properties.getName(), properties.getPermissions());
    }

    @Bean
    @ConditionalOnMissingBean
    PathTreeProvider pathTreeProvider() {
        return new PathTreeProvider() {
            @Override
            public Set<String> providePathData() {
                return properties.getResourceRole() == null ? Collections.emptySet() : new HashSet<>(properties.getResourceRole());
            }

            @Override
            public Set<String> provideExcludedResource() {
                return properties.getExcludedResource() == null ? Collections.emptySet() : new HashSet<>(properties.getExcludedResource());
            }
        };
    }

    /**
     * jwt secret key
     */
    private static final String TOM_SECRET_KEY = "?::4s9ssf2sf4sed45pf):" +
            "RnLN7XNn4wARoQXizIv6MHUsIV+EFfiMw/x7R0ntu4aWr/CWuApcFaj" +
            "CyaFv0bwq2Eik0jdrKUtsA6bx3sDJeFV643R+YYzGMRIqcBIp6AKA98" +
            "GM2RIqcBIp6-?::4390fsf4sdl6opf)4ZI:tdQMtcQQ14pkOAQdQ546";

    @Bean
    @ConditionalOnMissingBean
    ProcessorManager processorManager(SurenessAccountProvider accountProvider) {
        // process init
        List<Processor> processorList = new LinkedList<>();
        // use default none processor
        NoneProcessor noneProcessor = new NoneProcessor();
        processorList.add(noneProcessor);
        // use default jwt processor
        JwtProcessor jwtProcessor = new JwtProcessor();
        processorList.add(jwtProcessor);
        return new DefaultProcessorManager(processorList);
    }

    /**
     * @param databasePathTreeProvider the path tree resource load from database
     */
    @Bean
    @ConditionalOnMissingBean
    TreePathRoleMatcher pathRoleMatcher(PathTreeProvider databasePathTreeProvider) {
        // the path tree resource load form annotation - @RequiresRoles @WithoutAuth
        AnnotationPathTreeProvider annotationPathTreeProvider = new AnnotationPathTreeProvider();
        annotationPathTreeProvider.setScanPackages(Collections.singletonList("com.chinaunicom.store.web.controller"));
        // pathRoleMatcher init
        DefaultPathRoleMatcher pathRoleMatcher = new DefaultPathRoleMatcher();
        pathRoleMatcher.setPathTreeProviderList(Arrays.asList(
                annotationPathTreeProvider,
                databasePathTreeProvider));
        pathRoleMatcher.buildTree();
        return pathRoleMatcher;
    }

    @Bean
    @ConditionalOnMissingBean
    SubjectFactory subjectFactory() {
        // SubjectFactory init
        SubjectFactory subjectFactory = new SurenessSubjectFactory();
        subjectFactory.registerSubjectCreator(Arrays.asList(
                // attention! must add noSubjectCreator first
                new NoneSubjectServletCreator(),
                // use default jwt subject creator
                new JwtSubjectServletCreator()));
        return subjectFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    HandlerManager handlerManager(UserService userService) {
        // SubjectFactory init
        HandlerManager handlerManager = new HandlerManager();
        handlerManager.registerHandler(Arrays.asList(
                new JwtRefreshTokenHandler() {
                    @Override
                    public String getToken(String account, long expired) {
                        return userService.token(account, userService.permissions(account), expired);
                    }
                }));
        return handlerManager;
    }

    @Bean
    @ConditionalOnMissingBean
    SurenessSecurityManager securityManager(ProcessorManager processorManager,
                                            TreePathRoleMatcher pathRoleMatcher, SubjectFactory subjectFactory, HandlerManager handlerManager) {
        JsonWebTokenUtil.setDefaultSecretKey(TOM_SECRET_KEY);
        // surenessSecurityManager init
        SurenessSecurityManager securityManager = SurenessSecurityManager.getInstance();
        securityManager.setPathRoleMatcher(pathRoleMatcher);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setProcessorManager(processorManager);
        securityManager.setHandlerManager(handlerManager);
        return securityManager;
    }
}
