package com.chberndt.liferay.layout.type.controller.reference;

import com.liferay.layout.type.controller.BaseLayoutTypeControllerImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.servlet.PipingServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Christian Berndt
 */
@Component(
	immediate = true, property = "layout.type=" + "reference",
	service = LayoutTypeController.class
)
@SuppressWarnings("serial")
public class ReferenceLayoutTypeController extends BaseLayoutTypeControllerImpl {

	@Override
	public String getURL() {
		return _URL;
	}
	
	@Override
	public String includeEditContent(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Layout layout)
		throws Exception {

		httpServletRequest.setAttribute(WebKeys.SEL_LAYOUT, layout);

		return super.includeEditContent(
			httpServletRequest, httpServletResponse, layout);
	}

	@Override
	public boolean isFirstPageable() {
		return false;
	}

	@Override
	public boolean isParentable() {
		return true;
	}

	@Override
	public boolean isSitemapable() {
		return false;
	}

	@Override
	public boolean isURLFriendliable() {
		return false;
	}

	@Override
	public boolean isWorkflowEnabled() {
		return false;
	}

	@Override
	protected ServletResponse createServletResponse(
		HttpServletResponse httpServletResponse,
		UnsyncStringWriter unsyncStringWriter) {

		return new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);
	}

	@Override
	protected ServletResponse createServletResponse(HttpServletResponse httpServletResponse,
			com.liferay.petra.io.unsync.UnsyncStringWriter unsyncStringWriter) {
		return new PipingServletResponse(httpServletResponse, unsyncStringWriter);
	}

	@Override
	protected String getEditPage() {
		return _EDIT_PAGE;
	}

	@Override
	protected String getViewPage() {
		return StringPool.BLANK;
	} 

	@Reference(
		target = "(osgi.web.symbolicname=com.chberndt.liferay.layout.type.controller.reference)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}	

	private static final String _EDIT_PAGE = "/layout/edit/reference.jsp";

	// TODO: Retrieve the reference page's friendly url
	private static final String _URL = "http://localhost:8080";

}