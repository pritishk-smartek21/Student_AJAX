<!-- %@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Page</title>
<!-- function singleStudentFunc() {
		alert("inside single student script");
		var string;
		var stud;
		var xhttp = new XMLHttpRequest();
		var roll = document.getElementById("rollNo").value;
		var url = "http://localhost:8182/stud/" + roll;
		alert(url);
		xhttp.open("GET", url, true);
		xhttp.setRequestHeader("Content-type", "application/JSON");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == XMLHttpRequest.DONE) {
				if (xhttp.status == 200) {
					stud = JSON.parse(xhttp.responseText);
					string = "<table><tr><td>Age</td><td>" + stud.age
							+ "</td></tr><tr><td>Roll No</td><td>"
							+ stud.rollNo + "</td></tr><tr><td>Name</td><td>"
							+ stud.name + "</td></tr><tr><td>Address</td><td>"
							+ stud.address + "</td></tr><tr><td>DoB</td><td>"
							+ stud.dob + "</td></tr><tr><td>Gender</td><td>"
							+ stud.gender + "</td></tr><tr><td>Course</td><td>"
							+ stud.course
							+ "</td></tr><tr><td>Mobile No</td><td>"
							+ stud.mobileNo + "</td></tr></table>";
					alert('successfull send' + xhttp.responseText);
					document.getElementById("result").innerHTML = string;
					alert("outside single student script: " + string);
				} else if (xhttp.status == 400) {
					alert('There was an error 400');
				} else {

					alert('something else other than 200 was returned'
							+ xhttp.responseText);
				}
			}
		}

		xhttp.send();

		return false;
	} -->
<script>
	function deleteStudent() {
		alert("inside delete student");
		var xhttp = new XMLHttpRequest();
		if(window.XMLHttpRequest){
			xhttp = new XMLHttpRequest();
		}else{
			xhttp
		}
		
		
		
		var emp;
		var roll = document.getElementById("rollNo").value;
		var url = "http://localhost:8182/stud/" + roll;
		xhttp.open("DELETE", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechnage = function() {
			if (xhttp.readyState == XMLHttpRequest.DONE) {
				if (xhttp.status == 200) {
					alert(xhttp.readyState + " " + xhttp.status + " "
							+ xhttp.responseText)
					emp = JSON.parse(xhttp.responseText);
					alert("deleted : "+emp);
					alert(emp + " : successfully send : " + xhttp.responseText);
				} else if (xhttp.status == 400) {
					alert("There was an error 400");
				} else {
					alert("Error other than normal: " + xhttp.responseText);
				}
			}
		}
		alert("outside : "+emp);
		alert("outside delete student");
		xhttp.send();
		document.getElementById("result").innerHTML = "Successfully Deleted";
		return false;
	}
</script>
</head>
<body>

	<div>
		<table>
			<tr>
				<td>Student ID</td>
				<td><input type="text" id="rollNo" name="rollNo" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" onclick="deleteStudent()"
					value="Remove" /></td>
			</tr>
		</table>
	</div>
	<h1 id="result"></h1>
</body>
</html>