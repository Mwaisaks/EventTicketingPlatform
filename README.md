## Part One

### Project Brief
Brief description

**User Story Analysis**

 As an - I want - So that

Acceptance Criteria

**Definitions**
Event, Ticket, QR Code

#### User Personas Summary
**Identify the different user types**

Organizers, Attendees, Staff. What their primary goal is, their main challenge and what they value most.

**User Journey** - for the diff users

**Designing the UI**

* Interface for all users; landing page, logging in page
* Each user has a different landing page after logging in

### Domain Modelling

**Required**
* Domains
* cardinality info
* data types
* optional and required info

### TODO
Try creating UI design using AI

### UI/UX with AI

Tool - UX Pilot, ChatGPT

1. **Creating the Project Brief** - ChatGPT
I’m starting a [product type] called [Product Name], for [target users]. 
The goal is to [main objective]. 
Users can [features]. convert it to a concise project brief statement.

2. **SiteMap**
based on the project brief, create a sitemap in plain text, showing all primary pages, their hierarchy, and how they are interconnected.

3. **Layout and Content**
Create a static image of our homepage layout, that covers all the requirements
- Ask for any changes you may want, these images are aesthetically pleasing not real designs
- real world designs, industry standards and best practices while staying up to date with the latest trends
- Get an inspiration and show it to chatGPT

4. **Requirements**
give me a description of the ui structure for this home page, in plain text, from top to bottom. 
include only layout details and content blocks. don’t mention any styling or visual properties.

- Do the same for the rest of the pages. Create a description and then the image for it

5. **Design System**

UX Pilot - Create a new design file

i. Enter your visual requirements in plain text

ii. Use ChatGPT
Create an image with UI components for a [project], describe the style e.g. dark theme, transparency, bold colours, thin lines, modern UI, rounded corners

Give me a description of this style in plain text, in order to replicate the styling for a design system.

Enter the description in the context box, specify that you want a design system. save the prompts in your project doc

Go through the flow, to vies the pages. 

If it doesn't give you a design system > in chatgpt; Give me the pages and their content needed for a design system in plain text.

Copy each page description to the flow of UX pilot

Edit any changes you need, save it to figma.

Open a new figma deisgn file, open UX pilot plugin, add it to your design canvas

https://www.youtube.com/watch?v=Rl5pZ7Cncyk


## Part 2: Rest API Design

- Design REST API endpoints for the Event Organiser.
- Design REST API endpoints for the Event Staff.
- Design REST API endpoints for the Event Attendees.

## Part 3: Setting Up
static and template directories under the resource folder have been deleted.
We don't need them, used when doing server side rendering of our html pages

.mvn folder, mvnw(shellscript) and mvnw.cmd make up the maven wrapper which enable us to run maven without installing it.

### Running KeyCloak
* In docker environment- easiest way for local development
* Add configurations in the docker-compose.yml file
* run docker-compose up.
* head over to localhost:9090, and log in
* Initial configurations - create a realm 9name it your app's name
* create a client (rep the FE)
* Client authentication set to off to prevent public access
* Create Users, set the passowrd in the credentials tab
* Configure Springboot to keycloak; in the application.properties file

Is this what's used when you're in a system where you don't sign up, just log in with a preset password and then reset it?

### Configuring MapStruct
In your pom.xml file specify the versions for mapstruct and lombok in teh properties section
mapstruct version 1.6.3 goes well with lombok version 1.18.36
in the maven compiler plugin add the three plugins in the order of lombok, processor and lombok-Mapstruct binding.


## Part 4
* Create Enums
* For the User Entity
option 1:
* have an abstract User class with various subclasses - polymorphism (more complicated)
option 2:
* have different roles for these three user types and have one User entity

## Part 6: Create Event
CreateEventRequest.java is to be used in the service layer, the CreateEventRequestDto.java - repository layer.

Changes in either of the classes shouldn't affect the other one, we want them decoupled.

In the same project, I'm implementing the Create Event endpoint, I've createsd Dtos for the presentation layer

Below is the code for my CreateEventRequestDto class and my Event class. We've added validations in the dto and we still have 'validations' in the event ticket class, why's that? What's the difference between @NotNull, @NotEmpty and @Not Blank?

**Using Mapstruct Guide**

* Add Maven Dependencies
* Have your classes
* Create your mapper interface under mapper package
-Annotate the class with @Mapper to mark it as a mapping interface


### Part 7

**Pagination**
return a Page instead of a list

#### Consistency is key in any codebase


### Advanced Features
* Integrate a payment System
* Deploy on azure or AWS
* Emails
* Chatbot
* Sign up page
* Venue as it's own entity?
* Start and end date to be repetitive, open at __ and close at __
* Adjust docker-compose.yml and application.properties to prod standards
* Soft Delete functionality; User deleted, events remain..
* CSRF implementation
* Make sure the end date is after the first date
* Allow the user to specify other users as organisers
* Add other exceptions
* When the Organiser can be an attendee, staff for other events and vice versa
* implementing QR codes in real world scenarios
* Payment with Mpesa DARAJA API

### Part 9
**Implementing QR Codes**
* removed id autogeneration
* value made to be text instead of the default one varchar
* added 2 Crossing dependencies; Google Zxing core and javase
* Create a bean; QrCodeConfig class with bean QrCodeWriter
* Inject the bean in QrCodeServiceImpl


### Part 10
**Role Based Access**
* Extracted the roles from jwts 
Create a custom JWT converter in your config package; converts jwt to jwt authentication token
* use the converter in your SecurityConfig.java file, inject it in the arguments and replace the `Customizer.withDefaults`

**Lock Down Endpoints**
Use requestMatchers...hasRole in SecurityConfig.java class
When do we use hasAuthority?

**Implementing an Endpoint**
* Implement the service layer
* Create DTOs and mappers
* controller layer
* test functionality

