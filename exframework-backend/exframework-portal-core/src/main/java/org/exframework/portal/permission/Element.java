package org.exframework.portal.permission;

import org.exframework.portal.metadata.Meta;
import org.exframework.support.common.constants.CommonConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author rwe
 * @since 2021年6月30日
 *
 * 
 */
@Component
@Scope(CommonConstants.SCOPE_PROTOTYPE)
public class Element extends Meta implements PermissionEntry {

	private static final long serialVersionUID = 6584508633608203952L;

}
