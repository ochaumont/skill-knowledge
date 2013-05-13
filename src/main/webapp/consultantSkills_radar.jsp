<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Index</title>
    
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/css/jquery.	.css" />
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/themes/smoothness/jquery-ui-1.8.21.css" />
    
    <script src="/skill-knowledge/jquery/js/jquery-1.5.1.min.js" type="text/javascript"></script>
    <script src="/skill-knowledge/jquery/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="/skill-knowledge/jquery/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="/skill-knowledge/jquery/js/excanvas.js"></script><![endif]-->
   
    <script language="javascript" type="text/javascript">
   
		<%
		    List<Map<String,String>> result = (List<Map<String,String>>)request.getAttribute("result");
		
	    
	        for(int i=0;i<result.size();i++) {   	
	        	Map<String,String> map = result.get(i);        	
	     %>
	     $(document).ready(function () {
	     $('#jqChart<%=i%>').jqChart({
			  title: { text: '' },
			
			  axes: [
			           {
			               type: 'categoryAngle',
			               categories: <%=(String)map.get("categories")%>,
			           },
			           {
			               type: 'linearRadius',
			               minimum : 0,
			               maximum : 5,
			               interval : 1
			           }
			          ],
			    series: [
			              
			               {
			                   type: 'radarArea',
			                   title: '',
			                   majorTickMarks: { visible: true },
			                   data:  <%=(String)map.get("data")%>
			               },
			               {
			                   type: 'radarArea',
			                   title: '',
			                   majorTickMarks: { visible: true },
			                   data:  <%=(String)map.get("data")%>
			               }
			           ]
		  });
		        	
	    
	        });
       
     
     <%
      }
      %>
   
    </script>
</head>
<body>
 <table>
 <tr>
<%
 int nbrRow = 0;
 int nbrRowMax = 2;
 for(int i=0;i<result.size();i++) {   	
	 nbrRow++;
     Map<String,String> map = result.get(i);  
     out.write("<td> <div id=\"jqChart"+i+"\" style=\"width: 500px; height: 300px;\"> </td>");
     if(nbrRow>=nbrRowMax) {
    	out.write("</tr><tr>");
    	nbrRow = 0;
     }

   }
 %>  
</tr>
 </table>
    
</body>

</html>