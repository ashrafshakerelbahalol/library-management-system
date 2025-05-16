Library Management SystemOverviewThis project is a Library Management System built using Spring Boot, Spring Data JPA,
Spring Security, Lombok, MapStruct, Flyway, and MySQL. It provides a robust and efficient way to manage library
resources, members, and borrowing transactions.FeaturesUser Management:Admin role can manage users.User authentication
and authorization with Spring Security.Book Management:Librarian role can add, update, delete, and view books.Book
details include title, ISBN, year, quantity, edition, cover image URL, language, and summary.Member Management:Staff
role can manage members (add, update, delete, view).Member details include full name, email, address, phone, and maximum
borrow limit.Borrowing and Returning:Librarian role handles book borrowing and returning.Records borrowing transactions,
including member, book, checkout date, due date, return date, and fine amount.Automatically decreases book quantity upon
borrowing and increases it upon returning.Roles and Permissions:Admin: Manages users.Librarian: Manages books,
borrowing, and returning.Staff: Manages members and assists with borrowing/returning.Database Management:MySQL
database.Flyway for database migrations.Code Efficiency:Lombok to reduce boilerplate code in entity classes.MapStruct
for efficient mapping between entities and DTOs.Logging:User logs are saved to external files for auditing and
tracking.Technologies UsedSpring BootSpring Data JPASpring SecurityLombokMapStructFlywayMySQLDatabase SchemaThe database
schema consists of the following tables:authors: Stores author details (author_id, name, nationality, bio).book_authors:
связывает авторов с книгами (author_id, book_id).book_categories: связывает книги с категориями (book_id, category_id)
.books: Stores book details (book_id, title, isbn, year, quantity, edition, cover_image_url, language, summary,
publisher_id, created_by, creation_date, modified_by, modified_date).borrowing_transactions: Stores borrowing
transaction details (transaction_id, member_id, book_id, created_by, checkout_date, modified_by, modified_date,
return_date, due_date, fine_amount).categories: Stores book categories (category_id, name).category_hierarchy: Stores
category hierarchy (category_id, category_parent_id).members: Stores member details (member_id, full_name, email,
address, phone, created_by, creation_date, modified_by, modified_date, max_borrow_limit).publishers: Stores publisher
details (publisher_id, name, address, contact_email).users: Stores user details (user_id, username, password, full_name,
email, phone, role, address, is_valid).Roles and PermissionsThe system defines the following roles and their
corresponding permissions:Admin:Full access to user management (create, read, update, delete).Librarian:Full access to
book management (create, read, update, delete).Handles book borrowing (checkout).Handles book returning.Can view and
delete borrowing transactionsStaff:Full access to member management (create, read, update, delete).Can view borrowing
transactions and return booksSetup InstructionsPrerequisitesJava Development Kit (JDK) 21MySQL ServerMaven or
GradleInstallationClone the repository.Create a MySQL database for the library management system.Configure the database
connection in src/main/resources/application.properties.Run Flyway migrations to create the database schema.Build the
project using Maven (mvn clean install) or Gradle (gradle clean build).Run the Spring Boot
application.Configurationapplication.propertiesspring.application.name=LibraryManagementSystem
##database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_system
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=none
##flyway configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
##custom values for borrowing transaction
borrowingTransaction.GracePeriod=7
borrowingTransaction.fine.factor=5
##logging file configuration
logging.pattern.console=
#file properties
logging.file.path=logging/global/logs
logging.file.name=${logging.file.path}/myapp
logging.logback.rollingpolicy.max-file-size=5MB
logging.logback.rollingpolicy.max-history=5
Replace your_mysql_username and your_mysql_password with your actual MySQL credentials.Ensure the database name in the
URL (library_system) matches the name of your database.Flyway MigrationsFlyway will automatically apply these scripts
when the application starts.Example of a migration file (V1__initial_schema.sql):CREATE TABLE authors (
author_id INT AUTO_INCREMENT NOT NULL,
name VARCHAR(100) NULL,
nationality VARCHAR(100) NULL,
bio VARCHAR(500) NULL,
CONSTRAINT pk_authors PRIMARY KEY (author_id)
);
-- ... (Other CREATE TABLE statements from your provided schema)
LoggingUser activity logs are stored in external files.API EndpointsThe application provides RESTful API endpoints for
managing library resources. The endpoints are secured based on user roles. Here's a summary:Users:/api/v1/users: (
Admin) - Create, read, update, and delete users.Books:/api/v1/books: (Librarian) - Create, read, update, and delete
books.Members:/api/v1/members: (Staff) - Create, read, update, and delete members.Borrowing Transactions:
/api/transactions: (Librarian) - Borrow a book./api/transactions/return-of-book: (Librarian) - Return a
book./api/transactions: (Librarian, Staff) - Read/Delete borrowing transactions.AuthenticationSpring Security is used
for authentication.Users are authenticated using a username and a password.Authorization is based on roles (ADMIN,
LIBRARIAN, STAFF).