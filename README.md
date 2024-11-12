Suggestions for Next Steps
Service Layer: Implement service classes to handle business logic. 
For example, create a UserService class to manage user-related operations.

Repository Layer: Create repository interfaces for your entities to interact with the database. 
For instance, a UserRepository interface extending JpaRepository for CRUD operations on User.

Controller Layer: Develop REST controllers to expose endpoints for your application’s functionality. 
For instance, a UserController to manage user-related API endpoints.

Security: Implement authentication and authorization to secure your application, possibly using Spring Security.

Data Transfer Objects (DTOs): Use DTOs to transfer data between the client and the server, 
ensuring encapsulation and reducing the risk of exposing sensitive data.

Since you're planning to build a web version using HTML, CSS, and JavaScript along with Cordova, consider developing a responsive front-end that integrates seamlessly with your backend APIs. 
Tools like Bootstrap or Materialize can help you create a user-friendly interface.