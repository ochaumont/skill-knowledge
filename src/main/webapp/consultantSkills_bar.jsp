
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp" />
<%
   String skillFilter = (String)request.getParameter("skillFilter");
%>
    <script language="javascript" type="text/javascript">
  
    
	   function callskillfilter() {
		   	var skillFilter = document.getElementById("skillFilter");
		   	document.location.href = "/skill-knowledge/controller/consultantSkills?skillFilter="+skillFilter.value;	 
	    }
    
    
	    <%
		if (skillFilter != null) {
		%>
			$(document).ready(function () {
			   $('#skillFilter').val('<%=skillFilter%>');
			});
		<%
		}
		%>
    
		<%
		    List<Map<String,Object>> result = (List<Map<String,Object>>)request.getAttribute("result");
		
	    
	        for(int i=0;i<result.size();i++) {   	
	        	Map<String,Object> map = result.get(i);        	
	     %>
	     $(document).ready(function () {
	    	 
	    	 
	    	 $('#jqChart<%=i%>').jqChart({
	    	      title: { text: 'Bar Chart' },
	    	      axes: [
	    	              {
	    	                  type: 'category',
	    	                  location: 'left',
	    	                  categories: <%=(String)map.get("categories")%>
	    	              }
	    	            ],
	    	      series: [
	    	                  {
	    	                      type: 'bar',
	    	                      title: 'Bar',
	    	                      data:  <%=(String)map.get("data")%>
	    	                  },
	    	                  {
	    	                      type: 'bar',
	    	                      title: 'Bar',
	    	                      data:  <%=(String)map.get("data")%>
	    	                  }
	    	              ]
	    	  });
	    	 	    
	    s	    
	        });
       
     
     <%
      }
      %>
   
    </script>
 
   <select id="skillFilter" name="skillFilter" onchange="script:callskillfilter()">
	 <option value="all">All</option>
	 <option value="bytype">By Type</option>
	 <option value="byexpertise">By Expertise</option>	    
	</select>
 
 
		 <table>
			<tr>
				<%
				 int nbrRow = 0;
				 int nbrRowMax = 2;
				 for(int i=0;i<result.size();i++) {   	
					 nbrRow++;
				     Map<String,Object> map = result.get(i);  
				     out.write("<td> "+map.get("label")+"<div id=\"jqChart"+i+"\" style=\"width: 910px; height: "+map.get("size")+"px;\"/> </td>");
				    // if(nbrRow>=nbrRowMax) {
				    	out.write("</tr><tr>");
				    	nbrRow = 0;
				    // }
				
				   }
				 %>  
			</tr>
		 </table>    
		 
	
		 
	<jsp:include page="footer.jsp" />