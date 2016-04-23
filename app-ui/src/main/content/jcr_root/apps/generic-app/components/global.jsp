<%@include file="/libs/foundation/global.jsp"%>
<%@page import="com.day.cq.wcm.api.WCMMode"%>

<cq:defineObjects />

<c:set var="isEditMode" value="<%=WCMMode.fromRequest(slingRequest).equals(WCMMode.EDIT) %>" scope="request" />
<c:set var="isDesignMode" value="<%=WCMMode.fromRequest(slingRequest).equals(WCMMode.DESIGN) %>" scope="request" />