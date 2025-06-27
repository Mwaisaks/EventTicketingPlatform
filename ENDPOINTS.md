## Organiser Flow

**Create Event**
POST /api/v1/events
Request Body: Event

**List Events**
GET /api/v1/events

**Retrieve Events**
GET /api/v1/events/{event_id}

**Update Event**
PUT /api/v1/events/{event_id}
RequestBody: Event

**Delete Event**
DELETE /api/v1/events/{event_id}

**Validate Ticket**
POST /api/v1/events/{event_id}/ticket-validations

**List Ticket Validations**
GET /api/v1/events/{event_id}/ticket-validations

**List Ticket Sales**
GET /api/v1/events/{event_id}/tickets

**Retrieve Ticket Sale**
GET /api/v1/events/{event_id}/tickets/{ticket_id}

**Partial Update ticket**
PATCH /api/v1/events/{event_id}/tickets
Request Body: Partial Event  

**List Ticket Type**
GET /api/v1/events/{event_id}/ticket-types

**Retrieve Ticket Type**
GET /api/v1/events/{event_id}/ticket-types/{ticket-type_id}

**Delete Ticket Type** - add validation
DELETE /api/v1/events/{event_id}/ticket-types/{ticket-type_id}

**Partial update Ticket Type**
PATCH /api/v1/events/{event_id}/ticket-types/{ticket-type_id}
RequestBody: Partial Ticket 

## Attendee Flow

**Get Events**
 
**Search published Events**
GET api/v1/published-events

**Retrieve Published Event**
GET api/v1/published-events

**Purchase Ticket**
POST /api/v1/published-events/{published_event_id}/ticket-types/{ticket_types_id}
 - should be in the 3rd part payment 

**List Tickets**
GEt api'/v1/tickets

**Retrieve Ticket**
GET /api/v1/tickets/{ticket_id}

**Retrieve Ticket**
GET /api/v1/tickets/{ticket_id}/qr-codes

- If you have a cdn you don't need this endpoint

## Staff Flow


### TODO: dedicated endpoint for report data