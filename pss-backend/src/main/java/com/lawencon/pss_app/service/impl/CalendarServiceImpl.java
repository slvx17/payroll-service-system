package com.lawencon.pss_app.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.pss_app.dao.ClientAssignmentDao;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.ScheduleDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto_calendar.CalendarReqDto;
import com.lawencon.pss_app.dto_calendar.CalendarResDto;
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
	
	public CalendarServiceImpl(ClientAssignmentDao clientAssignmentDAO, 
			ScheduleDao scheduleDAO, 
			DateDao dateDAO, 
			UserDao userDAO) {
    	this.clientAssignmentDAO = clientAssignmentDAO;
    	this.scheduleDAO = scheduleDAO;
    	this.dateDAO = dateDAO;
    	this.userDAO = userDAO;
    }
	
	@Override
	public CalendarResDto getAllDeadlines(CalendarReqDto reqDto) {
        List<LocalDate> deadlines = new ArrayList<>();
        List<String> deadlineTypes = new ArrayList<>();
        
		User user = userDAO.findByEmail(reqDto.getEmail());
		Long id = user.getId();
		ClientAssignment clientAssignment = clientAssignmentDAO.getByClient(user);	
        Schedule schedule = scheduleDAO.getByClientAssignment(clientAssignment);
        List<Date> dates = dateDAO.getBySchedule(schedule);
        
        for(Date date : dates) {
            deadlines.add(date.getDeadlineDate());
            
            deadlineTypes.add(date.getDeadlineType().getDeadlineName());
        }
        
    	var calendarRes = new CalendarResDto();
    	calendarRes.setDeadline(deadlines);
    	calendarRes.setDeadlineType(deadlineTypes);
		return calendarRes;
		
	}
}
