package com.lawencon.pss_app.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.DeadlineTypeDao;
import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.calendar.CalendarReqDto;
import com.lawencon.pss_app.dto.calendar.CalendarResDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleReqDto;
import com.lawencon.pss_app.dto.ps.CreateScheduleResDto;
import com.lawencon.pss_app.model.ClientAssignment;
import com.lawencon.pss_app.model.Date;
import com.lawencon.pss_app.model.Schedule;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {
	private ClientAssignmentDao clientAssignmentDAO;
	private ScheduleDao scheduleDAO;
	private DateDao dateDAO;
	private UserDao userDAO;
	private DeadlineTypeDao deadlineTypeDAO;
	
	public CalendarServiceImpl(ClientAssignmentDao clientAssignmentDAO, 
			ScheduleDao scheduleDAO, 
			DateDao dateDAO, 
			UserDao userDAO,
			DeadlineTypeDao deadlineTypeDAO
			) {
    	this.clientAssignmentDAO = clientAssignmentDAO;
    	this.scheduleDAO = scheduleDAO;
    	this.dateDAO = dateDAO;
    	this.userDAO = userDAO;
    	this.deadlineTypeDAO = deadlineTypeDAO;
    }
	
	@Override
	public CalendarResDto getAllDeadlines(CalendarReqDto reqDto) {
        List<LocalDate> deadlines = new ArrayList<>();
        List<String> deadlineTypes = new ArrayList<>();
        
		User user = userDAO.findByEmail(reqDto.getEmail());
		ClientAssignment clientAssignment = clientAssignmentDAO.findByClient(user);	
        List<Schedule> schedule = scheduleDAO.findByClientAssignment(clientAssignment);
        for (Schedule eachschedule : schedule) {
            List<Date> dates = dateDAO.findBySchedule(eachschedule);
            
            for(Date date : dates) {
                deadlines.add(date.getDeadlineDate());
                
                deadlineTypes.add(date.getDeadlineType().getDeadlineName());
            }
        }
        
    	var calendarRes = new CalendarResDto();
    	calendarRes.setDeadline(deadlines);
    	calendarRes.setDeadlineType(deadlineTypes);
		return calendarRes;
		
	}
	
	@Override
	public CreateScheduleResDto createScheduleAndDates(CreateScheduleReqDto createScheduleReqDto) {
		String email = createScheduleReqDto.getUserEmail();
		
		String monthYear = createScheduleReqDto.getMonthYear();
		System.out.println(monthYear);

	    User user = userDAO.findByEmail(email);
	    if (user == null) {
	        throw new IllegalArgumentException("No user found with the email: " + email);
	    }

	    ClientAssignment clientAssignment = clientAssignmentDAO.findByClient(user);
	    if (clientAssignment == null) {
	        throw new RuntimeException("No client assignment found for user with email: " + email);
	    }

	    Schedule schedule = new Schedule();
	    schedule.setMonthYear(monthYear);
	    schedule.setClientAssignment(clientAssignment);
	    schedule = scheduleDAO.create(schedule); 
	    
	    LocalDate date = createScheduleReqDto.getSendDataDate();
        Date newDate = new Date();
        newDate.setSchedule(schedule);
        newDate.setDeadlineDate(date);
        newDate.setDeadlineType(deadlineTypeDAO.findById(1)); 
        dateDAO.create(newDate);
        
        date = createScheduleReqDto.getPayrollReportDate();
        newDate = new Date();
        newDate.setSchedule(schedule);
        newDate.setDeadlineDate(date);
        newDate.setDeadlineType(deadlineTypeDAO.findById(2)); 
        dateDAO.create(newDate);

	    date = createScheduleReqDto.getBankUploadDate();
        newDate = new Date();
        newDate.setSchedule(schedule);
        newDate.setDeadlineDate(date);
        newDate.setDeadlineType(deadlineTypeDAO.findById(3)); 
        dateDAO.create(newDate);
        
        date = createScheduleReqDto.getPayrollJournalDate();
        newDate = new Date();
        newDate.setSchedule(schedule);
        newDate.setDeadlineDate(date);
        newDate.setDeadlineType(deadlineTypeDAO.findById(4)); 
        dateDAO.create(newDate);
        
        
        date = createScheduleReqDto.getPaidOutDate();
        newDate = new Date();
        newDate.setSchedule(schedule);
        newDate.setDeadlineDate(date);
        newDate.setDeadlineType(deadlineTypeDAO.findById(5)); 
        dateDAO.create(newDate);
       
	    
	    return new CreateScheduleResDto(
//	    		schedule.getId()
	    		null
	    		,"User created successfully!");
	}

}
