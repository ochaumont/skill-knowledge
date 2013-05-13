<%@page import="java.util.Map"%>
<%@page import="com.ochaumont.demo.skillknowledge.domain.Skill"%>
<%@page import="com.ochaumont.demo.skillknowledge.domain.SkillType"%>
<%@page import="java.util.List"%>
<%@page import="com.ochaumont.demo.skillknowledge.domain.Expertise"%>
<jsp:include page="header.jsp" />

<%
  List<Skill> skills  = (List<Skill>)request.getAttribute("skills");
  List<SkillType> skillTypes  = (List<SkillType>)request.getAttribute("skillTypes");
  SkillType selectedSkillType = (SkillType)request.getAttribute("selectedSkillType");
  Map<String,Integer> levelMap = (Map<String,Integer>)request.getAttribute("levelMap");
%>

<script language="javascript" type="text/javascript">
	<%
	if (selectedSkillType != null) {
	%>
		$(document).ready(function () {
		   $('#skillType').val('<%=selectedSkillType.getName()%>');
		});
	<%
	}
	%>
	
	 

 function callskilltype() {
	var skillType = document.getElementById("skillType");
	document.location.href = "/skill-knowledge/controller/editProfil?skillType="+skillType.value;	 
 }

</script>

 <select id="skillType" name="skillType" onchange="script:callskilltype()">
 <option value="default">Filtre...</option>"
  <%
   for(SkillType skillType : skillTypes) {	 
	   out.write("<option value=\""+skillType.getName()+"\">"+skillType.getLabel()+"</option>");	   
   }
  %>  	  
</select> 

<form action="/skill-knowledge/controller/updateProfil" method="POST">
   <%
   for(Skill skill : skills) {
	
	      %>
	<div  class="cadre"> 
	    	<div class="title"><%=skill.getLabel()%></div>	
	   	 	<p><%=skill.getDescription()%></p>
	   	 		   	 	   	 		   	 	
	  	    <select name="level-<%=skill.getName()%>" >	  	     
	  	      <%
	  	       
	  	       String opt = "";
	  	       for(int i=0;i<=5;i++) {
	  	    	   Integer o = levelMap.get(skill.getName());
	  	    	  
	  	    	   if(o != null && o.intValue() == i) {
	  	    		   opt = "selected=\"selected\"";
	  	    	   } else {
	  	    		   opt = "";
	  	    	   }
	  	    	   out.write("<option value=\""+i+"\" "+opt+">"+i+"</option>");
	  	       }
	  	      
	  	      %>	  	      
			</select> 
			
	</div>
   
   <%
   }
   %>
	
	<input type="submit">
	
</form>
	
<jsp:include page="footer.jsp" />	
	

