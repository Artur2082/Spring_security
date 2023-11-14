<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
<h3>Information of all employees</h3>
<br>
<br>
<security:authorize access="hasAnyRole('HR')">
<input type="button" value="Salary"
onclick="window.location.href = 'hr_info'">
only for HR staff
</security:authorize>
<br>
<br>
<security:authorize access="hasAnyRole('MANAGER')">
<input type="button" value="Performance"
       onclick="window.location.href = 'manager_info'">
only for Managers
</security:authorize>
<br>
<br>
</body>
</html>