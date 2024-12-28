# Spring Security
- Intro
- Security
- Custom Configuration
- CSRF (Cross-Site Request Forgery)
- User Registration and Login / Logout
- JWT (JSON Web Token)
- Implement JWT
- JWT for Microservices & App
- Login SSO (Single Sign-On)

********************************************************************

## How it works ?
- **sessionID** is generated on the login and stored in the browser
- with every new request the **sessionID** is validated
- when the logout button is clicked, the **sessionID** is invalidated and cleared from the browser cache

### Default Functionality
- username: user
- password: logged in the cmd
- default endpoint for logout: localhost:8080/logout

### How it works?
- **Dispatcher Servlet** (Front-Controller) : handles all the requests before sending them to the respective controllers
- **Filter Chain** : interceptors before the Dispatcher Servlet
	- one of those filter chain is security filter
	- it authenticates and authorizes that request

********************************************************************
