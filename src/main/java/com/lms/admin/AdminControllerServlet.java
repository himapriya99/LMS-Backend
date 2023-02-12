package com.lms.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.lms.models.Student;
import com.lms.models.Subject;
import com.lms.models.Teacher;

import java.util.List;

/**
 * Servlet implementation class AdminControllerServlet
 */
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbRetrieve dbRetrieve;
	
	@Override
	public void init() throws ServletException {

		super.init();

		try {
			dbRetrieve = new DbRetrieve();

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
				
		try {
					// read the "command" parameter
					String command = request.getParameter("command");
					System.out.println(command);

					switch (command) {

					case "STUDENTS":
						studentsList(request, response);
						break;

					case "TEACHERS":
						teachersList(request, response);
						break;

					case "SUBJECTS":
						subjectList(request, response);
						break;

					case "CLASSES":
						classestList(request, response);
						break;

					case "ST_LIST":
						classStudentsList(request, response);
						break;

					case "LOGIN":
						
							login(request, response);
						break;

					default:
						classestList(request, response); 
					}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {
			classestList(request, response);
		} else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.include(request, response);
			pw.print("Please Check Admin Credentials");
		}

	}
	private void classestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get subjects from db util
		
		List<com.lms.models.Class> classes = dbRetrieve.getClasses();

		// add subjects to the request
		request.setAttribute("CLASSES_LIST", classes);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);

	}
	private void studentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = dbRetrieve.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send it to the jsp view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}

	private void teachersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Teacher> teachers = dbRetrieve.getTeachers();

		// add students to the request
		request.setAttribute("TEACHERS_LIST", teachers);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers-list.jsp");
		dispatcher.forward(request, response);

	}

	private void subjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get subjects from db util
		List<Subject> subjects = dbRetrieve.getSubjects();

		// add subjects to the request
		request.setAttribute("SUBJECTS_LIST", subjects);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects-list.jsp");
		dispatcher.forward(request, response);

	}
	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int classId = Integer.parseInt(request.getParameter("classId"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");

		// get subjects from db util
		List<Student> students = dbRetrieve.loadClassStudents(classId);

		// add subjects to the request
		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/class-students.jsp");
		dispatcher.forward(request, response);

	}

}
