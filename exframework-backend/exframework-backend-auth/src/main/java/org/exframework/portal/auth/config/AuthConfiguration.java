package org.exframework.portal.auth.config;

import com.usthe.sureness.handler.HandlerManager;
import com.usthe.sureness.matcher.DefaultPathRoleMatcher;
import com.usthe.sureness.matcher.PathTreeProvider;
import com.usthe.sureness.matcher.TreePathRoleMatcher;
import com.usthe.sureness.mgt.SecurityManager;
import com.usthe.sureness.mgt.SurenessSecurityManager;
import com.usthe.sureness.processor.DefaultProcessorManager;
import com.usthe.sureness.processor.Processor;
import com.usthe.sureness.processor.ProcessorManager;
import com.usthe.sureness.processor.support.JwtProcessor;
import com.usthe.sureness.processor.support.NoneProcessor;
import com.usthe.sureness.provider.SurenessAccountProvider;
import com.usthe.sureness.provider.annotation.AnnotationPathTreeProvider;
import com.usthe.sureness.subject.SubjectCreate;
import com.usthe.sureness.subject.SubjectFactory;
import com.usthe.sureness.subject.SurenessSubjectFactory;
import com.usthe.sureness.subject.creater.JwtSubjectServletCreator;
import com.usthe.sureness.subject.creater.JwtSubjectSpringReactiveCreator;
import com.usthe.sureness.subject.creater.NoneSubjectServletCreator;
import com.usthe.sureness.subject.creater.NoneSubjectSpringReactiveCreator;
import com.usthe.sureness.util.JsonWebTokenUtil;
import com.usthe.sureness.util.SurenessCommonUtil;
import org.exframework.portal.auth.filter.SurenessMvcFilter;
import org.exframework.portal.auth.filter.SurenessWebfluxFilter;
import org.exframework.portal.auth.service.UserService;
import org.exframework.portal.auth.service.impl.DocumentUserService;
import org.exframework.portal.auth.sureness.handler.JwtRefreshTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.*;

/**
 * @author rwe
 * @date 2022/7/11 16:18
 **/
@Configuration
@ConditionalOnProperty(prefix = AuthConfiguration.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = AuthProperties.class)
public class AuthConfiguration {

    final static String PREFIX = "exframework.auth";

    @Autowired
    private AuthProperties properties;

    @Bean
    @ConditionalOnMissingBean(UserService.class)
    UserService userService() {
        return new DocumentUserService(properties.getAccounts());
    }

    @Bean
    @ConditionalOnMissingBean(PathTreeProvider.class)
    PathTreeProvider pathTreeProvider() {
        return new PathTreeProvider() {
            @Override
            public Set<String> providePathData() {
                return properties.getResourceRole() == null ? Collections.emptySet() : new HashSet<>(properties.getResourceRole());
            }

            @Override
            public Set<String> provideExcludedResource() {
                List<String> excludedResource = properties.getExcludedResource();
                Set<String> resourceSet;
                if (excludedResource != null) {
                    resourceSet = new HashSet<>(excludedResource);
                } else {
                    resourceSet = new HashSet<>();
                }
                try {
                    File resource = new ClassPathResource("static").getFile();
                    for (File file : resource.listFiles()) {
                        resourceSet.add("/" + file.getName() + (file.isDirectory() ? "/**" : "") + "===get");
                    }
                } catch (Exception e) {

                }

                return SurenessCommonUtil.attachContextPath(getContextPath(), resourceSet);
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
    @ConditionalOnMissingBean(SecurityManager.class)
    SecurityManager securityManager(
            ProcessorManager processorManager,
            TreePathRoleMatcher pathRoleMatcher,
            SubjectFactory subjectFactory) {
        if (properties.getJwt() != null) {
            String jwtSecret = properties.getJwt().getSecret();
            if (jwtSecret != null && !"".equals(jwtSecret)) {
                JsonWebTokenUtil.setDefaultSecretKey(jwtSecret);
            }
        }
        SurenessSecurityManager securityManager = SurenessSecurityManager.getInstance();
        securityManager.setPathRoleMatcher(pathRoleMatcher);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setProcessorManager(processorManager);
        return securityManager;
    }

    @Bean
    @ConditionalOnMissingBean(ProcessorManager.class)
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
    @ConditionalOnMissingBean(TreePathRoleMatcher.class)
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
    @ConditionalOnMissingBean(SubjectFactory.class)
    SubjectFactory subjectFactory() {
        SubjectFactory subjectFactory = new SurenessSubjectFactory();
        List<SubjectCreate> subjectCreates = new ArrayList<>();
        AuthProperties.ContainerType containerType = properties.getContainer();
        if (containerType == null) {
            containerType = AuthProperties.ContainerType.MVC;
        }
        switch (containerType) {
            case MVC:
                subjectCreates.add(new NoneSubjectServletCreator());
                subjectCreates.add(new JwtSubjectServletCreator());
                break;
            case WEBFLUX:
                subjectCreates.add(new NoneSubjectSpringReactiveCreator());
                subjectCreates.add(new JwtSubjectSpringReactiveCreator());
            default:
                break;
        }
        subjectFactory.registerSubjectCreator(subjectCreates);
        return subjectFactory;
    }

    @Bean
    @ConditionalOnMissingBean(HandlerManager.class)
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
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnExpression("'${exframework.auth.container:mvc}'.equalsIgnoreCase('mvc')")
    public SurenessMvcFilter surenessMvcFilter(SecurityManager securityManager) {
        return new SurenessMvcFilter(securityManager);
    }

    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @ConditionalOnExpression("'${exframework.auth.container:webflux}'.equalsIgnoreCase('webflux')")
    public SurenessWebfluxFilter surenessWebfluxFilter(SecurityManager securityManager) {
        return new SurenessWebfluxFilter(securityManager);
    }
}
