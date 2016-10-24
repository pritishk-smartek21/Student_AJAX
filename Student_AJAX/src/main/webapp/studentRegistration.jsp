<!-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration form</title>
<script>

function generateJSON(){
	alert("entered");
	var age = document.forms["myForm"]["age"].value;
	var rollNo = document.forms["myForm"]["rollNo"].value;
	var name = document.forms["myForm"]["name"].value;
	var dob = document.forms["myForm"]["dob"].value;
	var gender = document.forms["myForm"]["gender"].value;
	var course = document.forms["myForm"]["course"].value;
	var mobileNo = document.forms["myForm"]["mobileNo"].value;

	var stud = {
			"age" : parseInt(age),
			"rollNo" : parseInt(rollNo),
			"name" : name,
			"address" : "Pune,Dist:611117",
			"dob" : dob,
			"gender" : gender,
			"course" : course,
			"mobileNo" : parseInt(mobileNo)
	}
	
	
	
	var studJson = JSON.stringify(stud);
	alert("sns");
	var xhttp = new XMLHttpRequest();
	/* if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP"); */
	
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == XMLHttpRequest.DONE) {
				if (xhttp.status == 200) {
					alert('successfull send');
				} else if (xhttp.status == 400) {
					alert('There was an error 400');
				} else {
					var res=JSON.parse(xhttp.responseText);			
					alert('something else other than 200 was returned'+res.firstName);
				}
			}
		};

		xhttp.open("POST", "./studRegister",true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(studJson); 
		
		document.getElementById("myForm").reset();
		document.getElementById("myForm").style.display = "hidden";
		document.getElementById("heading").innerHTML = "Student successfully registered!";
		
		return false;
	}


</script>

</head>
<body>
	<h1 id="heading">Student Registration Form</h1>
	<div>
		<form action="/studRegister" method="post" id="myForm" onsubmit="return generateJSON()">
			<table>
				<tr>
					<td>Age:</td>
					<td><input type="text" name="age" /></td>
				</tr>
				<tr>
					<td>Roll No:</td>
					<td><input type="text" name="rollNo" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>DOB:</td>
					<td><input type="text" name="dob" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><input type="text" name="gender" /></td>
				</tr>
				<tr>
					<td>Course:</td>
					<td><input type="text" name="course" /></td>
				</tr>
				<tr>
					<td>Mobile No:</td>
					<td><input type="text" name="mobileNo" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Enter" /> 
						<!-- <button onclick="myFunc()">BUTTON</button> -->
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>