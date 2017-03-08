package de.eventon.ui;

import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.eventon.core.Event;
import de.eventon.services.ActiveUserService;
import de.eventon.services.EventService;

@ManagedBean
@RequestScoped
public class EventViewForm {

	private Event event;
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	public EventViewForm() {
		
	}

	@PostConstruct
	private void init(){
		Map<String, String> rqParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String id = rqParameter.get("id");

		if (id != null) {
			try {
				int idAsInteger = Integer.parseInt(id);
				Optional<Event> optEvent = eventService.getEventById(idAsInteger);
				if(optEvent.isPresent())
				{
					event = optEvent.get();
				}
			} catch (NumberFormatException e) {
				event = null;
			}
		} else {
			event = null;
		}
	}
	
	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
