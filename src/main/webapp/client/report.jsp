<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
 <head>
    <title>User panel</title>
 </head>
<body>
 <h1>User panel</h1>
 <h2>Client report</h2>
 <div style="width: 300px; display: flex;margin-top: 20px">
 <table >
  <colgroup>
         <col span="8" style="background:#DCDCDC">
     </colgroup>
             <tr>
                <th>REVENUE</th>
                <th>STAF</th>
                <th>COST_PRICE</th>
                <th>ASSETS</th>
                <th>RESERVES</th>
                <th>PROFIT</th>
                <th>LOANS</th>
                <th>DATE</th>
            </tr>
     <c:forEach items="${finances}" var="client">
            <tr>
                <td>${client.revenue}</td>
                <td>${client.staf}</td>
                <td>${client.costPrice}</td>
                <td>${client.assets}</td>
                <td>${client.reserves}</td>
                <td>${client.profit}</td>
                <td>${client.loans}</td>
                 <td>${client.date}</td>
            </tr>
     </c:forEach>
     </table>
 </div>
  <div style="width: 300px; display: flex;margin-top: 20px">
  <table >
   <colgroup>
          <col span="4" style="background:#DCDCDC">
      </colgroup>
              <tr>
                 <th>PROFITABILITY_SALE</th>
                 <th>INVENTORY_SALE</th>
                 <th>QUICK_LIQUIDITY</th>
                 <th>DATE</th>
             </tr>
      <c:forEach items="${report}" var="report">
             <tr>
                 <td>${report.profitabilitySale}</td>
                 <td>${report.inventoryTurnover}</td>
                 <td>${report.quickLiquidity}</td>
                 <td>${report.date}</td>
             </tr>
      </c:forEach>
      </table>
  </div>
</body>
</html>