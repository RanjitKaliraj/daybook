package com.daybook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daybook.dto.ProfileDto;
import com.daybook.service.UserProfile;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/editprofile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile profile = new UserProfile();
		ProfileDto profileDto = new ProfileDto();
		
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
				
		profileDto.setUser_Id(userId);
		profile.getUserProfile(profileDto);
		System.out.println("Servlet.editProfile: Redirecting to editing profile");
		request.setAttribute("profile", profileDto);
		System.out.println("Servlet.Signup::Dispatch to edit-profile.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] userSession = (String[])request.getSession().getAttribute("user");
		int userId = Integer.parseInt(userSession[0]);
		
		ProfileDto profileDto = new ProfileDto();
		UserProfile userProfile = new UserProfile();		
		
		String oldPassword = request.getParameter("oldpassword");//old password
		String newPassword = request.getParameter("newpassword");//new password
		
		profileDto.setUser_Id(userId);
		profileDto.setF_name(request.getParameter("fname"));
		profileDto.setL_name(request.getParameter("lname"));
		profileDto.setEmail(request.getParameter("email"));
		profileDto.setBank_name(request.getParameter("bankName"));
		profileDto.setAc_No(request.getParameter("acNo"));
		
		System.out.println("Servlet.editProfile: Checking old password is entered or not entered.");
		if (oldPassword=="" && newPassword!=""){
			System.out.println("Servlet.editProfile: old password is not entered.");
			String msg ="Please enter your old Password";
			request.setAttribute("password", msg);
			request.setAttribute("profile", profileDto);
			System.out.println("Servlet.Signup::Dispatch to edit-profile.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.jsp");
			dispatcher.forward(request, response);
		}	
		else if (oldPassword!="" && newPassword==""){
			System.out.println("Servlet.editProfile: New password is not entered.");
			String msg ="Please enter new Password";
			request.setAttribute("password", msg);
			request.setAttribute("profile", profileDto);
			System.out.println("Servlet.Signup::Dispatch to edit-profile.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.jsp");
			dispatcher.forward(request, response);
		}
		else if (oldPassword=="" && newPassword==""){
			System.out.println("Servlet.editProfile: Checking if both password are null. if so the profile will be updated");
			userProfile.updateUserProfile(profileDto);
			if (profileDto.isUserProfileUpdated()){
				System.out.println("Servlet.editProfile: profile successfully updated: with our password");
				String msg ="Profile is updated successfully.";
				request.setAttribute("msg", msg);
				System.out.println("Servlet.Signup::Dispatch to profile.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			}
		}		
		else{
			System.out.println("Servlet.editProfile: updating password and profile both.");
			profileDto.setPassword(oldPassword);
			System.out.println("Servlet.editProfile: checking old password.");
			userProfile.checkOldPassword(profileDto);
			if (profileDto.isPasswordMatched()){
				System.out.println("Servlet.editProfile: old password matched");
				profileDto.setPassword(newPassword);
				System.out.println("Servlet.editProfile: changing new password.");
				userProfile.changePassword(profileDto);
				System.out.println("Servlet.editProfile: updating profile.");
				userProfile.updateUserProfile(profileDto);
				
				if (profileDto.isPasswordUpdated() && profileDto.isUserProfileUpdated()){
					System.out.println("Servlet.editProfile: profile successfully updated including password");
					String msg ="Profile is updated successfully.";
					request.setAttribute("msg", msg);
					System.out.println("Servlet.Signup::Dispatch to profile.jsp");
					RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
					dispatcher.forward(request, response);
				}
				else{
					System.out.println("Something went wrong");
				}
			}
			else{
				System.out.println("Servlet.editProfile: old password do not matched.");
				String msg ="Old password do not matched.";
				request.setAttribute("password", msg);
				request.setAttribute("profile", profileDto);
				System.out.println("Servlet.editProfile::Dispatch to edit-profile.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.jsp");
				dispatcher.forward(request, response);
			}
		}
	}		
}
