<%@ include file="/layout/init.jsp" %>

<%
String layoutId = StringPool.BLANK;

if (selLayout != null) {
	UnicodeProperties typeSettingsProperties = selLayout.getTypeSettingsProperties();

	layoutId = typeSettingsProperties.getProperty("layoutId", StringPool.BLANK);
}
%>

<aui:input cssClass="lfr-input-text-container" id="layoutId" label="layoutId" name="TypeSettingsProperties--layoutId--" type="text" value="<%= layoutId %>">
	<aui:validator errorMessage="please-enter-a-valid-layout-id" name="required" />
</aui:input>

<aui:script use="liferay-form">
	var form = Liferay.Form.get('<portlet:namespace />addPageFm');

	if (!form) {
		form = Liferay.Form.get('<portlet:namespace />editLayoutFm');
	}

	if (form) {
		var rules = form.formValidator.get('rules');

		var fieldName = '<portlet:namespace />TypeSettingsProperties--layoutId--';

		if (!(fieldName in rules)) {
			rules[fieldName] = {
				custom: false,
				required: true
			};
		}
	}
</aui:script>