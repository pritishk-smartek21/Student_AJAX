<!DOCTYPE html>

<!--%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%-->
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
	/* Home Function */
	function homePage() {
		var string = "A student information system (SIS), student management system, school administration software or student "
				+ "administration system is a management information system for education establishments to manage student data. Student"
				+ " information systems provide capabilities for registering students in courses, documenting grading, transcripts, results "
				+ "of student tests and other assessment scores, building student schedules, tracking student attendance, and managing many"
				+ " other student-related data needs in a school. A SIS should not be confused with a learning management system or virtual "
				+ "learning environment, where course materials, assignments and assessment tests can be published electronically."
				+ "Diagram showing the importance and result of well thought out Student Data Management."
				+ "The SIS can include features that can be considered as an enterprise resource planning or ERP system"
				+ " for a corporate customer. As such, many of the issues with ERP system selection methodology, implementation, "
				+ "and operation of an ERP system apply too.";
		document.getElementById("p1").innerHTML = string;
		document.getElementById("p1").style = "color:#2F4F4F";
	}

	/* Register Function */
	function registerPage() {
		var string = "<div id='myForm'><table><tr><td>Age</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type='text' id='age'/></td></tr>"
				+ "<tr><td>Roll No</td><td></td><td><input type='text' id='rollNo'/></td></tr>"
				+ "<tr><td>Name</td><td></td><td><input type='text' id='name'/></td></tr>"
				+ "<tr><td>Address</td><td></td><td><input type='text' id='address'/></td></tr>"
				+ "<tr><td>Date Of Birth</td><td></td><td><input type='text' id='dob' placeholder='yyyy-mm-dd'/></td></tr>"
				+ "<tr><td>Gender</td><td></td><td><input type='text' id='gender' placeholder='M or F'/></td></tr>"
				+ "<tr><td>Course</td><td></td><td><input type='text' id='course'/></td></tr>"
				+ "<tr><td>Mobile No</td><td></td><td><input type='text' id='mobileNo'/></td></tr>"
				+ "<tr><td colspan='3'><br/><br/><button onclick='registerFunc()'>Register</button></td></tr>"
				+ "</table></div><h1 id='p2'></h1>";
		document.getElementById("p1").innerHTML = string;
	}

	function registerFunc() {
		/* alert("script called"); */
		var xhttp = new XMLHttpRequest();
		var url = "http://192.168.50.226:8282/stud";
		xhttp.open("POST", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && status == 200) {
				/* alert("working properly"); */
			}
		}
		var stud = JSON.stringify({
			"age" : document.getElementById("age").value,
			"rollNo" : document.getElementById("rollNo").value,
			"name" : document.getElementById("name").value,
			"address" : document.getElementById("address").value,
			"dob" : document.getElementById("dob").value,
			"gender" : document.getElementById("gender").value,
			"course" : document.getElementById("course").value,
			"mobileNo" : document.getElementById("mobileNo").value
		})
		xhttp.send(stud);
		/* alert("datasent"); */
		document.getElementById("myForm").style.visibility = "hidden";
		/* document.getElementById("myForm").reset(); */
		document.getElementById("p2").innerHTML = "You can search all Registered students in Show All tab.";
		return false;
	}

	/* Upload Resume Function */
	function uploadPage() {
		var select = "<div><body><h2>File Upload Example</h2><form id='form2' method='post' action='upload' enctype='multipart/form-data'>"
				+ "Select a file : <input name='file' id='file' type='file' size='45'/><br/><input type='submit' value='Upload File' name='submit'/></form>"
				+ "<div id='result'></div></body></div><h1 id='p2'></h1>";
		document.getElementById("p1").innerHTML = select;
	}

	/* Download Function */
	function downloadPage() {
		/* alert("script started"); */
		var string="";
		var files = "";
		var select = "<div><body><h2>List of Files</h2></body></div><br/><h3 id='p2'></h3>";
		document.getElementById("p1").innerHTML = select;
		document.getElementById("p2").innerHTML = "No files available";
		var xhttp = new XMLHttpRequest();
		var url = "http://192.168.50.226:8282/downloadList";
		xhttp.open("GET", url, true);
		xhttp.setRequestHeader("Content-type", "application/json")
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				string = JSON.parse(xhttp.responseText);
				for (var i = 0; i < string.length; i++) {
					files =  files+"<table><tr><td><a onclick='downloadFunc()' id='"+string[i]+"'>"+string[i]+"</a></td></tr></table>";
					document.getElementById("p2").innerHTML = files;
				}
			}
		}
		
		xhttp.send();
		/* alert("script ended"); */
		return false;
	}
	
	function downloadFunc(){
		alert("script called");
		var xhttp=new XMLHttpRequest();
		var url = "http://192.168.50.226:8282/download/"+document.getElementById(string[i]).value;
		alert(url);
		alert("script ended");
	}
	

	/* Delete Function */
	function deletePage() {
		var select = "<div id='myDiv'><body><input type='text' name='rollNo' id='rollNo' required><br/><br/><button onclick='deleteFunc()'>Delete Entry</button></body></div><h1 id='p2'></h1>";
		document.getElementById("p1").innerHTML = select;
	}

	function deleteFunc() {
		/* alert("script called"); */
		var xhttp = new XMLHttpRequest();
		var roll = document.getElementById("rollNo").value;
		var url = "http://192.168.50.226:8282/stud/" + roll;
		xhttp.open("DELETE", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && status == 200) {
				/* alert("working properly"); */
			}
		}
		/* alert("outside delete student"); */
		xhttp.send();
		document.getElementById("p2").innerHTML = "ThankYou!";
		return false;
	}

	/* Search Function */
	function showSinglePage() {
		var select = "<div id='myDiv'><body><input type='text' name='rollNo' id='rollNo'><br/><br/><button onclick='showSingleFunc()'>Search</button></body></div><h3 id='p2'></h3>";
		document.getElementById("p1").innerHTML = select;
	}

	function showSingleFunc() {
		/* alert("script called"); */
		var stud;
		var string = "No such entry available";
		var xhttp = new XMLHttpRequest();
		var roll = document.getElementById("rollNo").value;
		var url = "http://192.168.50.226:8282/stud/" + roll;
		xhttp.open("GET", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				stud = JSON.parse(xhttp.responseText);
				/* alert("success single form :" + stud.name); */
				string = "<table width='80%'><tr><td>Age</td><td>" + stud.age
						+ "</td></tr>" + "<tr><td>Roll No</td><td>"
						+ stud.rollNo + "</td></tr>" + "<tr><td>Name</td><td>"
						+ stud.name + "</td></tr>" + "<tr><td>Address</td><td>"
						+ stud.address + "</td></tr>"
						+ "<tr><td>Date Of Birth</td><td>" + stud.dob
						+ "</td></tr>" + "<tr><td>Gender</td><td>"
						+ stud.gender + "</td></tr>"
						+ "<tr><td>Course</td><td>" + stud.course
						+ "</td></tr>" + "<tr><td>Mobile No</td><td>"
						+ stud.mobileNo + "</td></tr>"
						+ "</table></div><h1 id='p2'></h1>";
				document.getElementById("p2").innerHTML = string;
			}
		}
		xhttp.send();
		/* alert("script ended"); */
		document.getElementById("p2").innerHTML = string;
		return false;
	}

	/* Update Function */
	function updatePage() {
		var select = "<div id='myDiv'><body><input type='text' name='rollNo' id='rollNo'><br/><br/><button onclick='showUpdateFunc()'>Update Information</button></body></div><h1 id='p2'></h1>";
		document.getElementById("p1").innerHTML = select;
	}

	function showUpdateFunc() {
		/* alert("script called"); */
		var stud;
		var stuInf = "No such entry available";
		var url = "http://192.168.50.226:8282/stud/"
				+ document.getElementById("rollNo").value;
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				stud = JSON.parse(xhttp.responseText);
				/* alert("successfully working : " + stud.name);  */

			}
		};
		xhttp.open("GET", url, false);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send();

		stuInf = "<div id='myForm'><table width='80%'><tr><td>Age</td><td><input type='text' id='age' value='"+stud.age+"'/></td></tr>"
				+ "<tr><td>Roll No</td><td><input type='text' id='rollNo' value='"+stud.rollNo+"' disabled/></td></tr>"
				+ "<tr><td>Name</td><td><input type='text' id='name' value='"+stud.name+"'/></td></tr>"
				+ "<tr><td>Address</td><td><input type='text' id='address' value='"+stud.address+"'/></td></tr>"
				+ "<tr><td>Date Of Birth</td><td><input type='text' id='dob' value='"+stud.dob+"'/></td></tr>"
				+ "<tr><td>Gender</td><td><input type='text' id='gender' value='"+stud.gender+"'/></td></tr>"
				+ "<tr><td>Course</td><td><input type='text' id='course' value='"+stud.course+"'/></td></tr>"
				+ "<tr><td>Mobile No</td><td><input type='text' id='mobileNo' value='"+stud.mobileNo+"'/></td></tr>"
				+ "<tr><td colspan='2'><button onclick='updateFunc()'>Update</button></td></tr>"
				+ "</table></div>";
		/* alert("script ended"); */
		document.getElementById("p2").innerHTML = stuInf;
		return false;
	}

	function updateFunc() {
		/* alert("script called"); */
		var studUpdate;
		var url = "http://192.168.50.226:8282/stud/"
				+ document.getElementById("rollNo").value;
		var xhttp = new XMLHttpRequest();
		xhttp.open("PUT", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				/* alert("working properly"); */
			}
		}
		studUpdate = JSON.stringify({
			"age" : document.getElementById("age").value,
			"rollNo" : document.getElementById("rollNo").value,
			"name" : document.getElementById("name").value,
			"address" : document.getElementById("address").value,
			"dob" : document.getElementById("dob").value,
			"gender" : document.getElementById("gender").value,
			"course" : document.getElementById("course").value,
			"mobileNo" : document.getElementById("mobileNo").value
		})

		/* alert("data before update"); */
		xhttp.send(studUpdate);
		/* alert("data updated"); */
		document.getElementById("p2").innerHTML = "You can search all updated students data in Show All tab.";
		return false;
	}

	/* Show All Func */
	function showAllPage() {
		/* alert("script called"); */
		var stud;
		var i;
		var string = "";
		var xhttp = new XMLHttpRequest();
		var url = "http://192.168.50.226:8282/stud";
		xhttp.open("GET", url, true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {

				stud = JSON.parse(xhttp.responseText);
				/* alert("success : " + stud); */
				for (i = 0; i < stud.length; i++) {
					string = string
							+ "<table  width='100%'><tr><td>Age</td><td>"
							+ stud[i].age + "</td></tr>"
							+ "<tr><td>Roll No</td><td>" + stud[i].rollNo
							+ "</td></tr>" + "<tr><td>Name</td><td>"
							+ stud[i].name + "</td></tr>"
							+ "<tr><td>Address</td><td>" + stud[i].address
							+ "</td></tr>" + "<tr><td>Date Of Birth</td><td>"
							+ stud[i].dob + "</td></tr>"
							+ "<tr><td>Gender</td><td>" + stud[i].gender
							+ "</td></tr>" + "<tr><td>Course</td><td>"
							+ stud[i].course + "</td></tr>"
							+ "<tr><td>Mobile No</td><td>" + stud[i].mobileNo
							+ "</td></tr></table><hr/>";

				}
				document.getElementById("p1").innerHTML = string;
			}

		}
		xhttp.send();

		/* alert("script ended"); */
		return false;
	}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body onload="homePage()">
	<div class="container">
		<h1 style="text-align: center; color: #2F4F4F">Student Management
			System</h1>
		<ul class="nav nav-tabs">
			<li><a onclick="homePage()">Home</a></li>
			<li><a onclick="registerPage()">Registration</a></li>
			<li><a onclick="updatePage()">Update</a></li>
			<li><a onclick="deletePage()">Delete</a></li>
			<li><a onclick="showSinglePage()">Show By ID</a></li>
			<li><a onclick="showAllPage()">Show All</a></li>
			<li><a onclick="uploadPage()">Upload Resume</a></li>
			<li><a onclick="downloadPage()">Download</a></li>
		</ul>
		<br /> <br /> <br />
		<p id="p1"></p>
	</div>
	<a href="file:///e:\uploaded_contentss\desk.html" download>Download</a>
	<a href="file:///C:\Programs\sort.mw">Link 1</a>
	<!-- <table style="al">
		<tr>
			<td><a onclick="registerPage()">Registration</a></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><a onclick="updatePage()">Update</a></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><a onclick="deletePage()">Delete</a></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><a onclick="showSinglePage()">Show By ID</a></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><a onclick="showAllPage()">Show All</a></td>
			<td></td>
			<td></td>
		</tr>
	</table> -->


</body>
</html>