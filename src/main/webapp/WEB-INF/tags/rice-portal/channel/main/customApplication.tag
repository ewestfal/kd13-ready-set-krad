<%--
  ~ Copyright 2006-2012 The Kuali Foundation
  ~
  ~ Licensed under the Educational Community License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.opensource.org/licenses/ecl2.php
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%--
Kuali Rice ArcheType Help

This file contains custom application specific portal content.
--%>

<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp" %>

<channel:portalChannelTop channelTitle="Kuali Days 2013 - Ready, Set, KRAD!"/>
<div class="body">

  <ul class="chan">
  <%--
      <li><portal:portalLink displayTitle="true" title="Conference Session Lookup" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.kd2013.dataobject.ConferenceSession&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
      <li><portal:portalLink displayTitle="true" title="Presenter Lookup" 
      		url="${ConfigProperties.application.url}/kr-krad/lookup?methodToCall=start&dataObjectClassName=org.kuali.kd2013.dataobject.PresenterInfo&returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
   --%>
  </ul>

</div>
<channel:portalChannelBottom/>
