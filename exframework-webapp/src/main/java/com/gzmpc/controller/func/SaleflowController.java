package com.gzmpc.controller.func;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzmpc.controller.BaseController;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.toolbar.ToolButton;
import com.gzmpc.ui.ToolbarService;

@Controller
@RequestMapping("/func/saleflow")
public class SaleflowController extends BaseController {
	private Log log = LogFactory.getLog(SaleflowController.class.getName());

	
	@RequestMapping("/main.view")
	public String main(final HttpServletRequest request, final ModelMap model) throws NotAuthorizedException {
		setCommonAttribute(request,model);
//		Account account = (Account)model.get("account");
//		try {
//			ToolButton[] tbs = toolbarService.get("test-toolbar", account);
//			model.addAttribute("tbs", tbs);
//		} catch (NotFoundException e) {
//			log.error(e.getMessage(),e);
//		}
		
	    return "/func/saleflow/main";
	}
}
