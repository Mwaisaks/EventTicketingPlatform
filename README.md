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
* In docker environment- easiest way
* Add configurations in the docker-compose.yml file
* run docker-compose up.
* head over to localhost:9090, and log in
* Initial configurations - create a realm
* create a client (rep the FE)
* Client authentication set to off to prevent public access
* Create Users
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
* have an abstract User class with various sub-classes - polymorphism (more complicated)
option 2:
* have different roles for these three user types and have one User entity


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
* 

